package com.example.wineshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.wineshop.entity.Winery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.wineshop.entity.Region;
import com.example.wineshop.entity.Type;
import com.example.wineshop.entity.Wine;

import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class WineTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Winery winery = new Winery(1, "Vega Sicilia");
    private Type type = new Type(1, "Tempranillo");
    private Region region = new Region(1, "Ribera de Duero", "España");
    private Wine wine = new Wine(1, "Deiu", "2010", 4.3f, 50, 78, "4", "3", winery, type, region);

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/wine/")
            .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    void getOne() throws Exception {
        mockMvc.perform(get("/api/wine/1/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser
    void createWine() throws Exception{
        mockMvc.perform(post("/api/wine/")
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(wine)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Deiu"))
            .andExpect(jsonPath("$.year").value("2010"));
    }

    @Test
    @WithMockUser
    void updateWine() throws Exception{
        mockMvc.perform(put("/api/wine/13/")
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(wine)))
            .andExpect(jsonPath("$.id").value(13))
            .andExpect(jsonPath("$.name").value("Deiu"))
            .andExpect(jsonPath("$.year").value("2010"));
    }

    @Test
    @WithMockUser
    void deleteWine() throws Exception{
        mockMvc.perform(delete("/api/wine/18/") //Change id manually before running test...
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void testWine() {
        assertNotNull(wine);
        assertEquals("Deiu", wine.getName());
        assertEquals("2010", wine.getYear());
        assertEquals("Tempranillo", wine.getType().getName());
        assertEquals("Vega Sicilia", wine.getWinery().getName());
        assertEquals("Ribera de Duero", wine.getRegion().getName());
    }

}
