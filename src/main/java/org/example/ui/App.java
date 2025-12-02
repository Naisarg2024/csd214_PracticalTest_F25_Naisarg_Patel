package org.example.ui;

import org.example.entities.CarEntity;
import org.example.entities.MotorcycleEntity;
import org.example.entities.VehicleEntity;
import org.example.services.VehicleService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private final VehicleService service;
    private Scanner scanner = new Scanner(System.in);

    public App(VehicleService service) {
        this.service = service;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addVehicle();
                case "2" -> listVehicles();
                case "3" -> updateVehicle();
                case "4" -> deleteVehicle();
                case "5" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Exiting...");
    }

    private void printMenu() {
        System.out.println("==== Vehicle Manager ====");
        System.out.println("1. Add Vehicle");
        System.out.println("2. List All Vehicles");
        System.out.println("3. Update Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Quit");
        System.out.print("Choice: ");
    }

    private void addVehicle() {
        System.out.print("Car (c) or Motorcycle (m)? ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        if (type.equals("c")) {
            System.out.print("Number of doors: ");
            int doors = Integer.parseInt(scanner.nextLine());
            service.createCar(make, model, year, doors);
        } else if (type.equals("m")) {
            System.out.print("Has sidecar? (y/n): ");
            boolean hasSidecar = scanner.nextLine().trim().equalsIgnoreCase("y");
            service.createMotorcycle(make, model, year, hasSidecar);
        } else {
            System.out.println("Unknown type.");
        }
    }

    private void listVehicles() {
        List<VehicleEntity> vehicles = service.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        for (VehicleEntity v : vehicles) {
            String type;
            String extra;

            if (v instanceof CarEntity car) {
                type = "Car";
                extra = "doors=" + car.getNumDoors();
            } else if (v instanceof MotorcycleEntity m) {
                type = "Motorcycle";
                extra = "hasSidecar=" + m.isHasSidecar();
            } else {
                type = "Vehicle";
                extra = "";
            }

            System.out.printf(
                    "ID=%d, type=%s, make=%s, model=%s, year=%d %s%n",
                    v.getId(), type, v.getMake(), v.getModel(), v.getYear(), extra
            );
        }
    }

    private void updateVehicle() {
        System.out.print("Enter ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        Optional<VehicleEntity> maybe = service.findById(id);
        if (maybe.isEmpty()) {
            System.out.println("Not found.");
            return;
        }
        VehicleEntity v = maybe.get();

        System.out.print("New make (" + v.getMake() + "): ");
        String make = scanner.nextLine();
        if (!make.isBlank()) v.setMake(make);

        System.out.print("New model (" + v.getModel() + "): ");
        String model = scanner.nextLine();
        if (!model.isBlank()) v.setModel(model);

        System.out.print("New year (" + v.getYear() + "): ");
        String yearStr = scanner.nextLine();
        if (!yearStr.isBlank()) v.setYear(Integer.parseInt(yearStr));

        if (v instanceof CarEntity car) {
            System.out.print("New doors (" + car.getNumDoors() + "): ");
            String dStr = scanner.nextLine();
            if (!dStr.isBlank()) car.setNumDoors(Integer.parseInt(dStr));
        } else if (v instanceof MotorcycleEntity m) {
            System.out.print("Has sidecar (" + m.isHasSidecar() + ") y/n (blank keep): ");
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("y")) m.setHasSidecar(true);
            else if (s.equalsIgnoreCase("n")) m.setHasSidecar(false);
        }

        service.save(v);
    }

    private void deleteVehicle() {
        System.out.print("Enter ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        service.deleteById(id);
        System.out.println("Deleted (if existed).");
    }
}