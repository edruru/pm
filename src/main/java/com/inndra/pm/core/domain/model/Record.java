package com.inndra.pm.core.domain.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Date;

//import org.springframework.stereotype..Component;

//import javax.validation.constraints.;

/**
 * Created by edwin.rubio on 6/2/2014.
 */

public class Record{

    //@NotNull
    private Integer id;

    @Min(value = 1, message = "Please enter a valid user")
    private Integer userId;

    @NotEmpty(message = "Please enter a description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;

    public Record() {

        beginDate = null;
        finishDate = null;
    }

    public Record(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {

        if (beginDate != null)
        {
            this.beginDate = beginDate;
        }
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {

        if (finishDate != null)
        {
            this.finishDate = finishDate;
        }
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", user=" + userId +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", beginDate=" + beginDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
