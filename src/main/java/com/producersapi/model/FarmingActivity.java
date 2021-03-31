package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.producersapi.enums.ActivitiesName;

import lombok.Data;

@Data
@Entity
public class FarmingActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private ActivitiesName activityName;

	private String product;

	private float averageCash;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Producer> producers;

}