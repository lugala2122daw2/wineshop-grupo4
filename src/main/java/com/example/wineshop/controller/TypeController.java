package com.example.wineshop.controller;

import com.example.wineshop.entity.Type;
import com.example.wineshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/api/type/{id}")
    public Type retrieveType(@PathVariable int id) {
        return typeService.findOne(id);
    }

    @GetMapping("/api/type")
    public List<Type> getWines(){
        return typeService.findTypes();
    }

    @PostMapping("/api/type")
    public Type createNewType(@RequestBody Type type){
        typeService.createType(type);
        return type;
    }

    @PutMapping("/api/type/{id}")
    public Type updateType(@PathVariable int id, @RequestBody Type nType){
        return typeService.updateType(id, nType);
    }

    @DeleteMapping("/api/type/{id}")
    public String deleteType(@PathVariable int id){
        typeService.deleteType(id);
        return "Eliminado el tipo de vino con id: " + id;
    }

}
