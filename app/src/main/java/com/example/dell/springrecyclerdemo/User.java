package com.example.dell.springrecyclerdemo;

import java.io.Serializable;

/**
 * Created by DELL on 2018/1/8.
 */

public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
