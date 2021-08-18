package com.gmail.ksma.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Maxim Melanich
 * Date: 02.08.2021
 * Time: 22:13
 */
public class Route {
    private int id;
    private DeliveryPoint from;
    private DeliveryPoint to;
    public Route() {
    }

    public Route(int id, DeliveryPoint from, DeliveryPoint to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public Route setId(int id) {
        this.id = id;
        return this;
    }

    public DeliveryPoint getFrom() {
        return from;
    }

    public Route setFrom(DeliveryPoint from) {
        this.from = from;
        return this;
    }

    public DeliveryPoint getTo() {
        return to;
    }

    public Route setTo(DeliveryPoint to) {
        this.to = to;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(from, route.from) &&
                Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to);
    }

}
