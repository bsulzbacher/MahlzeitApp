package org.campus02.lv.mc.services;

import java.util.List;

import org.campus02.lv.mc.entities.Cat;
import org.campus02.lv.mc.entities.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	private static final Logger log_ = LoggerFactory.getLogger(UserController.class);
	
	@Autowired	
	private GetRestaurantService resService;

	@RequestMapping(method = RequestMethod.GET, path = "/getall", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Restaurant>> getAll() {
		
		
		return new ResponseEntity<List<Restaurant>>(resService.getAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/getallcat", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Cat>> getAllCat() {
		
		
		return new ResponseEntity<List<Cat>>(resService.getAllCat(), HttpStatus.OK);
		
	}
	
	//{"name":"Chinese","ort":"Hauptplatz","category":{"id":2}}
	@RequestMapping(method = RequestMethod.POST, path = "/addrestaurant", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addRestaurant(@RequestBody Restaurant resToAdd) throws JsonProcessingException {
		
		this.resService.addRest(resToAdd);
		
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
	
	
	
	
	
}
