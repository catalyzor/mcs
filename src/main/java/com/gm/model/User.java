package com.gm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by michael on 8/26/17.
 */
@Entity
public class User {
    @Id
    private String id;
    private String phone;
    private String email;
    private Integer status;
    private Date time;
    private String name;
    private Integer sex;

    @OneToMany
    private List<Code> codes;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
