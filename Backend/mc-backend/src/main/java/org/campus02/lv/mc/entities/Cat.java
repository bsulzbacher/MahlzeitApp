package org.campus02.lv.mc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
public class Cat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String category;

	public Cat(String category) {
		this.category = category;
	}

	public Cat() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
