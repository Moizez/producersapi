package com.producersapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	public Optional<Manager> findByEmailAndPassword(String email, String password);
	public Manager findByEmail(String email);
	
	@Query(value = "select * from manager m where m.cpf = ?", nativeQuery = true)
	public Manager findByCpf(String cpf);
}
