package com.example.wineshop;

import com.example.wineshop.controller.WineryController;
import com.example.wineshop.entity.Winery;
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
    private WineryController wineryController;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    @WithMockUser
    void retrieveWinery() throws Exception {

        Integer id=1;
        Winery winery = new Winery();
        winery.setId(id);
        winery.setName("Teso La Monja");

        given(wineryController.retrieveWinery(id)).willReturn(winery);
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
        given(wineryController.retireveAllWinery()).willReturn(listOfWinery);

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
        given(wineryController.createWinery(any(Winery.class))).willAnswer((invocation)->invocation.getArgument(0));

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
        given(wineryController.retrieveWinery(1)).willReturn(savedWinery);
        given(wineryController.updateWinery(1,updatedWinery)).willAnswer((invocation)->invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/winery/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWinery)));

        response.andExpect(status().isOk());
    }

    @Test
    void deleteWinery() throws Exception {
        Integer wineryId=1;
        given(wineryController.deleteWinery(wineryId)).willReturn(new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK));
        ResultActions response = mockMvc.perform(delete("/api/winery/{id}",wineryId));
        response.andExpect(status().isOk()).andDo(print());
    }
}