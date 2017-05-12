package org.campus02.lv.mc.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Gruppe")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Restaurant restaurant;

	@ManyToMany(mappedBy="groups"	)
	private Set<User> members;
	
	private Date date;

	public Group(Restaurant restaurant, Set<User> members, Date date) {
		this.restaurant = restaurant;
		this.members = members;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", restaurant=" + restaurant + ", members=" + members + ", date=" + date + "]";
	}
	
	public Group(){
	}

}
