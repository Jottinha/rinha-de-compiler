package com.rinha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rinha.structure.JsonStructure;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        String filePath = "./var/rinha/source.rinha.json";
        new Interpreter().runInterpreter(new JsonStructure().getAbstractTree(filePath));
    }
}