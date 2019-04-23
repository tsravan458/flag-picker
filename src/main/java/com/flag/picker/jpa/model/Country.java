package com.flag.picker.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "country_name")
	private String name;

	@Column(name = "flag")
	private String flag;

	@ManyToOne
	@JoinColumn(name = "continent_id", nullable = false,insertable=false,updatable=false)
	private Continent continent;

	public Country() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public Country(int id, String name, String flag, Continent continent) {
		super();
		this.id = id;
		this.name = name;
		this.flag = flag;
		this.continent = continent;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", flag=" + flag + ", continent=" + continent + "]";
	}

}
