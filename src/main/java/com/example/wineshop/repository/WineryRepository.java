package com.example.wineshop.repository;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.entity.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery,Integer> {

}
