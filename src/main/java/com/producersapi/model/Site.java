package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Site implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@ManyToOne
	private Producer producer;
	
	@OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
	private List<Rain> rains;

	public void setRains(List<Rain> rains) {
		this.rains = rains;
	}

	public Integer getId() {
		return id;
	}

}
