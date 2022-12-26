package com.eryckavel.springspa;

import com.eryckavel.springspa.model.Cargo;
import com.eryckavel.springspa.repository.CargoRepository;
import com.eryckavel.springspa.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringSpaApplication implements CommandLineRunner {

	private boolean system = true;

	@Autowired
	CargoService cargoService;
	public static void main(String[] args) {
		SpringApplication.run(SpringSpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner entrada = new Scanner(System.in);

		while(system){
			System.out.println("Qual acão voçe quer executar?");
			System.out.println("0 - sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			int action = entrada.nextInt();
			switch(action){
				case 0:
					System.out.println("Finalizando a aplicação!");
					system = false;
					break;
				case 1:
					cargoService.inicial(entrada);
					break;

			}
		}

	}
}
