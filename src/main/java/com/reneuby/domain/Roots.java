package com.reneuby.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ROOTS")
public class Roots implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private double x1;
    private double x2;
    @Transient
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Roots() {
    }

    public Roots(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Корни: x1=" + x1 +
                ", x2=" + x2;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roots roots = (Roots) o;
        return id == roots.id &&
                Double.compare(roots.x1, x1) == 0 &&
                Double.compare(roots.x2, x2) == 0 &&
                Objects.equals(error, roots.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x1, x2, error);
    }
}
