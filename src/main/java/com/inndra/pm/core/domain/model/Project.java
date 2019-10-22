package com.inndra.pm.core.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public class Project {
    private int id;

    @NotEmpty(message = "Please enter a name")
    private String name;
    private String description;

    public Project() {
        this.name = "";
        this.description = "";
    }

    public Project(Project p) {
        this.id = p.getId();
        this.description = p.getDescription();
        this.name = p.getName();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ']';
    }
}
