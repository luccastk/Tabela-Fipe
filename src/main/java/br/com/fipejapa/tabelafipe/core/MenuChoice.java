package br.com.fipejapa.tabelafipe.core;

import br.com.fipejapa.tabelafipe.service.FipeApiClient;

import java.util.Scanner;

public class MenuChoice {
    private Scanner sc = new Scanner(System.in);
    private FipeApiClient apiClient = new FipeApiClient();

    public void displayMenu() {
        String menu = """
                ************************
                *      Tabela Fipe     *
                ************************
                
                [1 - Carros] [2 - Motos] [3 - Caminhões]
                
                Escolha o automóvel: """;

        System.out.print(menu);

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                var carros = apiClient.urlApi("https://parallelum.com.br/fipe/api/v1/carros/marcas");
                System.out.println(carros);
                break;

            case 2:
                var motos = apiClient.urlApi("https://parallelum.com.br/fipe/api/v1/motos/marcas");
                System.out.println(motos);
                break;

            case 3:
                var caminhoes = apiClient.urlApi("https://parallelum.com.br/fipe/api/v1/caminhoes/marcas");
                System.out.println(caminhoes);
                break;

            default:
                System.out.println("Opção inválida!");

        }
    }
}
