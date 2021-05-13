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
public class ActivityName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer value;

	private String label;
	
	@JsonIgnore
	@OneToMany(mappedBy = "activityName")
	private List<FarmingActivity> FarmingActivities;

	public Integer getValue() {
		return value;
	}

}