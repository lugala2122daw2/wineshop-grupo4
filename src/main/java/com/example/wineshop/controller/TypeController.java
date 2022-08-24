package com.example.wineshop.controller;

import com.example.wineshop.entity.Type;
import com.example.wineshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/api/type/{id}")
    public ResponseEntity<Type> retrieveType(@PathVariable int id) {
        return typeService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/type")
    public List<Type> getTypes(){
        return typeService.findTypes();
    }

    @PostMapping("/api/type")
    public Type createNewType(@RequestBody Type type){
        typeService.createType(type);
        return type;
    }

    @PutMapping("/api/type/{id}")
    public ResponseEntity<Type> updateType(@PathVariable int id, @RequestBody Type nType){
        return typeService.findOne(id)
                .map(savedType -> {

                    savedType.setName(nType.getName());
                    savedType.setId(nType.getId());

                    Type updatedType = typeService.updateType(savedType);
                    return new ResponseEntity<>(updatedType, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/delete/type/{id}")
    public ResponseEntity<String> deleteType(@PathVariable int id){
        typeService.deleteType(id);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }

}
