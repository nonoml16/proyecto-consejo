INSERT INTO Usuario (id, username, password, consejero) VALUES (1, 'admin', '{bcrypt}$2a$10$ps6QN4cRlMvAZmkkBTygyOTrZAjT1Osxs7M4ElwdcOkEAqmO376Be', true);
INSERT INTO Usuario (id, username, password, consejero) VALUES (2, 'user', '{bcrypt}$2a$10$8FLnEJGxM7Iz4IpcoyPq.u5uCnVDxmUpi4lhb2c0gLK8ZNqIB4/4G', false);

INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (1, '89088712Y', 'Francisco', 'Vélez de Luna');
INSERT INTO Propietario (id, dni, nombre, apellidos) VALUES (2, '21135681S', 'Antonio', 'Martínez López');

INSERT INTO Sector (id, localizacion, precio) VALUES (1, 'Campana', 299);
INSERT INTO Sector (id, localizacion, precio) VALUES (2, 'Sierpes', 249);
INSERT INTO Sector (id, localizacion, precio) VALUES (3, 'Plaza de San Francisco', 1099);
INSERT INTO Sector (id, localizacion, precio) VALUES (4, 'Avenida', 199);
INSERT INTO Sector (id, localizacion, precio) VALUES (5, 'Catedral', 149);
INSERT INTO Sector (id, localizacion, precio) VALUES (6, 'Plaza Virgen de los Reyes', 289);

ALTER SEQUENCE hibernate_sequence RESTART WITH 1000;
