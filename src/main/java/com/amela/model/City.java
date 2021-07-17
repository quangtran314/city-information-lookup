package com.amela.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class City {
    private int id;
    private String name;
    private String nation;
    private double area;
    private int population;
    private int GDP;
    private String description;

    public City() {
    }

    public City(String name, String nation, double area, int population, int GDP, String description) {
        this.name = name;
        this.nation = nation;
        this.area = area;
        this.population = population;
        this.GDP = GDP;
        this.description = description;
    }

    public City(int id, String name, String nation, double area, int population, int GDP, String description) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.area = area;
        this.population = population;
        this.GDP = GDP;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @NotNull
    @Min(1000)
    @Max(Integer.MAX_VALUE)
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @NotNull
    @Min(100000)
    @Max(10000000)
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @NotNull
    @Min(10)
    @Max(1000)
    public int getGDP() {
        return GDP;
    }

    public void setGDP(int GDP) {
        this.GDP = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
