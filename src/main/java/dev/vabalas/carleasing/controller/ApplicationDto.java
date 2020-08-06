package dev.vabalas.carleasing.controller;

import javax.validation.constraints.*;

public class ApplicationDto {

    @NotNull
    private int amount;

    @NotNull @NotEmpty @NotBlank
    private String make;

    @NotNull @NotEmpty @NotBlank
    private String model;

    @NotNull
    private Integer year;

    @NotNull @NotEmpty @NotBlank
    private String fuelType;

    @NotNull @NotEmpty @NotBlank
    private String damage;

    @NotNull @NotEmpty @NotBlank
    private String firstName;

    @NotNull @NotEmpty @NotBlank
    private String lastName;

    @NotNull @NotEmpty @NotBlank
    private String socialSecurityNumber;

    @NotNull
    @Min(value = 0)
    private int dependents;

    @NotNull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int monthlyIncome;

    protected ApplicationDto() {}

    public ApplicationDto(int amount, String make, String model, Integer year, String fuelType, String damage, String firstName, String lastName, String socialSecurityNumber, int dependents, int monthlyIncome) {
        this.amount = amount;
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.damage = damage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dependents = dependents;
        this.monthlyIncome = monthlyIncome;
    }

    public int getAmount() {
        return amount;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getDamage() {
        return damage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public int getDependents() {
        return dependents;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setDependents(int dependents) {
        this.dependents = dependents;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationDto{");
        sb.append("amount=").append(amount);
        sb.append(", make='").append(make).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", year=").append(year);
        sb.append(", fuelType='").append(fuelType).append('\'');
        sb.append(", damage='").append(damage).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'');
        sb.append(", dependents=").append(dependents);
        sb.append(", monthlyIncome=").append(monthlyIncome);
        sb.append('}');
        return sb.toString();
    }
}
