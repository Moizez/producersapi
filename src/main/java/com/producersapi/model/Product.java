package com.producersapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer value;

	private String label;

	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Producer> producers;
	
	@JsonIgnore
	@OneToMany
    @JoinColumn(name = "product_value") 
    private List<SaleProducer> SalesProducer;
	
}