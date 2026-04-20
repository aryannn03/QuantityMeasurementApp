package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuantityMeasurementServiceImpl Tests")
public class QuantityMeasurementServiceImplTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    private static final String USER_EMAIL = "test@example.com";
    private static final double DELTA = 0.0001;

    // ─── DTO Helpers ──────────────────────────────────────────
    private QuantityDTO lengthDTO(double value, String unit) {
        return new QuantityDTO(value, unit, "LENGTH");
    }

    private QuantityDTO weightDTO(double value, String unit) {
        return new QuantityDTO(value, unit, "WEIGHT");
    }

    private QuantityDTO volumeDTO(double value, String unit) {
        return new QuantityDTO(value, unit, "VOLUME");
    }

    private QuantityDTO temperatureDTO(double value, String unit) {
        return new QuantityDTO(value, unit, "TEMPERATURE");
    }

    // ─────────────────────────────────────────────────────────
    //  COMPARE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Compare: 1 FEET and 12 INCH should be equal")
    void compare_feetAndInch_returnsTrue() {
        boolean result = service.compare(
                lengthDTO(1.0, "FEET"),
                lengthDTO(12.0, "INCH"),
                USER_EMAIL);
        assertTrue(result);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Compare: 1 FEET and 11 INCH should not be equal")
    void compare_feetAndInch_returnsFalse() {
        boolean result = service.compare(
                lengthDTO(1.0, "FEET"),
                lengthDTO(11.0, "INCH"),
                USER_EMAIL);
        assertFalse(result);
    }

    @Test
    @DisplayName("Compare: 1 KILOGRAM and 1000 GRAM should be equal")
    void compare_kilogramAndGram_returnsTrue() {
        boolean result = service.compare(
                weightDTO(1.0, "KILOGRAM"),
                weightDTO(1000.0, "GRAM"),
                USER_EMAIL);
        assertTrue(result);
    }

    @Test
    @DisplayName("Compare: different measurement types should throw")
    void compare_differentTypes_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.compare(
                        lengthDTO(1.0, "FEET"),
                        weightDTO(1.0, "GRAM"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Compare: guest user (null email) should not save history")
    void compare_nullEmail_doesNotSaveHistory() {
        service.compare(lengthDTO(1.0, "FEET"), lengthDTO(12.0, "INCH"), null);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Compare: invalid unit name should throw")
    void compare_invalidUnit_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.compare(
                        lengthDTO(1.0, "INVALID_UNIT"),
                        lengthDTO(1.0, "INCH"),
                        USER_EMAIL));
    }

    // ─────────────────────────────────────────────────────────
    //  CONVERT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Convert: 1 FEET to INCH = 12")
    void convert_feetToInch_correctResult() {
        QuantityDTO result = service.convert(lengthDTO(1.0, "FEET"), "INCH", USER_EMAIL);
        assertEquals(12.0, result.getValue(), DELTA);
        assertEquals("INCH", result.getUnit());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Convert: 1 KILOGRAM to GRAM = 1000")
    void convert_kilogramToGram_correctResult() {
        QuantityDTO result = service.convert(weightDTO(1.0, "KILOGRAM"), "GRAM", USER_EMAIL);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Convert: 100 CELSIUS to FAHRENHEIT = 212")
    void convert_celsiusToFahrenheit_correctResult() {
        QuantityDTO result = service.convert(temperatureDTO(100.0, "CELSIUS"), "FAHRENHEIT", USER_EMAIL);
        assertEquals(212.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Convert: 1 LITRE to MILLILITRE = 1000")
    void convert_litreToMillilitre_correctResult() {
        QuantityDTO result = service.convert(volumeDTO(1.0, "LITRE"), "MILLILITRE", USER_EMAIL);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Convert: invalid target unit should throw")
    void convert_invalidTargetUnit_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.convert(lengthDTO(1.0, "FEET"), "METER", USER_EMAIL));
    }

    @Test
    @DisplayName("Convert: invalid measurement type should throw")
    void convert_invalidMeasurementType_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.convert(new QuantityDTO(1.0, "FEET", "INVALID"), "INCH", USER_EMAIL));
    }

    @Test
    @DisplayName("Convert: guest user (null email) should not save history")
    void convert_nullEmail_doesNotSaveHistory() {
        service.convert(lengthDTO(1.0, "FEET"), "INCH", null);
        verify(repository, never()).save(any());
    }

    // ─────────────────────────────────────────────────────────
    //  ADD
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Add: 5 GRAM + 1 KILOGRAM = 1005 GRAM")
    void add_gramAndKilogram_correctResult() {
        QuantityDTO result = service.add(
                weightDTO(5.0, "GRAM"),
                weightDTO(1.0, "KILOGRAM"),
                USER_EMAIL);
        assertEquals(1005.0, result.getValue(), DELTA);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Add: 1 LITRE + 500 MILLILITRE = 1.5 LITRE")
    void add_litreAndMillilitre_correctResult() {
        QuantityDTO result = service.add(
                volumeDTO(1.0, "LITRE"),
                volumeDTO(500.0, "MILLILITRE"),
                USER_EMAIL);
        assertEquals(1.5, result.getValue(), DELTA);
    }

    @Test
    @DisplayName("Add: Temperature should throw (unsupported)")
    void add_temperature_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.add(
                        temperatureDTO(10.0, "CELSIUS"),
                        temperatureDTO(20.0, "CELSIUS"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Add: different measurement types should throw")
    void add_differentTypes_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.add(
                        lengthDTO(1.0, "FEET"),
                        weightDTO(1.0, "GRAM"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Add: guest user (null email) should not save history")
    void add_nullEmail_doesNotSaveHistory() {
        service.add(weightDTO(1.0, "GRAM"), weightDTO(2.0, "GRAM"), null);
        verify(repository, never()).save(any());
    }

    // ─────────────────────────────────────────────────────────
    //  SUBTRACT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Subtract: 1 KILOGRAM - 500 GRAM = 500 GRAM")
    void subtract_kilogramMinusGram_correctResult() {
        QuantityDTO result = service.subtract(
                weightDTO(1.0, "KILOGRAM"),
                weightDTO(500.0, "GRAM"),
                USER_EMAIL);
        assertEquals(500.0, result.getValue(), DELTA);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Subtract: Temperature should throw (unsupported)")
    void subtract_temperature_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.subtract(
                        temperatureDTO(100.0, "CELSIUS"),
                        temperatureDTO(20.0, "CELSIUS"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Subtract: different types should throw")
    void subtract_differentTypes_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.subtract(
                        lengthDTO(1.0, "FEET"),
                        weightDTO(1.0, "GRAM"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Subtract: guest user (null email) should not save history")
    void subtract_nullEmail_doesNotSaveHistory() {
        service.subtract(weightDTO(5.0, "GRAM"), weightDTO(2.0, "GRAM"), null);
        verify(repository, never()).save(any());
    }

    // ─────────────────────────────────────────────────────────
    //  DIVIDE
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("Divide: 10 GRAM / 2 GRAM = 5.0")
    void divide_gramByGram_correctResult() {
        double result = service.divide(
                weightDTO(10.0, "GRAM"),
                weightDTO(2.0, "GRAM"),
                USER_EMAIL);
        assertEquals(5.0, result, DELTA);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Divide: 1 KILOGRAM / 500 GRAM = 2.0")
    void divide_kilogramByGram_correctResult() {
        double result = service.divide(
                weightDTO(1.0, "KILOGRAM"),
                weightDTO(500.0, "GRAM"),
                USER_EMAIL);
        assertEquals(2.0, result, DELTA);
    }

    @Test
    @DisplayName("Divide: by zero should throw")
    void divide_byZero_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.divide(
                        weightDTO(10.0, "GRAM"),
                        weightDTO(0.0, "GRAM"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Divide: Temperature should throw (unsupported)")
    void divide_temperature_throwsException() {
        assertThrows(QuantityMeasurementException.class, () ->
                service.divide(
                        temperatureDTO(100.0, "CELSIUS"),
                        temperatureDTO(10.0, "CELSIUS"),
                        USER_EMAIL));
    }

    @Test
    @DisplayName("Divide: guest user (null email) should not save history")
    void divide_nullEmail_doesNotSaveHistory() {
        service.divide(weightDTO(10.0, "GRAM"), weightDTO(2.0, "GRAM"), null);
        verify(repository, never()).save(any());
    }

    // ─────────────────────────────────────────────────────────
    //  HISTORY
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("getHistory: ALL returns findByUserEmail")
    void getHistory_all_callsFindByUserEmail() {
        List<QuantityMeasurementEntity> mockList = List.of(new QuantityMeasurementEntity());
        when(repository.findByUserEmail(USER_EMAIL)).thenReturn(mockList);

        List<QuantityMeasurementEntity> result = service.getHistory("ALL", USER_EMAIL);

        assertEquals(1, result.size());
        verify(repository).findByUserEmail(USER_EMAIL);
        verify(repository, never()).findByOperationAndUserEmail(any(), any());
    }

    @Test
    @DisplayName("getHistory: specific operation calls findByOperationAndUserEmail")
    void getHistory_specificOp_callsFindByOperationAndUserEmail() {
        List<QuantityMeasurementEntity> mockList = List.of(new QuantityMeasurementEntity());
        when(repository.findByOperationAndUserEmail("ADD", USER_EMAIL)).thenReturn(mockList);

        List<QuantityMeasurementEntity> result = service.getHistory("ADD", USER_EMAIL);

        assertEquals(1, result.size());
        verify(repository).findByOperationAndUserEmail("ADD", USER_EMAIL);
    }

    // ─────────────────────────────────────────────────────────
    //  COUNT
    // ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("getCount: returns correct count from repository")
    void getCount_returnsCorrectCount() {
        when(repository.countByOperationAndUserEmail("CONVERT", USER_EMAIL)).thenReturn(5L);

        long count = service.getCount("CONVERT", USER_EMAIL);

        assertEquals(5L, count);
        verify(repository).countByOperationAndUserEmail("CONVERT", USER_EMAIL);
    }

    @Test
    @DisplayName("getCount: returns 0 when no records exist")
    void getCount_noRecords_returnsZero() {
        when(repository.countByOperationAndUserEmail("ADD", USER_EMAIL)).thenReturn(0L);

        long count = service.getCount("ADD", USER_EMAIL);

        assertEquals(0L, count);
    }
}
