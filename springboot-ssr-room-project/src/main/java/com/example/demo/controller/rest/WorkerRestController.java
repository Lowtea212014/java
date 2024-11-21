package com.example.demo.controller.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.WorkerDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.WorkerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/rest/worker")
@CrossOrigin(origins = {"http://localhost:8082, http://localhost:8083"},
					     allowCredentials = "true")
public class WorkerRestController {
	@Autowired
	private WorkerService workerService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<WorkerDto>>>getWorkers() {
		List<WorkerDto> workerDtos = workerService.getAllWorkers();
		String message = workerDtos.isEmpty() ? "查無員工資料" : "Worker 查詢多筆成功";
		
		
		return ResponseEntity.ok(ApiResponse.success(message, workerDtos));
	}
	//新增worker
	@PostMapping
	public ResponseEntity<ApiResponse<WorkerDto>>addWorker(@RequestBody WorkerDto workerDto) {
		workerService.addWorker(workerDto);
	
		return ResponseEntity.ok(ApiResponse.success("worker新增成功", workerDto));
	}
	//取得單筆
	@GetMapping({"/{workerId}"})
	public ResponseEntity<ApiResponse<WorkerDto>> getWorker(@PathVariable Integer workerId){
		WorkerDto workerDto =workerService.getWorkerById(workerId);
		return ResponseEntity.ok(ApiResponse.success("worker新增成功", workerDto));
	}
	
	
}
