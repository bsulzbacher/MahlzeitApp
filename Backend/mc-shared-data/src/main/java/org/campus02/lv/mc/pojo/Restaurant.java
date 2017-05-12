package org.campus02.lv.mc.pojo;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Restaurant {
	
	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String name;
	@XmlElement
	private String ort;
	@XmlElement
	private Cat category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public Cat getCategory() {
		return category;
	}
	public void setCategory(Cat category) {
		this.category = category;
	}
	public Restaurant(Long id, String name, String ort, Cat category) {
		super();
		this.id = id;
		this.name = name;
		this.ort = ort;
		this.category = category;
	}
	
	
	public Restaurant() {

	}
}
