CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    this_value DOUBLE NOT NULL,
    this_unit VARCHAR(50) NOT NULL,
    this_measurement_type VARCHAR(50) NOT NULL,
    operation VARCHAR(50) NOT NULL,
    result_string VARCHAR(255)
);