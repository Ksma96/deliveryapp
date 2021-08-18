package com.gmail.ksma.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Maxim Melanich
 * Date: 02.08.2021
 * Time: 22:08
 */
public class DeliveryOrder {
    private  int id;
    private Role role;
    private User user;
    private CargoType cargoType;
    private long weight;
    private long volume;
    private LocalDate deliveryTime;

    public DeliveryOrder() {
    }

    public DeliveryOrder(int id, Role role, User user, CargoType cargoType, long weight, long volume, LocalDate deliveryTime) {
        this.id = id;
        this.role = role;
        this.user = user;
        this.cargoType = cargoType;
        this.weight = weight;
        this.volume = volume;
        this.deliveryTime = deliveryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public LocalDate getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDate deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryOrder that = (DeliveryOrder) o;
        return id == that.id && weight == that.weight && volume == that.volume && role == that.role && Objects.equals(user, that.user) && Objects.equals(cargoType, that.cargoType) && Objects.equals(deliveryTime, that.deliveryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, user, cargoType, weight, volume, deliveryTime);
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "id=" + id +
                ", role=" + role +
                ", user=" + user +
                ", cargoType=" + cargoType +
                ", weight=" + weight +
                ", volume=" + volume +
                ", deliveryTime=" + deliveryTime +
                '}';
    }

    
}
