package com.example.wineshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.repository.WineRepository;

@Service
public class WineService {

    @Autowired
    WineRepository wineRepository;

    public Optional<Wine> findWine(int id){
        return wineRepository.findById(id);
    }

    public List<Wine> findWines(){
        return wineRepository.findAll();
    }
    
    public ResponseEntity<Wine> createWine(Wine wine){
        wineRepository.save(wine);
        return new ResponseEntity<>(wine, HttpStatus.OK);
    }

    public ResponseEntity<Wine> updateWine(int id, Wine nWine){
        return findWine(id)
            .map(wine -> {
                wine.setName(nWine.getName());
                wine.setYear(nWine.getYear());
                wine.setRating(nWine.getRating());
                wine.setNum_reviews(nWine.getNum_reviews());
                wine.setPrice(nWine.getPrice());
                wine.setBody(nWine.getBody());
                wine.setAcidity(nWine.getAcidity());
                
                wineRepository.save(wine);
                return new ResponseEntity<>(wine, HttpStatus.OK);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteWine(int id){
        wineRepository.deleteById(id);
    }

}
