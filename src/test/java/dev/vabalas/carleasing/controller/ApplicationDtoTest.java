package dev.vabalas.carleasing.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDtoTest {

    @Test
    void constructorWorks() {
        ApplicationDto applicationDto = new ApplicationDto(1, "make", "model", 1999,
                "other", "other", "firstName", "lastName",
                "socnr1234", 1, 1000);

        assertEquals(applicationDto.getAmount(), 1);
        assertEquals(applicationDto.getMake(), "make");
        assertEquals(applicationDto.getModel(), "model");
        assertEquals(applicationDto.getYear(), 1999);
        assertEquals(applicationDto.getFuelType(), "other");
        assertEquals(applicationDto.getDamage(), "other");
        assertEquals(applicationDto.getFirstName(), "firstName");
        assertEquals(applicationDto.getLastName(), "lastName");
        assertEquals(applicationDto.getSocialSecurityNumber(), "socnr1234");
        assertEquals(applicationDto.getDependents(), 1);
        assertEquals(applicationDto.getMonthlyIncome(), 1000);
    }
}