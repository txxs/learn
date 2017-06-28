package com.hui.readwrite.po;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/28
 * @Time:11:21
 */
public class Count {

    private int id;
    private int num;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Count{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
