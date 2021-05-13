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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	private boolean status = true;

	@JsonIgnore
	private String password;

	private int role = 1;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthDate;

	private String cpf;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date created = new Date();

	@ManyToOne
	private Manager manager;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "producer_address")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "producer_activity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private FarmingActivity farmingActivity;

	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "producer_products")
	private List<Product> products;

	public FarmingActivity getFarmingActivity() {
		return farmingActivity;
	}
	
	public List<Product> getProducts() {
		return products;
	}

}