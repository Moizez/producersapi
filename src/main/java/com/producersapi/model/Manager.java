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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "manager_address")
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private List<Producer> producers;

	public void setName(String name) {
		this.name=name;
		
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		
	}

	public void setCpf(String cpf) {
		this.cpf=cpf;
		
	}

	public void setBirthDate(Date date) {
		this.birthDate = date;
		
	}

	public void setPhone(String phone) {
		this.phone=phone;
		
	}

	public void setEmail(String email) {
		this.email=email;
		
	}

	public void setPassword(String password) {
		this.password=password;
		
	}

	public void setAddress(Address address) {
		this.address = address;
		
	}

}
