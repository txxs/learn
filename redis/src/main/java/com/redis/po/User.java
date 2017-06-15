package com.redis.po;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/16
 * @Time:11:45
 */
public class User implements Serializable{


    private static final long serialVersionUID = -4735764941908502293L;

    private int id;

    private String name;

    private String addressName;

    private List<String> phoneNum;

    public User() {}

    public User(int id, String name, String addressName) {
        this.id = id;
        this.name = name;
        this.addressName = addressName;
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

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<String> getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(List<String> phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressName='" + addressName + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }
}
