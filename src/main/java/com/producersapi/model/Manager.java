package com.producersapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

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

	private String password;

	private int role = 0;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "manager_address")
	private Address address;

}
