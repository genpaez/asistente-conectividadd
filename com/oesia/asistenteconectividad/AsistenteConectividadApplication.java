package com.oesia.asistenteconectividad;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan("com.oesia")
@EntityScan(basePackages = {"com.oesia.model"})
@SpringBootApplication
public class AsistenteConectividadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsistenteConectividadApplication.class, args);
	}
}
