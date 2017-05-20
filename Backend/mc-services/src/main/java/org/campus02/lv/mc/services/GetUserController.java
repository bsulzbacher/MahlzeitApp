package org.campus02.lv.mc.services;

import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.pojo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController {
	private static final Logger log_ = LoggerFactory.getLogger(GetUserController.class);
	
	@Autowired	
	private GetUserService userService;

	@RequestMapping(method = RequestMethod.GET, path = "login/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<LoginUser> loginuser(@PathVariable(value="id") Long id) {
		User user = this.userService.loginuser(id);
	   
		if (user != null) {
			log_.info(user.toString());
			log_.info(id.toString());
			
			LoginUser loginUser = new LoginUser(user.getId(), user.getSurname(), user.getPrename());
			return new ResponseEntity<>(loginUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new LoginUser(), HttpStatus.OK);
		}
		
	}
}