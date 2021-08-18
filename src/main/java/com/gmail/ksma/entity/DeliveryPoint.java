package com.gmail.ksma.entity;

import java.util.Objects;

/**
 * @author Maxim Melanich
 * Date: 02.08.2021
 * Time: 22:03
 */
public class DeliveryPoint {
    private int id;
    private String address;

    public DeliveryPoint(){

    }

    public DeliveryPoint(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public DeliveryPoint setId(int id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryPoint setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryPoint that = (DeliveryPoint) o;
        return id == that.id &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }

    @Override
    public String toString() {
        return "DeliveryPoint{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}

