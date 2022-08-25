package com.example.wineshop.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Wine> getWine(@PathVariable int id) {
        return wineService.findWine(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/wine")
    public List<Wine> getWines(){
        return wineService.findWines();
    }

    @PostMapping("/api/wine")
    public ResponseEntity<Wine> createNewWine(@RequestBody Wine wine){
        return new ResponseEntity<>(wineService.createWine(wine), HttpStatus.OK);
    }

    @PutMapping("/api/wine/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable int id, @RequestBody Wine nWine){
        return wineService.updateWine(id, nWine);
    }

    @DeleteMapping("/api/wine/{id}")
    public ResponseEntity<String> deleteWine(@PathVariable int id){
        wineService.deleteWine(id);
        return new ResponseEntity<String>("Se ha eliminado el vino con id: " + id, HttpStatus.OK);
    }

}
