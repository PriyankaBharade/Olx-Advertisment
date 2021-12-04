package com.olx.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertisement;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.service.AdvertisementSerivce;
import com.olx.service.LoginServiceDelegate;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/olx/advertisement")
public class AdvertisementController {

	@Autowired
	@Qualifier("MONGO_SERVICE")
	AdvertisementSerivce advertisementService;

	@PostMapping(value = "/advertise", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create Advertisement")
	public Advertisement PostAdvertisement(@RequestHeader("Authorization") String authToken,
			@RequestBody Advertisement advertisementData) {
		return advertisementService.postAdvertiseByData(advertisementData, authToken);
	}

	@PutMapping(value = "/advertise/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update Advertisement")
	public Advertisement updateAdvertise(@RequestHeader("Authorization") String authToken, @PathVariable("id") int id,
			@RequestBody Advertisement advertise) {
		return advertisementService.updateAdvertise(authToken, id, advertise);
	}

	
	  @GetMapping(value = "/user/advertise", produces = {
	  MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	  @ApiOperation(value = "Get Advertisement") 
	  public List<Advertisement> getUserAdvertisment(@RequestHeader("Authorization") String authToken) {
	  return advertisementService.getUserAdvertisment(authToken); }
	 

	@DeleteMapping(value = "/user/advertise/{advertiseId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Delete Advertisement Accroding to advertisement Id")
	public boolean deleteAdvertismentById(@RequestHeader("Authorization") String authToken,
			@PathVariable("advertiseId") int advertiseId) {
		return advertisementService.deleteAdvertismentById(authToken, advertiseId);
	}

	@ApiOperation(value = "Search Advertisement List")
	@GetMapping(value = "/advertise", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Advertisement> searchThroughName(@RequestHeader("Authorization") String authToken,
			@RequestParam(value = "searchText", required = false) String searchText) {
		return advertisementService.searchByName(authToken, searchText);
	}

	/*
	 * @GetMapping(value = "/advertise", produces = {
	 * MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	 * 
	 * @ApiOperation(value = "Get Advertisement") public List<Advertisement>
	 * getAdvertisment(@RequestHeader("Authorization") String authToken) { return
	 * advertisementService.getAdvertise(authToken); }
	 */
	
	
	
	@ApiOperation(value = "Filter Advertisement Data")
	@GetMapping(value = "/advertise/search/filtercriteria", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Collection<Advertisement> FilterAdvertisment(
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "postedBy", required = false) String postedBy,
			@RequestParam(value = "dateCondition", required = false) String dateCondition,
			@RequestParam(value = "onDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "startIndex", required = false) String startIndex,
			@RequestParam(value = "records", required = false) String records) {
		return advertisementService.FilterAdvertismentById(searchText, category, postedBy, dateCondition, onDate,
				fromDate, toDate, sortBy, startIndex, records);
	}
	

}
