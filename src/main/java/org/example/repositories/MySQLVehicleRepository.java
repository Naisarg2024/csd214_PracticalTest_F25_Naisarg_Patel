package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.VehicleEntity;

import java.util.List;
import java.util.Optional;

public class MySQLVehicleRepository implements Repository<VehicleEntity> {

    private final EntityManagerFactory emf;

    public MySQLVehicleRepository() {
        this.emf = Persistence.createEntityManagerFactory("csd214-unit");
    }

    @Override
    public Optional<VehicleEntity> findById(Long id) {
        EntityManager entityMngr = emf.createEntityManager();
        try {
            return Optional.ofNullable(entityMngr.find(VehicleEntity.class, id));
        } finally {
            entityMngr.close();
        }
    }

    @Override
    public List<VehicleEntity> findAll() {
        EntityManager entityMngr = emf.createEntityManager();
        try {
            return entityMngr.createQuery(
                    "SELECT d FROM ElectronicDeviceEntity d",
                    VehicleEntity.class).getResultList();
        } finally {
            entityMngr.close();
        }
    }

    @Override
    public VehicleEntity save(VehicleEntity entity) {
        EntityManager entityMngr = emf.createEntityManager();
        EntityTransaction transaction = entityMngr.getTransaction();
        transaction.begin();
        VehicleEntity merged = entityMngr.merge(entity);
        transaction.commit();
        entityMngr.close();
        return merged;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityMngr = emf.createEntityManager();
        EntityTransaction transaction = entityMngr.getTransaction();
        transaction.begin();
        VehicleEntity entity = entityMngr.find(VehicleEntity.class, id);
        if (entity != null) {
            entityMngr.remove(entity);
        }
        transaction.commit();
        entityMngr.close();
    }

    @Override
    public void close() {
        emf.close();
    }
}
