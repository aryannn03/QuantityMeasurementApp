package com.QuantityMeasurementApp.repository;

import com.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String INSERT_QUERY =
            "INSERT INTO quantity_measurement_entity " +
                    "(this_value,this_unit,this_measurement_type,operation,result_string) " +
                    "VALUES (?,?,?,?,?)";

    private static final String SELECT_ALL =
            "SELECT * FROM quantity_measurement_entity";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try {

            Connection connection = ConnectionPool.getConnection();

            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);

            ps.setDouble(1, entity.getThisValue());
            ps.setString(2, entity.getThisUnit());
            ps.setString(3, entity.getMeasurementType());
            ps.setString(4, entity.getOperation());
            ps.setString(5, entity.getResultString());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        try {

            Connection connection = ConnectionPool.getConnection();

            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

                entity.setId(rs.getInt("id"));
                entity.setThisValue(rs.getDouble("this_value"));
                entity.setThisUnit(rs.getString("this_unit"));
                entity.setMeasurementType(rs.getString("this_measurement_type"));
                entity.setOperation(rs.getString("operation"));
                entity.setResultString(rs.getString("result_string"));

                list.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}