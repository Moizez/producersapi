package com.producersapi.util;

import com.producersapi.model.Manager;
import com.producersapi.model.Producer;

import lombok.Data;

@Data
public class ProfileType {

	private Producer producer;
	private Manager manager;

}
