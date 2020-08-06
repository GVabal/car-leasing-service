package dev.vabalas.carleasing.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CarTest {

    @Test
    void constructorAndGettersAreCorrect() {
        Car car = new Car("make", "model", 1999, Car.FuelType.OTHER, Car.Damage.OTHER);
        assertEquals(car.getMake(), "make");
        assertEquals(car.getModel(), "model");
        assertEquals(car.getYear(), 1999);
        assertEquals(car.getFuelType(), Car.FuelType.OTHER);
        assertEquals(car.getDamage(), Car.Damage.OTHER);
    }
}