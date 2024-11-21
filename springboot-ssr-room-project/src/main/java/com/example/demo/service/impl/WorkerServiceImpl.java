package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.WorkerAlreadyExistsException;
import com.example.demo.exception.WorkerException;
import com.example.demo.exception.WorkerNotFoundException;
import com.example.demo.mapper.WorkerMapper;
import com.example.demo.model.dto.WorkerDto;
import com.example.demo.model.entity.Worker;
import com.example.demo.repository.WorkerRepositoryJdbc;
import com.example.demo.service.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService{
	
	  //自動連結RepositoryJdbc
		@Autowired
		private WorkerRepositoryJdbc workerRepositoryJdbc;
		
		//綁定Mapper去Enitity DTO互轉
		@Autowired
		private WorkerMapper workerMapper;
		
		@Override
		public List<WorkerDto> getAllWorkers(){
			return workerRepositoryJdbc.findAll()
					.stream()
					.map(workerMapper::toDto)
					.collect(Collectors.toList());
		}

		@Override
		public WorkerDto getWorkerById(Integer workerId) {
			Worker work = workerRepositoryJdbc.findById(workerId)
											  .orElseThrow(()-> 
											  new WorkerNotFoundException("工人 ID " + workerId + " 不存在"));											  
 			return workerMapper.toDto(work);
		}

		@Override
		public void addWorker(WorkerDto workerDto) {
			// 判斷worker已存在
			Optional<Worker> optWorker = workerRepositoryJdbc.findById(workerDto.getWorkerId());
			if(optWorker.isPresent()) {
				throw new WorkerAlreadyExistsException("工人 ID " + workerDto.getWorkerId() + " 已經存在");
			}
			Worker worker = workerMapper.toEntity(workerDto); 
		
			int rowcount = workerRepositoryJdbc.save(worker);
			if (rowcount == 0 ) {
				throw new WorkerException("修改未成功");
			}
		
		}
		@Override
		public void addWorker(Integer workerId, String workerName, String workerIdentity, String workerPermissions, double workerSalary, boolean workerActive, double workerTime) {
			WorkerDto workerDto = new WorkerDto(workerId, workerName, workerName, workerName, workerId, false, workerId); 
			addWorker(workerDto);
		}

		@Override
		public void updateWorker(Integer workerId, WorkerDto workerDto) {
			Optional<Worker> optworker = workerRepositoryJdbc.findById(workerId);
			if(optworker.isEmpty()) {
				throw new WorkerNotFoundException("修改失敗:"+ workerId + "不存在");
			}
			workerDto.setWorkerId(workerId);
			Worker worker = workerMapper.toEntity(workerDto);
		    workerRepositoryJdbc.update(worker);
		}

		@Override
		public void updateWorker(Integer workerId, String workerName, String workerIdentity, String workerPermissions, double workerSalary, boolean workerActive, double workerTime) {
			WorkerDto workerDto = new WorkerDto(workerId, workerName, workerIdentity, workerPermissions, workerSalary, workerActive, workerTime); 
			updateWorker(workerId, workerDto);
		}

		@Override
		public void deleteWorker(Integer workerId) {
			Optional<Worker> optWork = workerRepositoryJdbc.findById(workerId);
			if(optWork.isEmpty()) {
				throw new WorkerNotFoundException("刪除失敗:"+ workerId + "不存在");
			}
			int rowcount = workerRepositoryJdbc.deleteById(workerId);
			if(rowcount == 0) {
				throw new WorkerException("無任何紀錄有被刪除");
			}
		}

		

}
