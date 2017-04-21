package org.campus02.lv.mc.repositories;

import org.campus02.lv.mc.entities.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}