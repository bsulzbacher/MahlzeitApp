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
	//@JoinTable(name = "Group_Join", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private Set<Group> groups;
	
	

	

	public User(String surname, String prename, String pwd) {
		this.surname = surname;
		this.prename = prename;
		this.pwd = pwd;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", surname=" + surname + ", prename=" + prename + ", pwd=" + pwd + ", favorites="
				+ favorites + ", groups=" + groups + "]";
	}



	
}

