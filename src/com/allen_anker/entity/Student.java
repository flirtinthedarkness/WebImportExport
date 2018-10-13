package com.allen_anker.entity;

import java.util.Date;

public class Student {

    private String name;
    private Integer age;
    private Date time;

    public Student() {
    }

    public Student(String name, Integer age, Date time) {
        this.name = name;
        this.age = age;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
