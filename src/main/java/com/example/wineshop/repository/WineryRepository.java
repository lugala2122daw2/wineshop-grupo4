package com.example.wineshop.repository;

import com.example.wineshop.entity.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineryRepository extends JpaRepository<Winery,Integer> {
}
