package com.producersapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

	public Optional<Producer> findByEmailAndPassword(String email, String password);

	public Producer findByEmail(String email);

	@Query(value = "select * from producer p where p.name like concat('%', :name, '%')\n"
			+ "	|| p.nickname like concat('%', :name, '%')\n" + ";", nativeQuery = true)
	public List<Producer> findByNameOrNickname(@Param("name") String name);

	@Query(value = "select * from producer p where p.producer_manager like concat('%', :id, '%')\n"
			+ ";", nativeQuery = true)
	public List<Producer> findByManager(@Param("id") Integer id);

	@Query(value = "select * from producer p \n" + "inner join activity_name an on (p.producer_activity = an.id)\n"
			+ "where (p.producer) and (an.activity_name like concat ('%', :name, '%'))ORDER BY data_now DESC", nativeQuery = true)
	public List<Producer> findByProducerProduct(@Param("name") String name);

}
