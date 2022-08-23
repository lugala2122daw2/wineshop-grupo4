package com.example.wineshop.controller;

import com.example.wineshop.entity.Winery;
import com.example.wineshop.service.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WineryController {

    @Autowired
    private WineryService wineryService;

    @GetMapping("/api/winery/{id}")
    public Winery retrieveWinery(@PathVariable int id){
        Winery winery= wineryService.findOne(id);


        return winery;
    }

    @GetMapping("/api/winery")
    public List<Winery> retireveAllWinery(){
        return wineryService.findAll();
    }



}
