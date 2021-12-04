package com.olx.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.olx.dto.Advertisement;

public interface AdvertisementSerivce {
	
public Advertisement postAdvertiseByData(Advertisement advertise,String authToken);
	
	public List<Advertisement> getAdvertise(String authToken);
	
	public Advertisement updateAdvertise(String authToken , int id,  Advertisement advertise);
	
    public List<Advertisement> getUserAdvertisment(String authToken); 

	public List<Advertisement> searchByName(String authToken,String searchText);
	
	public Collection<Advertisement> FilterAdvertismentById(
			String searchText, 
			String category,
			String postedBy,
			String dateCondition,
			LocalDate onDate,
			LocalDate fromDate,
			LocalDate toDate,
			String sortBy,
			String startIndex,
			String records
			);
	
	public boolean deleteAdvertismentById(String authToken, int advertiseId);
	
	public  List<Map> getAdvertiseCategory();
}
