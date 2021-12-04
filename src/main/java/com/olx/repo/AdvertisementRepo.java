package com.olx.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertisementEntity;

public interface AdvertisementRepo extends JpaRepository<AdvertisementEntity, Integer>{

	//public List<AdvertisementEntity> findByTitle();
}