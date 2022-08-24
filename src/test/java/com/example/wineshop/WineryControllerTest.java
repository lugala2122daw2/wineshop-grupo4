package com.example.wineshop;

import com.example.wineshop.controller.WineryController;
import com.example.wineshop.entity.Winery;
import com.example.wineshop.service.WineryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class WineryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WineryService wineryService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @WithMockUser
    void retrieveWinery() throws Exception {

        Integer id=1;
        Winery winery = new Winery();
        winery.setId(id);
        winery.setName("Teso La Monja");

        given(wineryService.findOne(id)).willReturn(Optional.of(winery));
        ResultActions response = mockMvc.perform(get("/api/winery/{id}", id));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(winery.getId())))
                .andExpect(jsonPath("$.name",is(winery.getName())));

    }

    @Test
    @WithMockUser
    void retireveAllWinery() throws Exception {

        List<Winery> listOfWinery = new ArrayList<>();
        listOfWinery.add(new Winery());
        given(wineryService.findAll()).willReturn(listOfWinery);

        ResultActions response = mockMvc.perform(get("/api/winery"));

        response.andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.size()",is(listOfWinery.size())));

    }

    @Test
    @WithMockUser
    void createWinery() throws Exception {
        Winery winery = new Winery();
        winery.setName("Test");
        given(wineryService.save(any(Winery.class))).willAnswer((invocation)->invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/winery")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(winery)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Test")));
    }

    @Test
    @WithMockUser
    void updateWinery() throws Exception {
        Winery savedWinery = new Winery();
        savedWinery.setName("Test");

        Winery updatedWinery = new Winery();
        updatedWinery.setName("Test1");

        given(wineryService.findOne(1)).willReturn(Optional.of(savedWinery));
        given(wineryService.save(updatedWinery)).willAnswer((invocation)->invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/winery/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWinery)));

        response.andExpect(status().isOk());
    }

    @Test
    void deleteWinery() throws Exception {
        Integer wineryId=1;
        willDoNothing().given(wineryService).delete(wineryId);

        ResultActions response = mockMvc.perform(delete("/api/winery/{id}",wineryId));
        response.andExpect(status().isOk()).andDo(print());
    }

    @Test
    void invalid_Id_GetById() throws Exception {
        Integer invalid_id=-1;
        given(wineryService.findOne(invalid_id)).willReturn(Optional.empty());
        ResultActions response = mockMvc.perform(get("/api/winery/{id}", invalid_id));
        response.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    void invalid_Id_Update() throws Exception{
        Integer invalid_id=-1;
        Winery savedWinery = new Winery();
        savedWinery.setName("Test");

        Winery updatedWinery = new Winery();
        updatedWinery.setName("Test1");

        given(wineryService.findOne(invalid_id)).willReturn(Optional.empty());
        given(wineryService.save(updatedWinery)).willAnswer((invocation)->invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/winery/{id}", invalid_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWinery)));

        response.andExpect(status().isNotFound())
                .andDo(print());
    }

}