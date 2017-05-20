package org.campus02.lv.mc.repositories;


import org.campus02.lv.mc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


}