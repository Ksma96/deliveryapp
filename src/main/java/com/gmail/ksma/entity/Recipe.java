package com.gmail.ksma.entity;

import java.util.Objects;

/**
 * @author Maxim Melanich
 * Date: 02.08.2021
 * Time: 21:59
 */
public class Recipe {
    private int id;
    private DeliveryOrder deliveryOrder;
    private long price;
    private boolean isPaid;

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && price == recipe.price && isPaid == recipe.isPaid && Objects.equals(deliveryOrder, recipe.deliveryOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryOrder, price, isPaid);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", deliveryOrder=" + deliveryOrder +
                ", price=" + price +
                ", isPaid=" + isPaid +
                '}';
    }
}

