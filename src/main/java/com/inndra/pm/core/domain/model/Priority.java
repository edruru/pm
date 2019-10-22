package com.inndra.pm.core.domain.model;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public class Priority {
    private int id;
    private String type;

    public Priority(){
        this.id = -1;
        this.type = "default";
    }

    public Priority(Priority p){
        this.id = p.getId();
        this.type = p.getType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Priority [" +
                "id=" + id +
                ", type='" + type + '\'' +
                ']';
    }
}
