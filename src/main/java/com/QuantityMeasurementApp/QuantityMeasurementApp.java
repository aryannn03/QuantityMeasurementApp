package com.QuantityMeasurementApp;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;
import com.QuantityMeasurementApp.util.ApplicationConfig;
import com.QuantityMeasurementApp.util.ConnectionPool;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
    	 
    	ConnectionPool.initializeDatabase();
    	
        IQuantityMeasurementRepository repository;

        // decide repository type
        if ("database".equalsIgnoreCase(ApplicationConfig.getRepositoryType())) {
            repository = new QuantityMeasurementDatabaseRepository();
            System.out.println("Using Database Repository");
        } else {
            repository = QuantityMeasurementCacheRepository.getInstance();
            System.out.println("Using Cache Repository");
        }

        // initialize service
        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        // initialize controller
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // example quantities
        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        // perform addition
        QuantityDTO result = controller.performAddition(q1, q2);

        System.out.println("Addition result: " + result);
    }
}