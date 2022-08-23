package com.example.wineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wineshop.entity.Region;

public interface RegionRepository extends JpaRepository<Region,Integer> {

    //Id, name & Country

    
}
