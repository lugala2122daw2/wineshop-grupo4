package com.example.wineshop.service;

import com.example.wineshop.entity.Winery;
import com.example.wineshop.repository.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineryService {
    @Autowired
    private WineryRepository wineryRepository;

    public Optional<Winery> findOne(Integer id){
        return wineryRepository.findById(id);
    }

    public List<Winery> findAll(){
        return wineryRepository.findAll();
    }

    public Winery save(Winery winery) {
        return wineryRepository.save(winery);
    }

    public void delete(int id){
        wineryRepository.deleteById(id);

    }
}
