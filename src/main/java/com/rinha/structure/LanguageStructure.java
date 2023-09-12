package com.rinha.structure;

import com.rinha.structure.subs.Expression;
import com.rinha.structure.subs.Location;

public class LanguageStructure {
    private String name;
    private Expression expression;
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
