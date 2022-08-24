package com.example.wineshop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.wineshop.entity.Region;
import com.example.wineshop.entity.Wine;
import com.example.wineshop.entity.Type;
import com.example.wineshop.entity.Winery;

public class WineTests {
    
    @Test
    public void testWine() {
        Winery winery = new Winery(1, "Vega Sicilia");
        Type type = new Type(1, "Tempranillo");
        Region region = new Region(1, "Ribera de Duero", "España");
        Wine wine = new Wine(1, "Tinto", "2010", 4.3f, 50, 78, "4", "3", winery, type, region);
        assertNotNull(wine);
        assertEquals("Tinto", wine.getName());
        assertEquals("2010", wine.getYear());
        assertEquals("Tempranillo", wine.getType().getName());
        assertEquals("Vega Sicilia", wine.getWinery().getName());
        assertEquals("Ribera de Duero", wine.getRegion().getName());
    }


}
