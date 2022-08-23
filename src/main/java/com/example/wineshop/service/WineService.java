package com.example.wineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.stereotype.Service;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.repository.WineRepository;

@Service
public class WineService {

    @Autowired
    WineRepository wineRepository;

    public Wine findWine(int id){
        return wineRepository.getReferenceById(id);
    }

    public List<Wine> findWines(){
        return wineRepository.findAll();
    }
    
    public Wine createWine(Wine wine){
        return wineRepository.save(wine);
    }

    public Wine updateWine(int id, Wine nWine){
        Wine wine = findWine(id);
        wine.setName(nWine.getName());
        wine.setYear(nWine.getYear());
        wine.setRating(nWine.getRating());
        wine.setNum_reviews(nWine.getNum_reviews());
        wine.setPrice(nWine.getPrice());
        wine.setBody(nWine.getBody());
        wine.setAcidity(nWine.getAcidity());
        return wineRepository.save(wine);
    }

    public void deleteWine(int id){
        wineRepository.deleteById(id);
    }

}
