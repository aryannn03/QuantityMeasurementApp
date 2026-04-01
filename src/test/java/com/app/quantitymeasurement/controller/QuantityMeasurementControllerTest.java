package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
@DisplayName("QuantityMeasurementController Integration Tests")
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IQuantityMeasurementService service;

    // ─── Helpers ──────────────────────────────────────────────
    private QuantityDTO dto(double value, String unit, String type) {
        return new QuantityDTO(value, unit, type);
    }

    private String json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    // ─────────────────────────────────────────────────────────
    //  COMPARE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /compare — guest user, returns true")
    void compare_guestUser_returnsTrue() throws Exception {
        when(service.compare(any(), any(), isNull())).thenReturn(true);

        QuantityDTO[] payload = {
            dto(1.0, "FEET", "LENGTH"),
            dto(12.0, "INCH", "LENGTH")
        };

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("POST /compare — logged in user, returns false")
    @WithMockUser(username = "test@example.com")
    void compare_loggedInUser_returnsFalse() throws Exception {
        when(service.compare(any(), any(), eq("test@example.com"))).thenReturn(false);

        QuantityDTO[] payload = {
            dto(1.0, "FEET", "LENGTH"),
            dto(11.0, "INCH", "LENGTH")
        };

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("POST /compare — missing value field returns 400")
    void compare_missingValue_returns400() throws Exception {
        // value is null → @NotNull triggers
        String payload = "[{\"unit\":\"FEET\",\"measurementType\":\"LENGTH\"},{\"value\":12,\"unit\":\"INCH\",\"measurementType\":\"LENGTH\"}]";

        mockMvc.perform(post("/api/v1/quantities/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isBadRequest());
    }

    // ─────────────────────────────────────────────────────────
    //  CONVERT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /convert — guest user, 1 FEET to INCH")
    void convert_guestUser_correctResult() throws Exception {
        QuantityDTO resultDTO = dto(12.0, "INCH", "LENGTH");
        when(service.convert(any(), eq("INCH"), isNull())).thenReturn(resultDTO);

        mockMvc.perform(post("/api/v1/quantities/convert?targetUnit=INCH")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(dto(1.0, "FEET", "LENGTH"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(12.0))
                .andExpect(jsonPath("$.unit").value("INCH"));
    }

    @Test
    @DisplayName("POST /convert — missing targetUnit param returns 400")
    void convert_missingTargetUnit_returns400() throws Exception {
        mockMvc.perform(post("/api/v1/quantities/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(dto(1.0, "FEET", "LENGTH"))))
                .andExpect(status().isBadRequest());
    }

    // ─────────────────────────────────────────────────────────
    //  ADD
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /add — guest user, 5 GRAM + 1 KILOGRAM")
    void add_guestUser_correctResult() throws Exception {
        QuantityDTO resultDTO = dto(1005.0, "GRAM", "WEIGHT");
        when(service.add(any(), any(), isNull())).thenReturn(resultDTO);

        QuantityDTO[] payload = {
            dto(5.0, "GRAM", "WEIGHT"),
            dto(1.0, "KILOGRAM", "WEIGHT")
        };

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1005.0));
    }

    @Test
    @DisplayName("POST /add — logged in user saves history")
    @WithMockUser(username = "test@example.com")
    void add_loggedInUser_correctResult() throws Exception {
        QuantityDTO resultDTO = dto(1005.0, "GRAM", "WEIGHT");
        when(service.add(any(), any(), eq("test@example.com"))).thenReturn(resultDTO);

        QuantityDTO[] payload = {
            dto(5.0, "GRAM", "WEIGHT"),
            dto(1.0, "KILOGRAM", "WEIGHT")
        };

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1005.0));
    }

    // ─────────────────────────────────────────────────────────
    //  SUBTRACT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /subtract — guest user, 1 KILOGRAM - 500 GRAM")
    void subtract_guestUser_correctResult() throws Exception {
        QuantityDTO resultDTO = dto(500.0, "GRAM", "WEIGHT");
        when(service.subtract(any(), any(), isNull())).thenReturn(resultDTO);

        QuantityDTO[] payload = {
            dto(1.0, "KILOGRAM", "WEIGHT"),
            dto(500.0, "GRAM", "WEIGHT")
        };

        mockMvc.perform(post("/api/v1/quantities/subtract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(500.0));
    }

    // ─────────────────────────────────────────────────────────
    //  DIVIDE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /divide — guest user, 10 GRAM / 2 GRAM = 5.0")
    void divide_guestUser_correctResult() throws Exception {
        when(service.divide(any(), any(), isNull())).thenReturn(5.0);

        QuantityDTO[] payload = {
            dto(10.0, "GRAM", "WEIGHT"),
            dto(2.0, "GRAM", "WEIGHT")
        };

        mockMvc.perform(post("/api/v1/quantities/divide")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(payload)))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    // ─────────────────────────────────────────────────────────
    //  HISTORY (requires auth)
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /history/ALL — unauthenticated returns 401")
    void getHistory_unauthenticated_returns401() throws Exception {
        mockMvc.perform(get("/api/v1/quantities/history/ALL"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /history/ALL — authenticated returns list")
    @WithMockUser(username = "test@example.com")
    void getHistory_authenticated_returnsList() throws Exception {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "1.0 GRAM", "2.0 GRAM", "ADD", "3.0 GRAM", "test@example.com");
        when(service.getHistory("ALL", "test@example.com")).thenReturn(List.of(entity));

        mockMvc.perform(get("/api/v1/quantities/history/ALL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].operation").value("ADD"));
    }

    @Test
    @DisplayName("GET /history/ADD — authenticated returns filtered list")
    @WithMockUser(username = "test@example.com")
    void getHistory_filteredByOperation_returnsList() throws Exception {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "5.0 GRAM", "1.0 KILOGRAM", "ADD", "1005.0 GRAM", "test@example.com");
        when(service.getHistory("ADD", "test@example.com")).thenReturn(List.of(entity));

        mockMvc.perform(get("/api/v1/quantities/history/ADD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    // ─────────────────────────────────────────────────────────
    //  COUNT (requires auth)
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /count/ADD — unauthenticated returns 401")
    void getCount_unauthenticated_returns401() throws Exception {
        mockMvc.perform(get("/api/v1/quantities/count/ADD"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /count/ADD — authenticated returns count")
    @WithMockUser(username = "test@example.com")
    void getCount_authenticated_returnsCount() throws Exception {
        when(service.getCount("ADD", "test@example.com")).thenReturn(7L);

        mockMvc.perform(get("/api/v1/quantities/count/ADD"))
                .andExpect(status().isOk())
                .andExpect(content().string("7"));
    }
}
