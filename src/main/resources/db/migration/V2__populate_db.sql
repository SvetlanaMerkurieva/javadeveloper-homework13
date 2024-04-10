INSERT INTO client
(name)
VALUES
('Valentyn Perlovko'),
('Ruslan Velychko'),
('Tetyana Mastoluk'),
('Iryna Skrypnik'),
('Yriy Ignor'),
('Katerina Sosedko'),
('Anton Peremozhets'),
('Oleksander Potroshko'),
('Volodymyr Zverev'),
('Svitlana Merkurieva');

INSERT INTO planet
(id, name)
VALUES
('MARS', 'Mars'),
('MERK', 'Merkuriy'),
('EAR', 'Earth'),
('SAT', 'Saturn'),
('URAN', 'Uran');

INSERT INTO ticket
(client_id, from_planet_id, to_planet_id)
VALUES
(1, 'EAR', 'MARS'),
(2, 'EAR', 'MERK'),
(3, 'EAR', 'SAT'),
(4, 'EAR', 'URAN'),
(5, 'EAR', 'MARS'),
(6, 'EAR', 'MERK'),
(7, 'EAR', 'SAT'),
(8, 'EAR', 'URAN'),
(9, 'EAR', 'MARS'),
(10, 'MERK', 'EAR');