package com.example.demo.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
	
	@NotNull(message = "{ID不可為空}")
	private Integer workerId;
	
	@NotNull(message = "{姓名不可為空}")
	private String workerName;
	
	@NotNull(message = "{身分證不可為空}")
	@Size(min = 10, max = 10, message = "{身分證要十碼}")
	private String workerIdentity;
	
	@NotNull(message = "{權限不可為空}")
	private String workerPermissions;
	
	@NotNull(message = "{薪水不可為空}")
	@Range(min = 30000 , message = "{要大於30000}" )
	private double workerSalary;
	
	@NotNull(message = "{啟用是否不可為空}")
	private boolean workerActive;
	
	@NotNull(message = "{工作時間不可為空}")
	private double workerWorkTime;
	
}
