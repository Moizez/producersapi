package com.producersapi.enums;

public enum ActivitiesName {
	AGRICULTOR("Agricultor"), PESCADOR("Pescador"), APICULTOR("Apicultor");

	private String name;

	ActivitiesName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
