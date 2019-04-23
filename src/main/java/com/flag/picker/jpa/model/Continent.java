package com.flag.picker.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Continent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String continent;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "continent_id", nullable = false)
	private Set<Country> countries;

	public Continent() {
	}

	public Long getId() {
		return id;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Continent(Long id, String continent, Set<Country> countries) {
		super();
		this.id = id;
		this.continent = continent;
		this.countries = countries;
	}

	@Override
	public String toString() {
		return "Continent [id=" + id + ", continent=" + continent + ", countries=" + countries + "]";
	}

}
