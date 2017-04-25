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
		
		List<User> users = this.userService.getAll(id);
		
		User foundUser = this.userService.loginuser(id);
	   
		if (users != null && foundUser != null) {
			
			List<UserComplete> returnedUsers = new ArrayList<>();
			
			for (User u1 : users) {
				
				boolean found = false;
				for (User u2 : foundUser.getFavorites()) {
					if (u1.getId().equals(u2.getId())) {
						found = true;
						break;
					}
				}
				
				if (!u1.getId().equals(id)) {
					UserComplete uc = new UserComplete(u1.getId(), u1.getSurname(), u1.getPrename());
					uc.setIsFriend(found);
					
					returnedUsers.add(uc);
				}
			}
			
			return new ResponseEntity<List<UserComplete>>(returnedUsers, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserComplete>>(new ArrayList<UserComplete>(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/addFriends/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addFriends(@PathVariable(value="id") Long id, @RequestBody List<Long> usersToAdd) throws JsonProcessingException {
		
		this.userService.addFriends(usersToAdd, id);
		
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}
}