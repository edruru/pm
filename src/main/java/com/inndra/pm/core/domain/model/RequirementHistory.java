package com.inndra.pm.core.domain.model;

/**
 * @author edwin.rubio
 * @version 1.0
 * @since 12-06-2014
 */

public class RequirementHistory {

    private Integer id;
    private Integer requirementId;
    private Integer statusId;
    private String text;
    private String date;


    /**
     * Injecting a Requirement History Object
     *
     * @param id            the autoincrement id in the table.
     * @param requirementId The requirement that this object is referencing to.
     * @param text          The specification, on what is currently being made to this requirement.
     * @param date          Timestamp given by the server on creation time.
     */


    public RequirementHistory(int id, int requirementId, String text, String date) {
        this.id = id;
        this.requirementId = requirementId;
        this.text = text;
        this.date = date;
    }

    public RequirementHistory() {

    }

    public RequirementHistory(Integer requirementId, Integer statusId, String text, String date) {
        this.requirementId = requirementId;
        this.statusId = statusId;
        this.text = text;
        this.date = date;
    }

    public RequirementHistory(Integer requirementId, Integer statusId, String text) {
        this.requirementId = requirementId;
        this.statusId = statusId;
        this.text = text;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
