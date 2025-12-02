package org.example.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class VehicleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int year;

    // getters
    public Long getId() {
        return id;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }

    // setters
    public void setModel(String model) {
        this.model = model;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
