package com.olx.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.AdvertisementDocument;


public interface AdvertismentMongoRepo extends MongoRepository<AdvertisementDocument,Integer>{

}
