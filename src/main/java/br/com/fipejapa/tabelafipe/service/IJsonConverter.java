package br.com.fipejapa.tabelafipe.service;

public interface IJsonConverter {
    <T> T retrieveData(String json, Class<T> classe);
}
