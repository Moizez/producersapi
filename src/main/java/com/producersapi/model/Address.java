package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	private String district;

	private String street;

	private String houseNumber;

	private String reference;

	//@JsonIgnore
	//@OneToMany(mappedBy = "producer")
	//private List<Producer> producers;

}
