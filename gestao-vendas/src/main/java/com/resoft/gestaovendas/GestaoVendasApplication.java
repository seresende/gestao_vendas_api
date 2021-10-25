package com.resoft.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.resoft.gestaovendas.entity"})
@EnableJpaRepositories(basePackages = {"com.resoft.gestaovendas.repository"})
@ComponentScan(basePackages = {" com.resoft.gestaovendas.service"," com.resoft.gestaovendas.controller"})
@SpringBootApplication
public class GestaoVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVendasApplication.class, args);
	}

}
