package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.ActivityName;

@Repository
public interface ActivityNameRepository extends JpaRepository<ActivityName, Integer> {

	public ActivityName findByLabel(String label);

	public ActivityName findByValue(Integer Value);

}
