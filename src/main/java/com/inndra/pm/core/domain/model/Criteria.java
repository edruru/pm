package com.inndra.pm.core.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Created by edwin.rubio on 19/06/2014.
 */
public class Criteria {

    @Min(value = 1, message = "Please enter a valid option")
    private Integer type;

    @NotEmpty(message = "Please enter a valid query")
    private String text;
    private String beginDate;
    private String finishDate;

    public Criteria() {

        this.beginDate = null;
        this.finishDate = null;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {

        if (!beginDate.isEmpty()) {
            this.beginDate = beginDate;
        }
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {

        if (!beginDate.isEmpty()) {
            this.finishDate = finishDate;
        }
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                '}';
    }
}


