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
    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;
    private String email;
    private Integer status;
    private Date time;
    private String name;
    private Integer sex;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Code> codes;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Code> getCodes() {
        return codes;
    }

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
