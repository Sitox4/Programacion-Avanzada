-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "paproject" database.
-------------------------------------------------------------------------------

INSERT INTO User(userName, password, firstName, lastName, email, role) VALUES
    ('viewer', '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG', 'David', 'Fernandez', 'd.fernandez4@udc.es', 0),
    ('ticketseller', '$2a$10$8o34vbwlRURkBGETvQzr8OCuPrk52E.j2ilm4KGKPrwNR89eNV/YG', 'Daniel', 'Lago', 'dani.lago.iglesias@udc.es', 1);

INSERT INTO Sala(nombre, capacidad) VALUES
    ('Cinesa', 9),
    ('Multicines', 15);

INSERT INTO Pelicula(titulo, resumen, duracion) VALUES
    ('Creed III', 'Adonis Creed has been thriving in both his career and family life. When childhood friend and former boxing prodigy Damian resurfaces after serving a long sentence in prison, he is eager to prove that he deserves his shot in the ring.', 117),
    ('Momias', 'In the bowels of the earth, there is a city of mummies. By imperial mandate, Princess Nefer must marry Thut, a former chariot driver. No one wants marriage, but the designs of the gods are irrevocable.', 88);

INSERT INTO Sesion(fecha, precio ,peliculaId, salaId, ticketsLibres, version) VALUES
    (DATE_ADD(DATE(NOW()), INTERVAL '0 00:05' DAY_MINUTE), 6.99, 1, 2, 10, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '0 23:55' DAY_MINUTE), 6.99, 2, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '1 17:30' DAY_MINUTE), 6.99, 2, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '1 19:30' DAY_MINUTE), 6.99, 1, 2, 15, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '2 18:00' DAY_MINUTE), 4.99, 1, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '2 21:00' DAY_MINUTE), 4.99, 1, 2, 15, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '3 17:30' DAY_MINUTE), 6.99, 2, 2, 15, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '3 21:00' DAY_MINUTE), 6.99, 1, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '4 17:30' DAY_MINUTE), 6.99, 1, 2, 15, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '4 21:00' DAY_MINUTE), 6.99, 2, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '5 17:30' DAY_MINUTE), 6.99, 2, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '5 21:00' DAY_MINUTE), 6.99, 1, 2, 15, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '6 17:30' DAY_MINUTE), 6.99, 1, 1, 9, 0),
    (DATE_ADD(DATE(NOW()), INTERVAL '6 21:00' DAY_MINUTE), 6.99, 2, 2, 15, 0);

INSERT INTO Compra(cantidad, tarjeta, userId, sesionId, fecha, entregada) VALUES
    (2, "1234567891234567", 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0' DAY), false),
    (3, "1234567891234567", 1, 1, DATE_ADD(DATE(NOW()), INTERVAL '0' DAY), false);
