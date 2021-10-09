package com.producersapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Rain;
import com.producersapi.model.Site;

@Repository
public interface RainRepository extends JpaRepository<Rain, Integer> {
	
	@Query(value = "select * from rain r where date(r.date) = date(?) order by r.date asc", nativeQuery = true)
	public List<Rain> findByDate(Date date);
	
	@Query(value = "select * from rain r where date(r.date) between date(?) and date(?) order by r.date asc", nativeQuery = true)
	public List<Rain> findByPeriod(Date init, Date end);
	
	@Query(value = "select * from rain r where r.site in(?)", nativeQuery = true)
	public List<Rain> findBySites(List<Site> sites);
	
	public List<Rain> findBySite(Site site);
	
}
