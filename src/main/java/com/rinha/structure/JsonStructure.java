package com.rinha.structure;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonStructure {

    private String getStringJson(String filePath){
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_16);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public JsonObject getAbstractTree(String filePath){
        String json = getStringJson(filePath);
        return new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("expression");
    }
}
