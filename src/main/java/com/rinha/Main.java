package com.rinha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rinha.structure.JsonStructure;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        String filePath = "./var/rinha/source.rinha.json";
        Map<String, Object> env = new HashMap<>();
        new Interpreter().runInterpreter(new JsonStructure().getAbstractTree(filePath), env);
    }
}