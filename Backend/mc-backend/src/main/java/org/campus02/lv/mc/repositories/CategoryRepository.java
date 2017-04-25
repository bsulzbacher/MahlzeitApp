package org.campus02.lv.mc.repositories;

import org.campus02.lv.mc.entities.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository  extends JpaRepository<Cat, Long> {

}
