package com.example.wineshop.service;

import com.example.wineshop.entity.Region;
import com.example.wineshop.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public Region findOne(Integer id) {
        return regionRepository.findById(id).get();
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

  //Save into the mysql
    public Region save(Region region) {
        return regionRepository.save(region);
    }
    public void delete(Integer id) {
        regionRepository.deleteById(id);
    }

    public Region update(int id, Region nregion) {
        Region region = findOne(id);
        region.setName(nregion.getName());
        region.setCountry(nregion.getCountry());
        return regionRepository.save(region);
    }



}
