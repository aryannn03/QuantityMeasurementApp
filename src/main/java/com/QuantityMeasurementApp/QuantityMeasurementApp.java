package com.QuantityMeasurementApp;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.IQuantityMeasurementRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.QuantityMeasurementApp.repository.QuantityMeasurementDatabaseRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;
import com.QuantityMeasurementApp.util.ApplicationConfig;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository;

        String repositoryType =
                ApplicationConfig.getProperty("repository.type", "cache");

        if (repositoryType.equalsIgnoreCase("database")) {

            repository = new QuantityMeasurementDatabaseRepository();

        } else {

            repository = QuantityMeasurementCacheRepository.getInstance();
        }

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(2, "FEET", "Length");

        double result = controller.performDivision(q1, q2);

        System.out.println("Division result: " + result);
    }
}