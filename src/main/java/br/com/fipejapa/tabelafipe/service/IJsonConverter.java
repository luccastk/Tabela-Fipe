package br.com.fipejapa.tabelafipe.service;

import java.util.List;

public interface IJsonConverter {
    <T> T retrieveData(String json, Class<T> type);

    <T> List<T> retrieveList(String json, Class<T> type);
}
