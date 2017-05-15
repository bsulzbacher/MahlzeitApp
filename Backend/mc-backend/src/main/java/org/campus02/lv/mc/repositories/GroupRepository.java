package org.campus02.lv.mc.repositories;

import java.util.Date;
import java.util.List;

import org.campus02.lv.mc.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, Long> {


}