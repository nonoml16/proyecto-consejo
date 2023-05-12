INSERT INTO Sector (id, localizacion, precio) VALUES (1, 'Campana', 299);
INSERT INTO Sector (id, localizacion, precio) VALUES (2, 'Sierpes', 249);
INSERT INTO Sector (id, localizacion, precio) VALUES (3, 'Plaza de San Francisco', 1099);
INSERT INTO Sector (id, localizacion, precio) VALUES (4, 'Avenida', 199);
INSERT INTO Sector (id, localizacion, precio) VALUES (5, 'Catedral', 149);
INSERT INTO Sector (id, localizacion, precio) VALUES (6, 'Plaza Virgen de los Reyes', 289);

INSERT INTO Propietario (dni, nombre, apellidos) VALUES ('21135681S', 'Manolo', 'Garcéa Vázquez');
INSERT INTO Propietario (dni, nombre, apellidos) VALUES ('89088712Y', 'José', 'Rico Pérez');
INSERT INTO Propietario (dni, nombre, apellidos) VALUES ('65665578A', 'Victor', 'Garcia Rayo');
INSERT INTO Propietario (dni, nombre, apellidos) VALUES ('49424503X', 'Jose Manuel', 'Peña García');
INSERT INTO Propietario (dni, nombre, apellidos) VALUES ('09410348J', 'José Antonio', 'Sanchez Araujo');

ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;
