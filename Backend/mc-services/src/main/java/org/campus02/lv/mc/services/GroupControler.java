package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.List;

import org.campus02.lv.mc.entities.Group;
import org.campus02.lv.mc.entities.Restaurant;
import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.pojo.Groups;
import org.campus02.lv.mc.pojo.UserComplete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/groups")
public class GroupControler {
	private static final Logger log_ = LoggerFactory.getLogger(UserController.class);
	
	@Autowired	
	private GetGroupsSevice groupservice;
	@Autowired	
	private GroupsSevice groupservicePost;
	

	@RequestMapping(method = RequestMethod.GET, path = "/getGroups/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Groups>> getAll(@PathVariable(value="id") Long id) {
		
		
		return new ResponseEntity<List<Groups>>(groupservice.getAll(id), HttpStatus.OK);
		
	}
	
	// {"restaurant":{"id":3}}
	@RequestMapping(method = RequestMethod.POST, path = "/addGroup/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addRestaurant(@PathVariable(value="id") Long id,@RequestBody Group groupToAdd) throws JsonProcessingException {
		
		this.groupservicePost.addGroup(groupToAdd,id);
		
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
	
	
	// 12
	@RequestMapping(method = RequestMethod.POST, path = "/addMember/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addRestaurant(@PathVariable(value="id") Long userId,@RequestBody Long groupId) throws JsonProcessingException {
		
		this.groupservicePost.addUserToGroup(userId, groupId);
		
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
	
	
}
