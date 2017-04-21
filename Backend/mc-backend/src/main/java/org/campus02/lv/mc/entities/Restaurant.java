package org.campus02.lv.mc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String ort;
	private String category;
	
	public Restaurant(String name, String ort, String category) {
		this.name = name;
		this.ort = ort;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", ort=" + ort + ", category=" + category + "]";
	}
	
	
	
	
	
}
