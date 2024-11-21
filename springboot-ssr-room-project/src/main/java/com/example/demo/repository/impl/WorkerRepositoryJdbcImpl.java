package com.example.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.demo.model.entity.Worker;
import com.example.demo.repository.WorkerRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties")
public class WorkerRepositoryJdbcImpl implements WorkerRepositoryJdbc{
	
	private static final Logger logger =LoggerFactory.getLogger(WorkerRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${worker.sql.findAll}")
	private String findAllSql;
	
	@Value("${worker.sql.findById}")
	private String findByIdSql;
	
	@Value("${worker.sql.save}")
	private String savesql;
	
	@Value("${worker.sql.update}")
	private String updatesql;
	
	@Value("${worker.sql.deleteById}")
	private String deleteByIdSql;
	
	
		
    @Override
    public List<Worker> findAll(){
    	return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<>(Worker.class));
    }

    @Override
    public Optional<Worker> findById(Integer workerId) {
        try {
            Worker worker = jdbcTemplate.queryForObject(findByIdSql, new BeanPropertyRowMapper<>(Worker.class), workerId);
            return Optional.ofNullable(worker);  // 使用 Optional.ofNullable 防止 worker 為 null
        } catch (EmptyResultDataAccessException e) {
            logger.info("Worker not found with id: " + workerId);  // 明確記錄未找到的情況
        } catch (Exception e) {
            logger.error("Error occurred while fetching Worker with id: " + workerId, e);  // 更詳細的錯誤處理
        }
        return Optional.empty();
    }

	@Override
	public int save(Worker worker) {
		
		return jdbcTemplate.update(savesql,
				worker.getWorkerId(),
				worker.getWorkerName(),
				worker.getWorkerIdentity(),
				worker.getWorkerPermissions(),
				worker.getWorkerSalary(),
				worker.getWorkerWorkTime(),
				worker.isWorkerActive());
	}

	@Override
	public int update(Worker worker) {
		return jdbcTemplate.update(updatesql,
				worker.getWorkerId(),
				worker.getWorkerName(),
				worker.getWorkerIdentity(),
				worker.getWorkerPermissions(),
				worker.getWorkerSalary(),
				worker.getWorkerWorkTime(),
				worker.isWorkerActive());
	}

	@Override
	public int deleteById(Integer workerId) {
		
		return jdbcTemplate.update(deleteByIdSql, workerId);
	}
	
}
