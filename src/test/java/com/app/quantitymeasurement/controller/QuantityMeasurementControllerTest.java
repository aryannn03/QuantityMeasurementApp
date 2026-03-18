package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔥 TEST ADD API
    @Test
    void testAdd() throws Exception {

        QuantityDTO q1=new QuantityDTO(10.0,"FEET","LENGTH");
        QuantityDTO q2=new QuantityDTO(120.0,"INCH","LENGTH");

        QuantityDTO result=new QuantityDTO(20.0,"FEET","LENGTH");

        Mockito.when(service.add(Mockito.any(),Mockito.any()))
                .thenReturn(result);

        QuantityDTO[] input={q1,q2};

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(20.0))
                .andExpect(jsonPath("$.unit").value("FEET"));
    }

    // 🔥 TEST COMPARE API
    @Test
    void testCompare() throws Exception {

        Mockito.when(service.compare(Mockito.any(),Mockito.any()))
                .thenReturn(true);

        QuantityDTO[] input={
                new QuantityDTO(10.0,"FEET","LENGTH"),
                new QuantityDTO(120.0,"INCH","LENGTH")
        };

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    // 🔥 TEST CONVERT API
    @Test
    void testConvert() throws Exception {

        QuantityDTO input=new QuantityDTO(10.0,"FEET","LENGTH");
        QuantityDTO result=new QuantityDTO(120.0,"INCH","LENGTH");

        Mockito.when(service.convert(Mockito.any(),Mockito.any()))
                .thenReturn(result);

        mockMvc.perform(post("/api/v1/quantities/convert?targetUnit=INCH")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(120.0))
                .andExpect(jsonPath("$.unit").value("INCH"));
    }
}