package com.example.wineshop;

import com.example.wineshop.entity.Region;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class Regioncontrollertestmvc {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void all() throws Exception {
        mockMvc.perform(get("/api/region/")
                .contentType("application/json")
        );
    }

    @Test
    @WithMockUser
    void one() throws Exception {
        mockMvc.perform(get("/api/region/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Toro")));
    }

    @Test
    @WithMockUser
    void CreateRegion() throws Exception {

        Region region = new Region();
        region.setName("Test");
        region.setCountry("Spain");
        mockMvc.perform(post("/api/region/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(region)))
                .andExpect(jsonPath("$.name", is("Test")));


    }

    @Test
    @WithMockUser
    void UpdateRegion() throws Exception {

        Region region = new Region();
        region.setId(1);
        region.setName("Test");
        region.setCountry("Spain");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(region);
        mockMvc.perform(put("/api/region/60")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.name", is("Test")));


    }

    @Test
    @WithMockUser
    void DeleteRegion() throws Exception {

        mockMvc.perform(delete("/api/region/2")
                        .contentType(MediaType.APPLICATION_JSON))
                //Expect 200 OK
                .andExpect(status().isOk());

    }

    //Unexisting region
    @Test
    @WithMockUser
    void FindRegionInvalidId() throws Exception {

        mockMvc.perform(get("/api/region/100")
                        .contentType(MediaType.APPLICATION_JSON))
                //Expect 404 Not Found
                .andExpect(status().isNotFound());

    }

    //Unexisting update ID
    @Test
    @WithMockUser
    void UpdateRegionInvalidId() throws Exception {

        Region region = new Region();
        region.setId(100);
        region.setName("Test");
        region.setCountry("Spain");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(region);
        mockMvc.perform(put("/api/region/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //Expect 404 Not Found
                .andExpect(status().isNotFound());

    }

    //Unexisting delete ID
    @Test
    @WithMockUser
    void DeleteRegionInvalidId() throws Exception {

        mockMvc.perform(delete("/api/region/100")
                        .contentType(MediaType.APPLICATION_JSON))
                //Expect 404 Not Found
                .andExpect(status().isNotFound());

    }


}
