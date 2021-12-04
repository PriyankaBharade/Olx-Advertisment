package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertisement;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertismentIdException;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.repo.AdvertisementRepo;
import com.olx.security.JwtUtil;

@Service
public class AdvertisementServiceImpl implements AdvertisementSerivce {

	@Autowired
	AdvertisementRepo advertisemenRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LoginServiceDelegate loginserviceDelegate;
	@Autowired
	JwtUtil jwtutil;
	@Autowired
	EntityManager entityManager;

	@Override
	public Advertisement postAdvertiseByData(Advertisement advertise, String authToken) {
		if (!loginserviceDelegate.isTokenValid(authToken)) {
			throw new InvalidAuthTokenException();
		}
		AdvertisementEntity advertisementEntity = modelMapper.map(advertise, AdvertisementEntity.class);
		advertisemenRepo.save(advertisementEntity);
		Advertisement advertiseData = modelMapper.map(advertisementEntity, Advertisement.class);
		return advertiseData;
	}

	@Override
	public List<Advertisement> getAdvertise(String authToken) {
		List<AdvertisementEntity> listAdvertisementEntities = advertisemenRepo.findAll();
		return convertEntityToDto(listAdvertisementEntities);
	}

	@Override
	public Advertisement updateAdvertise(String authToken, int id, Advertisement advertise) {
		Optional<AdvertisementEntity> optAdvertismentEntity = advertisemenRepo.findById(id);
		if (!loginserviceDelegate.isTokenValid(authToken)) {
			throw new InvalidAuthTokenException();
		}
		if (optAdvertismentEntity.isPresent()) {
			AdvertisementEntity advertisementEntity = optAdvertismentEntity.get();
			advertise.setId(id);
			advertisementEntity = this.modelMapper.map(advertise, AdvertisementEntity.class);
			advertisementEntity = advertisemenRepo.save(advertisementEntity);
			Advertisement advertiseres = this.modelMapper.map(advertisementEntity, Advertisement.class);
		} else {
			throw new InvalidAdvertismentIdException();
		}
		return advertise;
	}

	@Override
	public List<Advertisement> getUserAdvertisment(String authToken) {
		if (!loginserviceDelegate.isTokenValid(authToken)) {
			throw new InvalidAuthTokenException();
		}
		String JwtToken = authToken.substring(7, authToken.length());
		String username = jwtutil.extractUsername(JwtToken);
		List<Advertisement> advertisementList = convertEntityToDtoUser(advertisemenRepo.findAll(), username);
		return advertisementList;
	}

	@Override
	public List<Advertisement> searchByName(String authToken, String searchText) {
		if (!loginserviceDelegate.isTokenValid(authToken)) {
			throw new InvalidAuthTokenException();
		}
		List<Advertisement> advertisementList = searchConvertEntityToDto(advertisemenRepo.findAll(), searchText);
		return advertisementList;
	}

	@Override
	public List<Advertisement> FilterAdvertismentById(String searchText, String category, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy,
			String startIndex, String records) {
		List<Advertisement> advertismentList = filterConvertEntityToDto(advertisemenRepo.findAll(), searchText,
				category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(AdvertisementEntity.class);
		Root<AdvertisementEntity> rootEntity = criteriaQuery.from(AdvertisementEntity.class);
		Predicate titlePredicate = criteriaBuilder.equal(rootEntity.get("title"), searchText);
		Predicate categoryPredicate = criteriaBuilder.equal(rootEntity.get("category"), category);
		Predicate finalPredicate = criteriaBuilder.and(titlePredicate, categoryPredicate);
		criteriaQuery.where(finalPredicate);
		TypedQuery<AdvertisementEntity> query = entityManager.createQuery(criteriaQuery);
		List<AdvertisementEntity> advertismentEntity = query.getResultList();
		return convertEntityToDto(advertismentEntity);
	}

	@Override
	public boolean deleteAdvertismentById(String authToken, int advertiseId) {
		if (!loginserviceDelegate.isTokenValid(authToken)) {
			throw new InvalidAuthTokenException();
		}
		advertisemenRepo.deleteById(advertiseId);
		return true;
	}

	@Override
	public List<Map> getAdvertiseCategory() {
		return null;
		// TODO Auto-generated method stub return null;
	}

	public List<Advertisement> convertEntityToDto(List<AdvertisementEntity> list) {
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		for (AdvertisementEntity advertisementEntity : list) {
			Advertisement advertisementData = modelMapper.map(advertisementEntity, Advertisement.class);
			advertisementList.add(advertisementData);
		}
		return advertisementList;
	}

	public List<Advertisement> convertEntityToDtoUser(List<AdvertisementEntity> list, String username) {
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		for (AdvertisementEntity advertisementEntity : list) {
			Advertisement advertisementData = modelMapper.map(advertisementEntity, Advertisement.class);
			if (username.equals(advertisementData.getUsername())) {
				advertisementList.add(advertisementData);
			}
		}
		return advertisementList;
	}

	public List<Advertisement> searchConvertEntityToDto(List<AdvertisementEntity> list, String searchText) {
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		for (AdvertisementEntity advertisementEntity : list) {
			Advertisement advertisementData = modelMapper.map(advertisementEntity, Advertisement.class);
			if (advertisementData.getTitle().contains(searchText)
					|| advertisementData.getDescription().contains(searchText)) {
				advertisementList.add(advertisementData);
			}
		}
		return advertisementList;
	}

	public List<Advertisement> filterConvertEntityToDto(List<AdvertisementEntity> list, String searchText,
			String category, String postedBy, String dateCondition, LocalDate onDate, LocalDate fromDate,
			LocalDate toDate, String sortBy, String startIndex, String records) {
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		for (AdvertisementEntity advertisementEntity : list) {
			Advertisement advertisementData = modelMapper.map(advertisementEntity, Advertisement.class);
			if (advertisementData.getCategory().equals(category)) {
				advertisementList.add(advertisementData);
			}
		}
		return advertisementList;
	}

}
