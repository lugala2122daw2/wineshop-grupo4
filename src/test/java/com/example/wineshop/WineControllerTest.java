package com.example.wineshop;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import com.example.wineshop.entity.*;
import com.example.wineshop.service.*;
import com.fasterxml.jackson.databind.*;

import org.springframework.security.test.context.support.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class WineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WineService wineService;

    @Autowired
    private ObjectMapper objectMapper;

    Winery winery = new Winery(3, "Vega Sicilia");
    Type type = new Type(2, "Tempranillo");
    Region region = new Region(3, "Ribera del Duero", "España");
    Wine wine = new Wine(1, "Deiu", "2010", 4.3f, 50, 78, "4", "3", winery, type, region);

    @Test
    @WithMockUser(username = "user", password = "password")
    void retrieveWine() throws Exception {
        when(wineService.findWine(wine.getId())).thenReturn(Optional.of(wine));
        ResultActions response = mockMvc.perform(get("/api/wine/{id}", wine.getId()));
        
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(wine.getId())))
                .andExpect(jsonPath("$.name",is(wine.getName())));
    }

    @Test
    void retrieveAllWine() throws Exception {
        List<Wine> wines = new ArrayList<>();
        wines.add(new Wine());
        when(wineService.findWines()).thenReturn(wines);

        ResultActions response = mockMvc.perform(get("/api/wine"));

        response.andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.size()",is(wines.size())));
    }

    @Test
    @WithMockUser
    void createWine() throws Exception{
        when(wineService.createWine(wine)).thenReturn(wine);

        ResultActions response = mockMvc.perform(post("/api/wine")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(wine)));

        response.andDo(print())
                .andExpect(status().isOk());
                /*.andExpect(jsonPath("$.name",is("Deiu")))
                .andExpect(jsonPath("$.year").value("2010"));*/
    }

    @Test
    @WithMockUser
    void updateWine() throws Exception{
        given(wineService.updateWine(1, wine)).willAnswer((invocation) -> invocation.getArguments());

        ResultActions response = mockMvc.perform(put("/api/wine/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wine)));

        response.andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deleteWine() throws Exception{
        willDoNothing().given(wineService).deleteWine(wine.getId());

        ResultActions response = mockMvc.perform(delete("/api/delete/wine/{id}",wine.getId()));
        
        response.andExpect(status().isOk()).andDo(print());
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
