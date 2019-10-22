package com.inndra.pm.core.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public class User {

    private int id;

    @NotEmpty
    private String name;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
