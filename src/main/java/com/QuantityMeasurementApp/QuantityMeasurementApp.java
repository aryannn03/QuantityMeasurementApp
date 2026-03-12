package com.QuantityMeasurementApp;

import com.QuantityMeasurementApp.controller.QuantityMeasurementController;
import com.QuantityMeasurementApp.dto.QuantityDTO;
import com.QuantityMeasurementApp.repository.QuantityMeasurementCacheRepository;
import com.QuantityMeasurementApp.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // initialize repository
        QuantityMeasurementCacheRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        // initialize service
        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        // initialize controller
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // example quantities
        QuantityDTO q1 = new QuantityDTO(10, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(6, "INCHES", "Length");

        // call controller
        QuantityDTO result = controller.performAddition(q1, q2);

        System.out.println("Addition result: " + result);
    }
}