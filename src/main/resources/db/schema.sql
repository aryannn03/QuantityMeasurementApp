CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    entity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50) NOT NULL,
    result_string VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_operation
ON quantity_measurement_entity(operation);