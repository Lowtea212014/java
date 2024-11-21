package com.example.demo.controller;

import java.util.List;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.exception.WorkerException;



import com.example.demo.model.dto.WorkerDto;
import com.example.demo.service.WorkerService;


@Controller
@RequestMapping(value = {"/worker", "/workers"})
public class WorkerController {
	
	@Autowired
	private WorkerService workerService;
	
	@GetMapping
	public String getWorkers(Model model, @ModelAttribute WorkerDto workerDto) {
		List<WorkerDto> workerDtos = workerService.getAllWorkers();
		model.addAttribute("workerDtos", workerDtos);	
		return "worker/worker";
		
		
	}
	//@Valid
	@PostMapping
	public String addWorker(@Valid @ModelAttribute WorkerDto workerDto,BindingResult bindingResult, Model model) {
		//如果驗證有錯誤
		if(bindingResult.hasErrors()) {
			model.addAttribute("workerDtos", workerService.getAllWorkers());
			return "worker/worker";
		}
		
		workerService.addWorker(workerDto);
		return "redirect:/workers"; // 重導到 /workers 頁面
	}
	@GetMapping("/delete/{workerId}")
	public String deleteWorker(@PathVariable Integer workerId) {
		workerService.deleteWorker(workerId);
		return "redirect:/workers"; // 重導到 /workers 頁面
	}
	@GetMapping("/{workerId}")
	public String getWorker(@PathVariable Integer workerId, Model model) {
		WorkerDto workerDto = workerService.getWorkerById(workerId);
		model.addAttribute("workerDto", workerDto);
		return "worker/worker_update";
	}
	@PostMapping("/update/{workerId}")
	public String updateWorker(@PathVariable Integer workerId, @Valid @ModelAttribute WorkerDto workerDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) { // 若有錯誤發生
			model.addAttribute("workerDto", workerDto); // 將原本的 workerDto 回傳
			return "worker/worker_update"; // 會自動將錯誤訊息傳給 jsp
		}
		workerService.updateWorker(workerId, workerDto);
		return "redirect:/workers"; // 重導到 /workers 頁面
	}
	
	@ExceptionHandler({WorkerException.class})
	public String handleWorkerException(WorkerException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error";
	}
	
}

