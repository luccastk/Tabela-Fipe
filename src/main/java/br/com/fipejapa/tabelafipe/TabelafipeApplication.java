package br.com.fipejapa.tabelafipe;

import br.com.fipejapa.tabelafipe.core.MenuChoice;
import br.com.fipejapa.tabelafipe.service.FipeApiClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TabelafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MenuChoice menuChoice = new MenuChoice();

		menuChoice.displayMenu();
		}
	}
