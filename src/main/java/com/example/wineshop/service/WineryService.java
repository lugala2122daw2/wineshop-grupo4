package com.example.wineshop.service;

import com.example.wineshop.entity.Winery;
import com.example.wineshop.repository.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineryService {
    @Autowired
    private WineryRepository wineryRepository;

    public Winery findOne(Integer id){
        return wineryRepository.findById(id).get();
    }

    public List<Winery> findAll(){
        return wineryRepository.findAll();
    }
}
