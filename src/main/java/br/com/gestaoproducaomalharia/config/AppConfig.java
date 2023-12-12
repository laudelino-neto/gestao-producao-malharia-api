package br.com.gestaoproducaomalharia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

@Configuration
public class AppConfig {

	@Bean
    public Hibernate5JakartaModule jsonHibernate5Module() {
        return new Hibernate5JakartaModule();
    }
		
}
