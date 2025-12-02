package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class MotorcycleEntity extends VehicleEntity {

    private boolean hasSidecar;

    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public  void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}
