package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetUserService {
	private static final Logger log_ = LoggerFactory.getLogger(GetUserService.class);
	
	@Autowired	
	private UserRepository repo;
	
	public User loginuser(Long id) {
		User user; 
		try {
	            user = this.repo.getOne(id);
	        } catch (Exception e) {
	            log_.error(e.getMessage());
	            return null;
	        }
		 
		log_.info(user.toString());
		log_.info(id.toString());
		
		return user;
	}
	
	public List<User> getAll(Long id) {

		return this.repo.findAll();
	}
	
	public void addFriends(List<Long> usersToAdd, Long loggedInUserId) {
		
		User loggedInUser = this.repo.getOne(loggedInUserId);
		
		loggedInUser.setFavorites(new HashSet<User>());
		
		for (Long uid : usersToAdd) {
			
			User u = this.repo.getOne(uid);
			if (u != null) {
				loggedInUser.getFavorites().add(u);
			}
		}
		this.repo.save(loggedInUser);
		
	}

}