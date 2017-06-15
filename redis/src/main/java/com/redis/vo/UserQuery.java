package com.redis.vo;

/**
 * @Author:jianghuimin
 * @Date: 2017/5/17
 * @Time:15:15
 */
public class UserQuery {

    private int id;

    private String name;

    private String addressName;

    public UserQuery(){}

    public UserQuery(int id, String name, String addressName) {
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

    @Override
    public String toString() {
        return "UserQuery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressName='" + addressName + '\'' +
                '}';
    }
}
