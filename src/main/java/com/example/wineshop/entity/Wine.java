package com.example.wineshop.entity;

import javax.persistence.*;

@Entity
public class Wine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    String name;
    String year;
    float rating;
    int num_reviews;
    float price;
    String body;
    String acidity;

    public Wine() {
    }

    public Wine(int id, String name, String year, float rating, int num_reviews, float price, String body, String acidity) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }
    
}
