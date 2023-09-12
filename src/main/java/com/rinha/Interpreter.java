package com.rinha;

import com.rinha.structure.LanguageStructure;

public class Interpreter {

    public void runInterpreter(LanguageStructure mappedLanguage){
        switch (mappedLanguage.getExpression().getKind()){
            case "Print":
                System.out.println(mappedLanguage.getExpression().getValue().getValue());
        }
    }
}
