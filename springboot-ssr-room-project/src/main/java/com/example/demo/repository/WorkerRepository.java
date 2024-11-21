package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Worker;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer>{
	//預設會自動實現CRUD
		//自定義查詢
		//1.查詢 WorkerId大於指定值的房間
		List<Worker> findWorkerByWorkerIdGreaterThan(Integer workerId);

		//2.查詢roomSize 大於指定值的房間(JPQL)
		@Query("select w from Worker w where w.workerId > :workerId")
		List<Worker> findWorkerByWorkerIdGreaterThan1(Integer workerId);
		
		//3.查詢roomSize 大於指定值的房間(SQL)
		@Query(value = "select worker_id, worker_name, worker_identity, worker_permissions, worker_active, worker_salary, worker_workertime FROM worker WHERE worker_id > :workerId",	
						nativeQuery = true)
		List<Worker> findWorkerByWorkerIdGreaterThan2(Integer workerId);
}
