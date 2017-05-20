package org.campus02.lv.mc.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.campus02.lv.mc.entities.Group;
import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.repositories.GroupRepository;
import org.campus02.lv.mc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupsSevice {

	@Autowired
	private GroupRepository groupRepo;
	@Autowired
	private UserRepository userRepo;



	public void addUserToGroup(Long userId, Long groupId) {
		Group group = groupRepo.findOne(groupId);
		User user = userRepo.findOne(userId);
		group.getMembers().add(user);
		user.getGroups().add(group);
		groupRepo.save(group);
		userRepo.save(user);
	}

	public void addGroup(Group group, Long id) {
		User user = userRepo.findOne(id);
		Set<User> users=new HashSet<User>();
		users.add(user);		
		group.setMembers(users);
		Date date=new Date();
		group.setDate(date);
		groupRepo.save(group);
		user.getGroups().add(group);
		userRepo.save(user);
	}
}
