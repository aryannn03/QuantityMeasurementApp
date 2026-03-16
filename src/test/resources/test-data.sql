INSERT INTO quantity_measurement_entity
(this_value, this_unit, this_measurement_type,
 that_value, that_unit, that_measurement_type,
 operation, result_value, result_unit, result_string)
VALUES
(10, 'FEET', 'Length',
 6, 'INCHES', 'Length',
 'ADD', 10.5, 'FEET', '10 FEET + 6 INCHES = 10.5 FEET');

INSERT INTO quantity_measurement_entity
(this_value, this_unit, this_measurement_type,
 that_value, that_unit, that_measurement_type,
 operation, result_value, result_unit, result_string)
VALUES
(5, 'KG', 'Weight',
 500, 'GRAM', 'Weight',
 'ADD', 5.5, 'KG', '5 KG + 500 GRAM = 5.5 KG');

INSERT INTO quantity_measurement_entity
(this_value, this_unit, this_measurement_type,
 that_value, that_unit, that_measurement_type,
 operation, result_value, result_unit, result_string)
VALUES
(2, 'LITRE', 'Volume',
 500, 'ML', 'Volume',
 'ADD', 2.5, 'LITRE', '2 LITRE + 500 ML = 2.5 LITRE');