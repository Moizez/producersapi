package com.producersapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
public class Rain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private int volume;
	
	@Column(name = "rain_date")
	private String date;
	
	private Date added = new Date();
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Site site;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	private Manager manager;

	public void setSite(Site site) {
		this.site = site;
	}

}
