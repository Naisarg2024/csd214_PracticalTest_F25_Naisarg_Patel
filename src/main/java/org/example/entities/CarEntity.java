package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class CarEntity extends VehicleEntity {

    private int numDoors;

    // getter
    public int getNumDoors() {
        return numDoors;
    }

    // setter
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }
}
