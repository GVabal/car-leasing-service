package dev.vabalas.carleasing.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void constructorAndGettersWork() {
        Car car = new Car("make", "model", 1999, Car.FuelType.OTHER, Car.Damage.OTHER);
        Person person = new Person("firstName", "lastName", "socnr123", 1, 1000);
        Application application = new Application(car, person, 10000);

        assertEquals(application.getCar(), car);
        assertEquals(application.getPerson(), person);
        assertEquals(application.getCreditAmount(), 10000);
    }

    @Test
    void processCreditApplication_approvesCreditCorrectly() {
        Car car = new Car("make", "model", 1999, Car.FuelType.OTHER, Car.Damage.OTHER);
        Person person1 = new Person("firstName", "lastName", "socnr123", 0, 600);
        Application provableApplication = new Application(car, person1, 10000);

        Person person2 = new Person("firstName", "lastName", "socnr123", 1, 1199);
        Application notProvableApplication1 = new Application(car, person2, 10000);

        Person person3 = new Person("firstName", "lastName", "socnr123", 0, 599);
        Application notProvableApplication2 = new Application(car, person3, 10000);

        assertTrue(provableApplication.isApproved());
        assertFalse(notProvableApplication1.isApproved());
        assertFalse(notProvableApplication2.isApproved());
    }
}