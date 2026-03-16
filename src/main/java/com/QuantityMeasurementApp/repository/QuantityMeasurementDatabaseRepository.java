package com.QuantityMeasurementApp.repository;

import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.exception.DatabaseException;
import com.QuantityMeasurementApp.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement_entity(operation, result_string) VALUES (?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT operation, result_string FROM quantity_measurement_entity";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, entity.getOperation());
            statement.setString(2, entity.getResult());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Failed to save measurement to database", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> measurements = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                String operation = rs.getString("operation");
                String result = rs.getString("result_string");

                measurements.add(new QuantityMeasurementEntity(operation, result));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve measurements", e);
        }

        return measurements;
    }
}