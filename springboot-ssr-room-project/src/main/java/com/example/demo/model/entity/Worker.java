package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "worker")
public class Worker {
	
	@Id
	@Column(name = "worker_id")
	private Integer workerId;
	
	@Column(name = "worker_name", nullable = false )
	private String workerName;
	
	@Column(name = "worker_identity")
	private String workerIdentity;
	
	@Column(name = "worker_permissions")
	private String workerPermissions;
	
	@Column(name = "worker_salary")
	private double workerSalary;
	
	@Column(name = "worker_active", columnDefinition = "boolean default false")
	private boolean workerActive;
	
	@Column(name = "worker_work_time")
	private double workerWorkTime;

	
	
	
	
	

}
