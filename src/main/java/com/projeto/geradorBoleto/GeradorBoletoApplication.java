package com.projeto.geradorBoleto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.projeto.geradorBoleto")
public class GeradorBoletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeradorBoletoApplication.class, args);
	}

}
