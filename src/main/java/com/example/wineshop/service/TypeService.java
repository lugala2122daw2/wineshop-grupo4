package com.example.wineshop.service;

import com.example.wineshop.entity.Type;
import com.example.wineshop.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type findOne(Integer id){
        return typeRepository.findById(id).get();
    }

    public List<Type> findTypes(){
        return typeRepository.findAll();
    }

    public Type createType(Type type){
        return typeRepository.save(type);
    }

    public Type updateType(int id, Type nType){

        Type type = findOne(id);
        type.setName(nType.getName());
        return typeRepository.save(type);
    }

    public void deleteType(int id){
        typeRepository.deleteById(id);
    }


}
