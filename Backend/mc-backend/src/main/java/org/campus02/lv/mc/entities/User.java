package org.campus02.lv.mc.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String surname;
	private String prename;
	private String pwd;
	@OneToMany
	private Set<User> favorites;
	@ManyToMany
	@JoinTable(name="user_groups")
	private Set<Group> groups;

	public User(String surname, String prename, String pwd) {
		this.surname = surname;
		this.prename = prename;
		this.pwd = pwd;
	}

//	@Override
//	 public String toString() {
//	  return "User [id=" + id + ", surname=" + surname + ", prename=" + prename + ", pwd=" + pwd + ", favorites="
//	    + favorites + ", groups=" + groups + "]";
//	 }
	
	public User(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public Set<User> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<User> favorites) {
		this.favorites = favorites;
	}
}
