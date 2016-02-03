package com.test.java.serial;

import java.io.Serializable;

/**
 * Created by 123 on 2016/2/1.
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1l;

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
