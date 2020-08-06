package dev.vabalas.carleasing.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.stream.Stream;

@Entity
@Table(name = "cars")
public class Car {

    public enum FuelType {
        DIESEL("diesel"),
        DIESEL_ELECTRIC("diesel/electric"),
        ELECTRIC("electric"),
        ETHANOL("ethanol"),
        GAS("gas"),
        GASOLINE("gasoline"),
        GASOLINE_ELECTRIC("gasoline/electric"),
        OTHER("other"),
        PETROL_GAS("petrol/gas"),
        PETROL_NATURAL_GAS("petrol/natural gas");

        private String type;

        FuelType(String type) {
            this.type = type;
        }

        @JsonCreator
        public static FuelType decode(final String type) {
            return Stream.of(FuelType.values()).filter(e -> e.type.equals(type)).findFirst().orElse(FuelType.OTHER);
        }

        @JsonValue
        public String getType() {
            return type;
        }

    }

    public enum Damage {
        CRASHED("crashed"),
        ENGINE("engine"),
        FIRE("fire"),
        GEARBOX("gearbox"),
        HAIL("hail"),
        NO_DAMAGES("no damages"),
        OTHER("other"),
        WATER("water");

        private String type;

        Damage(String type) {
            this.type = type;
        }

        @JsonCreator
        public static Damage decode(final String type) {
            return Stream.of(Damage.values()).filter(e -> e.type.equals(type)).findFirst().orElse(Damage.OTHER);
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "fuel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "damage", nullable = false)
    @Enumerated(EnumType.STRING)
    private Damage damage;

    protected Car() {}

    public Car(String make, String model, Integer year, FuelType fuelType, Damage damage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.damage = damage;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }
}
