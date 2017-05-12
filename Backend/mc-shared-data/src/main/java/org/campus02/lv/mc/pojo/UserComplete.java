package org.campus02.lv.mc.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserComplete {
	 
	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String surname;
	
	@XmlElement
	private String prename;
	
	private Boolean isFriend;

	public UserComplete() {
		// TODO Auto-generated constructor stub
	}
	
	public UserComplete(Long id, String surname, String prename) {
		this.id = id;
		this.surname = surname;
		this.prename = prename;
	}
	

	public Long getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public String getPrename() {
		return prename;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public Boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Boolean isFriend) {
		this.isFriend = isFriend;
	}

	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", surname=" + surname + ", prename=" + prename + "]";
	}
}
