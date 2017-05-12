package org.campus02.lv.mc.pojo;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Cat{
	
	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String cat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public Cat(Long id, String cat) {
		super();
		this.id = id;
		this.cat = cat;
	}

	public Cat() {
		super();
	}
	
	
	
	
}
