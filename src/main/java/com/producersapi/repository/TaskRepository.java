package com.producersapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.producersapi.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value = "select * from task t where date(t.task_date) = curdate() order by t.task_date asc", nativeQuery = true)
	public List<Task> findByCurrentDate();

	@Query(value = "select * from task t where date(t.task_date) > curdate() order by t.task_date asc", nativeQuery = true)
	public List<Task> findByFutureTasks();

}
