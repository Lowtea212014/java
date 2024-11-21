package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.WorkerDto;
import com.example.demo.model.entity.Worker;


@Component
public class WorkerMapper {
	//@Autowired
	//private ModelMapper modelMapper;
	@Autowired
	private ModelMapper modelMapper;
	
//	@Autowired
//	public RoomMapper(ModelMapper modelMapper) {
//		//...方法內容
//		this.modelMapper = modelMapper;
//	}
	//另一個方法
	public WorkerDto toDto(Worker worker) {
		//Entity 轉 Dto
		return modelMapper.map(worker, WorkerDto.class);
	}
	//反過來
	public Worker toEntity(WorkerDto workerDto) {
		//DTO 轉 Entity
		return modelMapper.map(workerDto, Worker.class);
	}
}
