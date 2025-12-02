package org.example.services;

import org.example.entities.VehicleEntity;
import org.example.entities.CarEntity;
import org.example.entities.MotorcycleEntity;
import org.example.repositories.Repository;

import java.util.List;
import java.util.Optional;

public class VehicleService {
    private final Repository<VehicleEntity> deviceRepository;

    public VehicleService(Repository<VehicleEntity> deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Optional<VehicleEntity> findById(long id) {
        return deviceRepository.findById(id);
    }

    public List<VehicleEntity> getAllDevices() {
        return deviceRepository.findAll();
    }

    // create a smarphone
    public MotorcycleEntity createMotorcycle(String make, String model, int year, boolean hasSidecar) {
        MotorcycleEntity m = new MotorcycleEntity();
        m.setMake(make);
        m.setModel(model);
        m.setYear(year);
        m.setHasSidecar(hasSidecar);
        return (MotorcycleEntity) deviceRepository.save(m);
    }

    // create a laptop
    public CarEntity createCar(String make, String model, int year, int numDoors) {
        CarEntity c = new CarEntity();
        c.setMake(make);
        c.setModel(model);
        c.setYear(year);
        c.setNumDoors(numDoors);
        return (CarEntity) deviceRepository.save(c);
    }

    // update : using abstract class so that it can update the extending type of vehicle
    public VehicleEntity updateDevice(Long id, String newBrandName, String newModel, int year) {
        Optional<VehicleEntity> vehicle = deviceRepository.findById(id);
        if (vehicle.isEmpty()) {
            return null;
        }
        VehicleEntity ed = vehicle.get();
        ed.setMake(newBrandName);
        ed.setModel(newModel);
        ed.setYear(year);
        return deviceRepository.save(ed);
    }

    // delete : using abstract class so that it can delete the extending type of vehicle
    public boolean deleteDevice(Long id) {
        Optional<VehicleEntity> vehicle = deviceRepository.findById(id);
        if (vehicle.isEmpty()) {
            return false;
        }
        deviceRepository.deleteById(id);
        return true;
    }

    public Optional<VehicleEntity> findById(Long id) {
        return deviceRepository.findById(id);
    }
    public VehicleEntity save(VehicleEntity v) {
        return deviceRepository.save(v);
    }
    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
    public List<VehicleEntity> getAllVehicles() {
        return deviceRepository.findAll();
    }
}
