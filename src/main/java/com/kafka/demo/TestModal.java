package com.kafka.demo;

import java.io.Serializable;

public class TestModal implements Serializable {


    private Integer id;
    private String text;

    @Override
    public String toString() {
        return "TestModal{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
