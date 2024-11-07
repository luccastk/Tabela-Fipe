package br.com.fipejapa.tabelafipe.core;

import br.com.fipejapa.tabelafipe.model.DataFipe;
import br.com.fipejapa.tabelafipe.model.Models;
import br.com.fipejapa.tabelafipe.model.Vehicles;
import br.com.fipejapa.tabelafipe.service.FipeApiClient;
import br.com.fipejapa.tabelafipe.service.JsonConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuChoice {
    private Scanner sc = new Scanner(System.in);
    private FipeApiClient apiClient = new FipeApiClient();
    private JsonConverter converter = new JsonConverter();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";


    public void displayMenu() {
        String menu = """
                ************************
                *      Tabela Fipe     *
                ************************
               \s
                [Carros] [Motos] [Caminhões]
               \s
                Escolha o automóvel:\s""";

        System.out.print(menu);

        var choice = sc.nextLine();
        String address = "";

        if (choice.toLowerCase().contains("carr")) {
            address = URL_BASE + "carros/marcas";
        } else if (choice.toLowerCase().contains("mot")) {
            address = URL_BASE + "motos/marcas";
        } else if (choice.toLowerCase().contains("caminh")) {
            address = URL_BASE + "caminhoes/marcas";
        } else {
            System.out.println("Digite novamante!");
        }

        var json = apiClient.requestApi(address);
        var brand = converter.retrieveList(json, DataFipe.class);

        brand.stream()
                .sorted(Comparator.comparing(DataFipe::codigo))
                .forEach(System.out::println);

        System.out.print("Digite o código para consulta: ");
        var codeBrand = sc.nextLine();

        address = address + "/" + codeBrand + "/modelos";
        json = apiClient.requestApi(address);

        var modelList = converter.retrieveData(json, Models.class);

        System.out.println("\nModelos dessa marca: ");
        modelList.modelos().stream()
                .sorted(Comparator.comparing(DataFipe::codigo))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do carro.");
        var patchCar = sc.nextLine();

        List<DataFipe> filtredModels = modelList.modelos().stream()
                .filter(m -> m.marca().toLowerCase().contains(patchCar.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos Filtrados");
        filtredModels.forEach(System.out::println);

        System.out.println("Digite o código.");
        var modelCode = sc.nextLine();

        address = address + "/" + modelCode + "/anos";
        json = apiClient.requestApi(address);
        List<DataFipe> years = converter.retrieveList(json, DataFipe.class);
        List<Vehicles> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var addressYears = address + "/" + years.get(i).codigo();
            json = apiClient.requestApi(addressYears);
            Vehicles vehicle = converter.retrieveData(json, Vehicles.class);
            vehicles.add(vehicle);

        }

        System.out.println("Todos os veículos por ano.");
        vehicles.forEach(System.out::println);
    }
}
