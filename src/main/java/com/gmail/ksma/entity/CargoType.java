package com.gmail.ksma.entity;

import java.util.Objects;

/**
 * @author Maxim Melanich
 * Date: 02.08.2021
 * Time: 21:51
 */
public class CargoType {
    private int id;
    private String name;

    public CargoType() {
    }

    public CargoType(String name) {
        this.name = name;
    }

    public CargoType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CargoType setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public CargoType setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoType cargoType = (CargoType) o;
        return id == cargoType.id &&
                Objects.equals(name, cargoType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
