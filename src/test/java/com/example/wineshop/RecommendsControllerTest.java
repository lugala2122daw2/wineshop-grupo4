package com.example.wineshop;

import com.example.wineshop.entity.Wine;
import com.example.wineshop.service.WineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class RecommendsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WineService wineService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBestWines() throws Exception {
        List<Wine> listOfBestWine = new ArrayList<>();
        listOfBestWine.add(new Wine());
        Integer top = 10;
        given(wineService.findTopNByRating(top)).willReturn(listOfBestWine);

        ResultActions response = mockMvc.perform(get("/api/recommend/best"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listOfBestWine.size())));
    }

    @Test
    void getExpensiveWines() throws Exception {
        List<Wine> listOfMostExpensiveWines = new ArrayList<>();
        listOfMostExpensiveWines.add(new Wine());
        Integer top = 10;
        given(wineService.findTopNByExpensive(top)).willReturn(listOfMostExpensiveWines);

        ResultActions response = mockMvc.perform(get("/api/recommend/expensive"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listOfMostExpensiveWines.size())));
    }

    @Test
    void getBangWines() throws Exception {
        List<Wine> listOfBangWines = new ArrayList<>();
        listOfBangWines.add(new Wine());
        Integer top = 10;
        given(wineService.findTopNByBang(top)).willReturn(listOfBangWines);

        ResultActions response = mockMvc.perform(get("/api/recommend/bang"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listOfBangWines.size())));
    }

    @Test
    void getVintageWines() throws Exception {
        List<Wine> listOfVintageWines = new ArrayList<>();
        listOfVintageWines.add(new Wine());
        Integer top = 10;
        given(wineService.findTopNByVintage(top)).willReturn(listOfVintageWines);

        ResultActions response = mockMvc.perform(get("/api/recommend/vintage"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(listOfVintageWines.size())));
    }
}