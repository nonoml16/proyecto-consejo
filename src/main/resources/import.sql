INSERT INTO Sector (id, localizacion, precio) VALUES (1, 'Campana', 299);
INSERT INTO Sector (id, localizacion, precio) VALUES (2, 'Sierpes', 249);
INSERT INTO Sector (id, localizacion, precio) VALUES (3, 'Plaza de San Francisco', 1099);
INSERT INTO Sector (id, localizacion, precio) VALUES (4, 'Avenida', 199);
INSERT INTO Sector (id, localizacion, precio) VALUES (5, 'Catedral', 149);
INSERT INTO Sector (id, localizacion, precio) VALUES (6, 'Plaza Virgen de los Reyes', 289);

INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (1, '21135681S', 'Manolo', 'Garcéa Vázquez');
INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (2, '89088712Y', 'José', 'Rico Pérez');
INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (3, '65665578A', 'Victor', 'Garcia Rayo');
INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (4, '49424503X', 'Jose Manuel', 'Peña García');
INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (5, '09410348J', 'José Antonio', 'Sanchez Araujo');

INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (1, 1, 6, 1, 'PALCO', 3);
INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (2, 4, 14, 2, 'SILLA', 1);
INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (3, 3, 34, 3, 'SILLA', 2);
INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (4, 7, 76, 4, 'SILLA', 4);
INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (5, 3, 44, 5, 'SILLA', 5);
INSERT INTO Localidad (id, fila, num_localidad, propietario_id, tipo_localidad, sector_id) VALUES (6, 15, 21, 1, 'SILLA', 6);

ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;
