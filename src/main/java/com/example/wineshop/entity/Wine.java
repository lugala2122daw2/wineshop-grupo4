package com.example.wineshop.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="winery_id")
    Winery winery;


//Integer type_id;

    @ManyToOne
    @JoinColumn(name="region_id")
    Region region;

    public Wine() {

    }

    public Wine(int id, String name, String year, float rating, int num_reviews, float price, String body, String acidity, Winery winery, Region region) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.winery = winery;
        this.region = region;
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

    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", num_reviews=" + num_reviews +
                ", price=" + price +
                ", body='" + body + '\'' +
                ", acidity='" + acidity + '\'' +
                ", winery=" + winery +
                ", region=" + region +
                '}';
    }
}
