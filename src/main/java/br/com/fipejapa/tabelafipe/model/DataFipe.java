package br.com.fipejapa.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataFipe(@JsonAlias("nome") String marca,
                       @JsonAlias("codigo") String codigo) {

}
