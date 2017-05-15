package org.campus02.lv.mc.pojo;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Groups {
	 
	@XmlAttribute
	private Long id;
	
	@XmlAttribute
	private Date date;
	
	
	@XmlElement
	private Restaurant resturant;
	
	
	@XmlElement
	private Set<UserComplete> members;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Restaurant getResturant() {
		return resturant;
	}


	public void setResturant(Restaurant resturant) {
		this.resturant = resturant;
	}


	public Set<UserComplete> getMembers() {
		return members;
	}


	public void setMembers(Set<UserComplete> members) {
		this.members = members;
	}


	public Groups(Long id, Date date, Restaurant resturant, Set<UserComplete> members) {
		super();
		this.id = id;
		this.date = date;
		this.resturant = resturant;
		this.members = members;
	}


	public Groups() {
	
	}
	
	
	


	
}
