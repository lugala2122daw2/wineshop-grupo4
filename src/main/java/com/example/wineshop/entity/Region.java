package com.example.wineshop.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "region")
public class Region {
    //Integer id, String name, String country

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String country;

    public Region() {
    }

    public Region(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Region{" + "id=" + id + ", name=" + name + ", country=" + country + '}';
    }
}
