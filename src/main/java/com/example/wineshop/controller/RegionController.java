package com.example.wineshop.controller;

import com.example.wineshop.entity.Region;
import com.example.wineshop.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/api/region/{id}") //Funciona
    public Region retrieveRegion(@PathVariable int id){
        Region region = regionService.findOne(id);

        return region;
    }
    @GetMapping("/api/region") //Funciona
    public List<Region> retrieveAllRegions(){
        return regionService.findAll();
    }
    @PostMapping("/api/region") //Funciona
    public Region saveRegion(Region region){
        return regionService.save(region);
    }


    @DeleteMapping("/api/region/{id}") //Funciona
    public void deleteRegion(@PathVariable int id) {
        regionService.delete(id);
     }

     @PutMapping("/api/region/{id}") //Funciona
    public Region updateRegion(@PathVariable int id, @RequestBody Region region){
        return regionService.update(id, region);
        }
}
