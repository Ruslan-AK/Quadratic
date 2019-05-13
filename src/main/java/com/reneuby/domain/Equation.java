package com.reneuby.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EQUATION")
public class Equation implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Coefficients coefficients;

    @Override
    public String toString() {
        return "Расчет №" + id + " " + coefficients + " " + roots;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Roots roots;

    public Equation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return id == equation.id &&
                Objects.equals(coefficients, equation.coefficients) &&
                Objects.equals(roots, equation.roots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coefficients, roots);
    }

    public Equation(Coefficients coefficients, Roots roots) {
        this.coefficients = coefficients;
        this.roots = roots;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coefficients getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(Coefficients coefficients) {
        this.coefficients = coefficients;
    }

    public Roots getRoots() {
        return roots;
    }

    public void setRoots(Roots roots) {
        this.roots = roots;
    }
}
