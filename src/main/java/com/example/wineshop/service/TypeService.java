package com.example.wineshop.service;

import com.example.wineshop.entity.Type;
import com.example.wineshop.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Optional<Type> findOne(Integer id){
        return typeRepository.findById(id);
    }

    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    public Type createType(Type type){
        return typeRepository.save(type);
    }

    public Type updateType(Type updatedType){

        return typeRepository.save(updatedType);
    }

    public void deleteType(int id){
        typeRepository.deleteById(id);
    }


}
