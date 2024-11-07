package br.com.fipejapa.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class JsonConverter implements IJsonConverter {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T retrieveData(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> retrieveList(String json, Class<T> type) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, type);

        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
