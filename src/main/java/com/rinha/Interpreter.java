package com.rinha;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    public Object runInterpreter(JsonObject mappedJson,  Map<String, Object> env){
        System.out.println( "Kind = "+ mappedJson.get("kind").getAsString());
        switch (mappedJson.get("kind").getAsString()){
            case "Print": {
                Object printValue = runInterpreter(mappedJson.getAsJsonObject("value"), env);
                System.out.println(printValue);
            }

            case "Str": {
                return mappedJson.get("value").getAsString();
            }

            case "Let": {
                Object valueLet = runInterpreter(mappedJson.getAsJsonObject("value"), env);
                env.put(mappedJson.getAsJsonObject("name").get("text").getAsString(), valueLet);
                return runInterpreter(mappedJson.getAsJsonObject("next"), env);
            }

            case "Call": {
                System.out.println("a");
                if ("fib".equals(mappedJson.getAsJsonObject("callee").get("text").getAsString())){
                    //Call mapped Json passing argumnets
                    break;
                }else {

                }
            }
            case "Binary": {
                //TODO: Finish bynary operation and strat implemnets fib function
                //TODO: Roda o programa vou conseguir ver onde parou no fluxo
            }

            case "Function": {
                return mappedJson;
            }

        }
        return null;
    }
}
