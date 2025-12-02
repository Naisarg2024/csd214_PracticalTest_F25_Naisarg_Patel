package org.example;

import org.example.entities.VehicleEntity;
import org.example.repositories.InMemoryVehicleRepository;
import org.example.repositories.MySQLVehicleRepository;
import org.example.repositories.Repository;
import org.example.services.VehicleService;
import org.example.ui.App;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select repository: 1. In-Memory, 2. MySQL");
        String choice = scanner.nextLine();
        Repository<VehicleEntity> repository = null;
        // 1. Create the correct repository based on user input
        if (choice.equals("1")) {
            repository = new InMemoryVehicleRepository();
        } else {
            repository = new MySQLVehicleRepository();
        }
        try {
            // 2. Create the service and INJECT the repository
            VehicleService service = new VehicleService(repository);
            // 3. Create the App and INJECT the service
            App app = new App(service);
            // 4. Run the application
            app.run();
        } finally {
            // 5. Clean up resources
            if (repository != null) {
                repository.close();
            }
        }
    }
}