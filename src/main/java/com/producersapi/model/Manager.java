package com.producersapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String nickname;
	
	private String cpf;

	private String phone;

	private String email;
	
	@JsonIgnore
	private String password;

	private int role = 0;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "manager_address")
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private List<Producer> producers;
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	private List<Task> tasks;

}