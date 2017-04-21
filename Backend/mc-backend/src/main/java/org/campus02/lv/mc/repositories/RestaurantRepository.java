package org.campus02.lv.mc.repositories;


import org.campus02.lv.mc.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}