package com.bridgelabz.configration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configrations {
   
	@Bean
	public ModelMapper mapperModel() {
		return new ModelMapper();
	}
}
