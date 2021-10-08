package com.producersapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

	public Site findSiteById(Integer id);
	
	@Query(value = "select * from site s where s.name like concat('%', :name, '%')" ,  nativeQuery = true)
	public List<Site> findByName(@Param("name") String name);

}
