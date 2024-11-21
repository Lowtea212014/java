package com.example.demo.service;

import java.util.List;


import com.example.demo.model.dto.WorkerDto;

public interface WorkerService {
	
	public List<WorkerDto> getAllWorkers();
	public WorkerDto getWorkerById(Integer workerId);
	public void addWorker(WorkerDto workerDto); // 新增
	public void addWorker(Integer workerId, String workerName, String workerIdentity, String workerPermissions, double workerSalary, boolean workerActive, double workerTime); // 新增
	public void updateWorker(Integer workerId, WorkerDto workerDto); // 修改
	public void updateWorker(Integer workerId, String workerName, String workerIdentity, String workerPermissions, double workerSalary, boolean workerActive, double workerTime); // 修改
	public void deleteWorker(Integer workerId); // 刪除
	
	
}
