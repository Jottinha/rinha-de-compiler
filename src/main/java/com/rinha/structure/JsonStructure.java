package com.rinha.structure;

import com.google.gson.Gson;
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

    public LanguageStructure getAbstractTree(String filePath){
        String json = getStringJson(filePath);
        Gson gson = new Gson();
        return gson.fromJson(json, LanguageStructure.class);
    }
}
