package dev.vabalas.carleasing.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void constructorAndGettersWork() {
        Person person = new Person("firstName", "lastName", "socnr123", 1, 1000);

        assertEquals(person.getFirstName(), "firstName");
        assertEquals(person.getLastName(), "lastName");
        assertEquals(person.getSocialSecurityNumber(), "socnr123");
        assertEquals(person.getDependents(), 1);
        assertEquals(person.getMonthlyIncome(), 1000);
    }
}