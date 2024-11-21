package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.entity.Worker;

public interface WorkerRepositoryJdbc {
	//CRUD建立
	List<Worker> findAll();
	Optional<Worker> findById(Integer workerId);
	int save(Worker worker);
	int update(Worker worker);
	int deleteById(Integer workerId);
}
