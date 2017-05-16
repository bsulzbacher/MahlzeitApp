package org.campus02.lv.mc.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.campus02.lv.mc.entities.Cat;
import org.campus02.lv.mc.entities.Restaurant;
import org.campus02.lv.mc.repositories.CategoryRepository;
import org.campus02.lv.mc.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetRestaurantService {

	@Autowired
	private RestaurantRepository resRepo;
	@Autowired
	private CategoryRepository catRepo;


	public List<Restaurant> getAll() {

		List<Restaurant> restaurants = this.resRepo.findAll();
	

		return restaurants;
	}
	
	
	public List<Cat> getAllCat() {

		List<Cat> cat = this.catRepo.findAll();
	

		return cat;
	}
	
	
	public void addRest(Restaurant r){
		this.resRepo.save(r);
	}
}
