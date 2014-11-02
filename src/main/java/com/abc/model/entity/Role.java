package com.abc.model.entity;

/**
 * Created by alexandr koller on 31/10/2014.
 */
public abstract class Role {

    private String name;

    protected Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
