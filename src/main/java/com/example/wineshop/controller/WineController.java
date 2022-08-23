package com.example.wineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.service.WineService;

@RestController
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/api/wine/{id}")
    public Wine getWine(@PathVariable int id){
        return wineService.findWine(id);
    }

    @GetMapping("/api/wine")
    public List<Wine> getWine(){
        return wineService.findWines();
    }
    
}
