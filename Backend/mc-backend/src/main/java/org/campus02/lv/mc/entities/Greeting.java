package org.campus02.lv.mc.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String msg;

	protected Greeting() {
	}

	public Greeting(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return String.format("Greeting[id=%d, msg='%s']", id, msg);
	}
}