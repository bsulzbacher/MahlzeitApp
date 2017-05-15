package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.pojo.UserComplete;
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
	            user = this.repo.findOne(id);
	        } catch (Exception e) {
	            log_.error(e.getMessage());
	            return null;
	        }
		 
		log_.info(user.toString());
		log_.info(id.toString());
		
		return user;
	}
	
	public List<UserComplete> getAll(Long id) {
		List<User> users = this.repo.findAll();
		
		User foundUser = loginuser(id);
		
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
		return returnedUsers;
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