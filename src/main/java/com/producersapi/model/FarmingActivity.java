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
import com.producersapi.enums.Period;
import com.producersapi.enums.ProductsName;

import lombok.Data;

@Data
@Entity
public class FarmingActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private ActivitiesName activityName;

	private ProductsName productName;
	
	private Period period;

	private float averageCash;
	
	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Producer> producers;

	public void setProductName(ProductsName productName) {
		this.productName = productName;
		
	}

	public void setActivityName(ActivitiesName activityName) {
		this.activityName = activityName;
		
	}

	public void setPeriod(Period period) {
		this.period = period;
		
	}

	public void setAverageCash(float averageCash) {
		this.averageCash = averageCash;
		
	}

}
