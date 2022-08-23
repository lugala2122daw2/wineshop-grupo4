package com.example.wineshop.service;

import com.example.wineshop.entity.Winery;
import com.example.wineshop.repository.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Winery save(Winery winery) {
        return wineryRepository.save(winery);
    }

    public Map<String,Boolean> delete(int id){
        wineryRepository.deleteById(id);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
