package org.campus02.lv.mc.repositories;

import org.campus02.lv.mc.entities.Group;
import org.campus02.lv.mc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}