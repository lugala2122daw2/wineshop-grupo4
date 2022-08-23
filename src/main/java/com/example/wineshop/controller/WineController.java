package com.example.wineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.service.WineService;
import org.springframework.web.bind.annotation.*;


@RestController
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("/api/wine/{id}")
    public Wine getWine(@PathVariable int id){
        return wineService.findWine(id);
    }

    @GetMapping("/api/wine")
    public List<Wine> getWines(){
        return wineService.findWines();
    }

    @PostMapping("/api/wine/")
    public Wine createNewWine(@RequestBody Wine wine){
        wineService.createWine(wine);
        return wine;
    }

    @PutMapping("/api/wine/{id}")
    public Wine updateWine(@PathVariable int id, @RequestBody Wine nWine){
        return wineService.updateWine(id, nWine);
    }

    @DeleteMapping("/api/wine/{id}")
    public String deleteWine(@PathVariable int id){
        wineService.deleteWine(id);
        return "Se ha eliminado el vino con id: " + id;
    }

}
