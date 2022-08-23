package com.example.wineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
}
