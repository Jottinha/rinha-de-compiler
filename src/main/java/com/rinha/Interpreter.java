package com.rinha;

import com.google.gson.JsonObject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    public Object runInterpreter(JsonObject mappedJson,  Map<String, Object> env){
// Using this print to test some entry
//        System.out.println( "Kind = "+ mappedJson.get("kind").getAsString());
        switch (mappedJson.get("kind").getAsString()){
            case "Str": {
                return mappedJson.get("value").getAsString();
            }

            case "Int": {
                return mappedJson.get("value").getAsInt();
            }

            case "Function": {
                return mappedJson;
            }

            case "Print": {
                Object printValue = runInterpreter(mappedJson.getAsJsonObject("value"), env);
                System.out.println(printValue);
                return printValue;
            }

            case "Let": {
                Object valueLet = runInterpreter(mappedJson.getAsJsonObject("value"), env);
                env.put(mappedJson.getAsJsonObject("name").get("text").getAsString(), valueLet);
                Map<String, Object> copyEnv = new HashMap<>(env);
                copyEnv.put(mappedJson.getAsJsonObject("name").get("text").getAsString(), valueLet);
                return runInterpreter(mappedJson.getAsJsonObject("next"), copyEnv);
            }

            case "Call": {
                //Fibonacci simple implementation
                if ("fib".equals(mappedJson.getAsJsonObject("callee").get("text").getAsString())){
                    BigInteger valueFibonacci = new BigInteger(String.valueOf(runInterpreter(mappedJson.getAsJsonArray("arguments").get(0).getAsJsonObject(), env)));

                    BigInteger a = BigInteger.ZERO, b = BigInteger.ONE;
                    for (int i = 0; i < valueFibonacci.intValue(); i++){
                        BigInteger next = a.add(b);
                        a = b;
                        b = next;
                    }
                    return a;
                }

                //Sum simple implementation
                if ("sum".equals(mappedJson.getAsJsonObject("callee").get("text").getAsString())){
                    BigInteger valueSum = new BigInteger(String.valueOf(runInterpreter(mappedJson.getAsJsonArray("arguments").get(0).getAsJsonObject(), env)));
                    int value = valueSum.intValue(),  result = 0;
                    for (int i = 0; i < valueSum.intValue(); i++, value--){
                        result += value;
                    }
                    return result;
                }
            }

            case "Binary": {
                Object firstValue = runInterpreter(mappedJson.get("lhs").getAsJsonObject(), env);
                Object secondValue = runInterpreter(mappedJson.get("rhs").getAsJsonObject(), env);

                if("Str".equals(mappedJson.getAsJsonObject("lhs").get("kind").getAsString())
                        || "Str".equals(mappedJson.getAsJsonObject("rhs").get("kind").getAsString())){
                    return firstValue.toString().concat(secondValue.toString());
                }

                if ("Add".equals(mappedJson.get("op").getAsString())){
                    return String.valueOf( Integer.parseInt(firstValue.toString()) + Integer.parseInt(secondValue.toString()));
                }

                if ("Sub".equals(mappedJson.get("op").getAsString())){
                    return String.valueOf( Integer.parseInt(firstValue.toString()) - Integer.parseInt(secondValue.toString()));
                }

                if ("Mul".equals(mappedJson.get("op").getAsString())){
                    return String.valueOf( Integer.parseInt(firstValue.toString()) * Integer.parseInt(secondValue.toString()));
                }

                if ("Div".equals(mappedJson.get("op").getAsString())){
                    return String.valueOf( Integer.parseInt(firstValue.toString()) / Integer.parseInt(secondValue.toString()));
                }

                if ("Rem".equals(mappedJson.get("op").getAsString())){
                    return String.valueOf( Integer.parseInt(firstValue.toString()) % Integer.parseInt(secondValue.toString()));
                }

                if ("Eq".equals(mappedJson.get("op").getAsString())){
                    return firstValue == secondValue ? true : false;
                }

                if ("Neq".equals(mappedJson.get("op").getAsString())){
                    return firstValue != secondValue ? true : false;
                }
            }
        }
        return null;
    }
}
