package com.producersapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.producersapi.enums.Parameter;

import lombok.Data;

@Data
@Entity
public class SaleProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private float valor;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	private float quantity;
	
	private String city;
	
	private Parameter parameter;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Producer producer;
	
}



