package com.producersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producersapi.model.FarmingActivity;

@Repository
public interface FarmingActivityRepository extends JpaRepository<FarmingActivity, Integer> {

}
