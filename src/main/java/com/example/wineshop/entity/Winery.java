package com.example.wineshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Winery {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Winery() {
    }

    public Winery(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Winery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
