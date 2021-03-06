package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.List;

import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.pojo.LoginUser;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger log_ = LoggerFactory.getLogger(UserController.class);
	
	@Autowired	
	private GetUserService userService;

	@RequestMapping(method = RequestMethod.GET, path = "/getAll/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<UserComplete>> getAll(@PathVariable(value="id") Long id) {
		
		
	   
			
			return new ResponseEntity<List<UserComplete>>(this.userService.getAll(id), HttpStatus.OK);
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/addFriends/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addFriends(@PathVariable(value="id") Long id, @RequestBody List<Long> usersToAdd) throws JsonProcessingException {
		
		this.userService.addFriends(usersToAdd, id);
		
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
}