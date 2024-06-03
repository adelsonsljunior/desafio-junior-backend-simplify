package com.adelsonsljunior.desafiojuniorbackendsimplify;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Desafio Júnior Backend Simplify", version = "1", description = "To do management system"))
public class DesafioJuniorBackendSimplifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioJuniorBackendSimplifyApplication.class, args);
    }

}
