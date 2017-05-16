package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.campus02.lv.mc.entities.Group;
import org.campus02.lv.mc.entities.User;
import org.campus02.lv.mc.pojo.Cat;
import org.campus02.lv.mc.pojo.Groups;
import org.campus02.lv.mc.pojo.Restaurant;
import org.campus02.lv.mc.pojo.UserComplete;
import org.campus02.lv.mc.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetGroupsSevice {

	@Autowired
	private GroupRepository groupRepo;
	@Autowired
	private GetUserService userService;

	public List<Groups> getAll(Long id) {

		List<Groups> groupsSend = new ArrayList<>();
		List<Group> allGroups = this.groupRepo.findAll();
		User foundUser = this.userService.loginuser(id);

		for (Group g : allGroups) {
			Cat cat = new Cat(g.getRestaurant().getCategory().getId(), g.getRestaurant().getCategory().getCategory());
			Set<UserComplete> members = new HashSet<>();

			for (User u : g.getMembers()) {
				UserComplete uc = new UserComplete(u.getId(), u.getSurname(), u.getPrename());
				Set<User> favorites = foundUser.getFavorites();
				boolean isFriend = favorites.stream().filter(user -> user.getId().equals(u.getId())).count() > 0;
				uc.setIsFriend(isFriend);
				members.add(uc);
			}
			Restaurant res = new Restaurant(g.getRestaurant().getId(), g.getRestaurant().getName(),
					g.getRestaurant().getOrt(), cat);
			Groups group = new Groups(g.getId(), g.getDate(), res, members);
			groupsSend.add(group);
		}

		return groupsSend;
	}
}
