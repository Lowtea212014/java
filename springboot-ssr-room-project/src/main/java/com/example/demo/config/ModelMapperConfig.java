package com.example.demo.config;


//配置ModelMapper 便於DTO與實體之間的轉換
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Springboot 在啟動完成前會先行執行這個配置
public class ModelMapperConfig {
	
	@Bean	//由SpringBoot 來管理此物件
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}