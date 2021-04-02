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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Producer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String nickname;

	private String phone;

	private String email;
	
	@JsonIgnore
	private String password;
	
	private int role = 1;
	
	private int profile;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	private String cpf;
	
	@ManyToOne
	@JoinColumn(name = "producer_manager")
	private Manager manager;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "producer_address")
	private Address address;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "producer_activity")
	private FarmingActivity farmingActivity;

}
