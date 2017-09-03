package com.gm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by michael on 8/29/17.
 */
@Entity
public class Phone {
    @Id
    private String id;
    private String phone;
    private String vPhone;
    private Date bindTime;
    private Integer status;

    public String getvPhone() {
        return vPhone;
    }

    public void setvPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
}
