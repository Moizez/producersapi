package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String uf;

	private String city;
	
	private String zipCode;

	private String district;

	private String street;

	private String houseNumber;

	private String reference;

	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Producer> producers;
	
	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private Manager manager;

	public void setUf(String uf) {
		this.uf=uf;
		
	}

	public void setCity(String city) {
		this.city = city;
		
	}

	public void setZipCode(String zipCode) {
		this.zipCode=zipCode;
		
	}

	public void setDistrict(String district) {
		this.district = district;
		
	}

	public void setStreet(String street) {
		this.street = street;
		
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
		
	}

	public void setReference(String reference) {
		this.reference = reference;
		
	}

}
