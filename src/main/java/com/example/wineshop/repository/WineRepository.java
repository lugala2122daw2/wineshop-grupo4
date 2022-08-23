package com.example.wineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wineshop.entity.Wine;

@Repository
public interface WineRepository extends JpaRepository<Wine, Integer>{}
