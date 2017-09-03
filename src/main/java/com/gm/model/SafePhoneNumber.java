package com.gm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by michael on 9/3/17.
 */
@Entity
public class SafePhoneNumber {
    @Id
    private String id;
    private String number;
    private String bindPhone;
    private Date bindTime;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
