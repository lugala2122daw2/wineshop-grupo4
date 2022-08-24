package com.example.wineshop.controller;

import com.example.wineshop.entity.Winery;
import com.example.wineshop.service.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class WineryController {

    @Autowired
    private WineryService wineryService;

    //Read
    @GetMapping("/api/winery/{id}")
    public ResponseEntity<Winery> retrieveWinery(@PathVariable int id){



        return wineryService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Read
    @GetMapping("/api/winery")
    public List<Winery> retireveAllWinery(){
        return wineryService.findAll();
    }

    //Create
    @PostMapping("/api/winery")
    public Winery createWinery(@RequestBody Winery winery){
        return wineryService.save(winery);
    }

    //Update
    @PutMapping("/api/winery/{id}")
    public ResponseEntity<Winery> updateWinery(@PathVariable int id, @RequestBody Winery winery){

        return wineryService.findOne(id)
                .map(savedWinery -> {

                    savedWinery.setName(winery.getName());
                    savedWinery.setId(winery.getId());

                    Winery updatedWinery = wineryService.save(savedWinery);
                    return new ResponseEntity<>(updatedWinery, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete
    @DeleteMapping("/api/winery/{id}")
    public ResponseEntity<String> deleteWinery(@PathVariable int id){
        wineryService.delete(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }



}
