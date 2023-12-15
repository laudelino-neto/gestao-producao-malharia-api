package br.com.gestaoproducaomalharia;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.gestaoproducaomalharia.entity.Colaborador;
import br.com.gestaoproducaomalharia.service.impl.EscalaServiceImpl;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}		
	
	@Autowired
	private EscalaServiceImpl service;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			LocalDate dataInicial = LocalDate.of(2023, 12, 19);
			LocalDate dataFinal = LocalDate.of(2023, 12, 25);
			LocalTime entrada = LocalTime.of(9, 0);
			LocalTime saida = LocalTime.of(18, 0);			
			Colaborador colaborador = new Colaborador();
			colaborador.setId(1);
			this.service.gerarPor(colaborador, dataInicial, dataFinal, entrada, saida);					
		};
	}

}
