package com.reneuby.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "COEFFICIENTS")
public class Coefficients implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private double a;
    private double b;
    private double c;

    public Coefficients(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Coefficients() {
    }

    @Override
    public String toString() {
        return "Коэффициенты: a=" + a +
                ", b=" + b +
                ", c=" + c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coefficients that = (Coefficients) o;
        return id == that.id &&
                Double.compare(that.a, a) == 0 &&
                Double.compare(that.b, b) == 0 &&
                Double.compare(that.c, c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, a, b, c);
    }
}
