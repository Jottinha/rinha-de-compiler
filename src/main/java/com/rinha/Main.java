package com.rinha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rinha.structure.JsonStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        String filePath = "./var/rinha/source.rinha.json";
        Map<String, Object> env = new HashMap<>();

        long startTime = System.nanoTime();

        new Interpreter().runInterpreter(new JsonStructure().getAbstractTree(filePath), env);

        long endTime = System.nanoTime();

        // Calcular tempo execução em Mili.
        long durationInNano = endTime - startTime;
        long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(durationInNano);

        System.out.println("Tempo de execução: " + durationInMilliseconds + " milissegundos");
    }
}