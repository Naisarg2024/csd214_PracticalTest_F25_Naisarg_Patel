package org.example.repositories;

import org.example.entities.VehicleEntity;

import java.lang.reflect.Field;
import java.util.*;

public class InMemoryVehicleRepository implements Repository<VehicleEntity> {
    private final Map<Long, VehicleEntity> storage = new HashMap<>();
    private long nextId = 1;

    @Override
    public Optional<VehicleEntity> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
    @Override
    public List<VehicleEntity> findAll() {
        return new ArrayList<>(storage.values());
    }
    @Override
    public VehicleEntity save(VehicleEntity entity) {
        try {
            Field fieldId = entity.getClass().getSuperclass().getDeclaredField("id");
            fieldId.setAccessible(true);
            Long id = (Long)fieldId.get(entity);

            if (id == null) {
                id=nextId++;
                fieldId.set(entity, id);
            }
            storage.put(id, entity);
            return entity;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
