package com.example.wineshop.recommends;


import com.example.wineshop.entity.Wine;
import com.example.wineshop.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RecommendsController {
    @Autowired
    private WineService repository;

    @GetMapping("/api/recommend/best")
    //Find the best wines by rating
    public List<Wine> getBestWines(@RequestParam(required = false, defaultValue = "10") Integer top) {

        List<Wine> wines = repository.findTopNByRating(top);
        return wines;
    }

    @GetMapping("/api/recommend/expensive")
    public List<Wine> getExpensiveWines(@RequestParam(required = false, defaultValue = "10") Integer top) {

        List<Wine> wines = repository.findTopNByExpensive(top);
        return wines;
    }

    @GetMapping("/api/recommend/bang")
    public List<Wine> getBangWines(@RequestParam(required = false, defaultValue = "10") Integer top) {

        List<Wine> wines = repository.findTopNByBang(top);
        return wines;
    }

    @GetMapping("/api/recommend/vintage")
    public List<Wine> getVintageWines(@RequestParam(required = false, defaultValue = "10") Integer top) {
        //Best vintage: Years with best rated wines
        List<Wine> wines = repository.findTopNByVintage(top);
        return wines;
    }



}
