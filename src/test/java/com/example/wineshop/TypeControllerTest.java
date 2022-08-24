package com.example.wineshop;

import com.example.wineshop.entity.Type;
import com.example.wineshop.service.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Optional;

import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters=false)
public class TypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeService typeService;

    @Autowired
    private ObjectMapper objectmapper;


    @Test
    @WithMockUser
    public void testAll() throws Exception {
        mockMvc.perform(get("/api/type/"));
    }



    @Test
    @WithMockUser
    public void getOne() throws Exception{
        mockMvc.perform(get("/api/type/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Toro Red")));
    }

    @Test
    @WithMockUser
    public void oneNotFound() throws Exception {
        long testId = 1L;
        ResultActions response = mockMvc.perform(get("/api/type/{id}", testId));

        response.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    @WithMockUser
    public void createTest() throws Exception {

        Type type = new Type();
        type.setName("Bebida");
        type.setId(200);

        mockMvc.perform(post("/api/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectmapper.writeValueAsString(type)))
                .andExpect(jsonPath("$.id",is(200)))
                .andExpect(jsonPath("$.name", is("Bebida")));
    }


    @Test
    @WithMockUser
    public void updateTest() throws Exception {
        int typeId = 1;

        Type type = new Type();
        type.setName("Bebida");
        type.setId(200);
        Type updatedType = new Type();
        updatedType.setName("Bebiendo");
        updatedType.setId(200);

        given(typeService.findOne(typeId)).willReturn(Optional.of(type));
        given(typeService.updateType(any(Type.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/type/{id}", typeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectmapper.writeValueAsString(updatedType)));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(updatedType.getId())))
                .andExpect(jsonPath("$.name", is(updatedType.getName())));
    }

    @Test
    @WithMockUser
    public void updateErrorTest() throws Exception {
        int typeId = 1;
        Type type = new Type();
        type.setName("Bebida");
        type.setId(200);
        Type updatedType = new Type();
        updatedType.setName("Bebiendo");
        updatedType.setId(200);

        given(typeService.findOne(typeId)).willReturn(Optional.empty());
        given(typeService.updateType(any(Type.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/api/type/{id}", typeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectmapper.writeValueAsString(updatedType)));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @WithMockUser
    public void deleteTest() throws Exception {
        int typeId = 1;

        willDoNothing().given(typeService).deleteType(typeId);

        ResultActions response = mockMvc.perform(delete("/api/type/{id}", typeId));

        response.andExpect(status().isOk()).andDo(print());
    }





}
