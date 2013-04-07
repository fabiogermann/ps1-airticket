--
-- Daten für Tabelle `benutzer`
--

INSERT INTO `benutzer` (`email`, `passwort`, `vorname`, `name`, `strasse`, `ort`, `postleitzahl`, `telefonnummer`, `land`) VALUES
('ab10@bc10.com', 'cb51b5ab83127c02f8795b77da5bcab4', 'Carla', 'Grimm', 'Trapezstrasse 1400 b', 'Nirgendshausen', 0, '123456789', 'Schweiz'),
('ab11@bc11.com', '09c078856e3e0b93cec73c6ae953d0a7', 'Jürg', 'Jürgesen', 'Hirschstrasse 25', 'Andwil', 9205, '123456789799', 'Schweiz'),
('ab12@bc12.com', '85663396e1605fb0b12fa5bb5851285a', 'Karsten', 'Speckkopf', 'Tragikweg', 'Dresden', 58000, '1234567897899', 'Deutschland'),
('ab13@bc13.com', 'f93821e0d801990598369f88fb8df9cd', 'Falko', 'Dorado', 'Bischofsstrasse 39', 'Kradolf', 8954, '123456489', 'Schweiz'),
('ab14@bc14.com', '1017451f85c00fda31bc809beb177c87', 'Django', 'Flamengo', 'Bengalstreet 1', 'London', 2000, '12314564789', 'Great Britain'),
('ab15@bc15.com', '4e6e9749bf4cdfc46c87de645b1232aa', 'Yannick', 'Bosshardt', 'Draunweg 1b', 'Bergersdorf', 9288, '123456978999', 'Schweiz'),
('ab1@bc1.com', 'bf9a3e94214c40bc26ee50186f5eb6cf', 'Ali', 'Bengali', 'Langstrasse', 'Bimbamdorf', 9200, '1234567891011', 'Schweiz'),
('ab2@bc2.com', '41f1cd5867bbee46a3c3d0c3b2318804', 'Diana', 'Rumpf', 'Stumpfstrasse', 'Zürich', 8800, '1234567891100', 'Schweiz'),
('ab3@bc3.com', '430ccd3f86378378991d942e4e107ff7', 'Dirk', 'Braun', 'Am Weg 2', 'Goldach', 7800, '123456789101112', 'Schweiz'),
('ab4@bc4.com', 'fc4b430cdbcf9972ec56b2399ee82f1a', 'Dieter', 'Bodo', 'Hausmeisterweg 10', 'Dackelhausen', 44000, '123456789789', 'Deutschland'),
('ab5@bc5.com', 'fab2b7073fe528fd73e8ecaec7188350', 'Ronnie', 'Coleman', 'Gaschdestrasse', 'Muckibude', 1337, 123456789798, 'Schweiz'),
('ab6@bc6.com', '826c6c0b89bdc9c5eeed023c6ef121f3', 'Hans', 'Wurst', 'Brotstrasse 98', 'Baden', 8000, '123456789789', 'Schweiz'),
('ab7@bc7.com', 'f9e209dc270a1a012c9667b611c3b717', 'Ingo', 'Olek', 'Bolekweg 4', 'Dönershausen', 9800, '12345647', 'Schweiz'),
('ab8@bc8.com', '28d46f969d3d8b9615a39af40e6d92ac', 'Hanna', 'Montana', 'Luegwegstrasse', 'Zürich', 8800, '123456789', 'Schweiz'),
('ab9@bc9.com', 'c68f701f6502463259d99fcf37887f35', 'Birte', 'Kickeborg', 'Langnauweg 2', 'Langnau', 2000, '1253456789798', 'Schweiz');


--
-- Daten für Tabelle `flugzeug`
--

INSERT INTO `flugzeug` (`modell`, `reihe_business`, `sitze_business`, `reihe_economy`, `sitze_economy`) VALUES
('Airbus A300', 10, 10, 30, 12),
('Airbus A320', 15, 8, 32, 9),
('Airbus A340', 8, 12, 20, 8),
('Airbus A350', 10, 8, 20, 11),
('Airbus A380', 25, 8, 45, 10),
('Beechcraft Model 17', 6, 2, 0, 0),
('Beechcraft Model 18', 8, 2, 0, 0),
('Bell 212', 0, 0, 2, 2),
('Bell 222', 0, 0, 2, 3),
('Blohm & Voss BV 238', 2, 4, 2, 6),
('Boeing 717', 10, 8, 15, 10),
('Boeing 727', 2, 5, 7, 8),
('Boeing 737', 5, 5, 10, 10),
('Boeing 757', 5, 7, 12, 12),
('Boeing 777', 20, 5, 30, 8);


--
-- Daten für Tabelle `flughafen`
--

INSERT INTO `flughafen` (`code`, `name`, `stadt`, `land`) VALUES
('ABS', 'Flughafen Abu Simbel', 'Abu Simbel', 'Ägypten'),
('ADJ', 'Flughafen Marka International', 'Amman', 'Jordanien'),
('FDF', 'Internationaler Flughafen Martinique Aimé Césaire', 'Fort-de-France', 'Frankreich'),
('FDH', 'Bodensee-Airport Friedrichshafen', 'Friedrichshafen', 'Deutschland'),
('FKS', 'Flughafen Fukushima', 'Fukushima', 'Japan'),
('FLF', 'Flugplatz Flensburg-Schäferhaus', 'Flensburg', 'Deutschland'),
('JMK', 'Flughafen Mykonos', 'Mykonos', 'Griechenland'),
('LAX', 'Los Angeles International Airport', 'Los Angeles', 'USA'),
('LHR', 'London-Heathrow', 'London', 'Grossbritannien'),
('OXF', 'Flughafen London Oxford', 'Oxford', 'Grossbritannien'),
('PPT', 'Flughafen Tahiti', 'Papeete', 'Französisch-Polynesien'),
('RGN', 'Internationaler Flughafen Rangun', 'Yangoon', 'Myanmar'),
('RKV', 'Flughafen Reykjavík', 'Reykjavík', 'Island'),
('RNS', 'Flughafen Rennes', 'Rennes', 'Frankreich'),
('SGD', 'Flughafen Sønderborg', 'Sønderborg', 'Dänemark'),
('SJJ', 'Flughafen Sarajevo', 'Sarajevo', 'Bosnien und Herzegowina'),
('USM', 'Flughafen Ko Samui', 'Koh Samui', 'Thailand'),
('VVO', 'Flughafen Wladiwostok', 'Wladiwostok', 'Russland'),
('ZHR', 'Flughafen Zürich', 'Kloten', 'Schweiz'),
('ZSA', 'Flughafen San Salvador', 'San Salvador', 'Bahamas');


--
-- Daten für Tabelle `rolle`
--

INSERT INTO `rolle` (`email`, `Rollen`, `benutzer`) VALUES
('ab1@bc1.com', 'Benutzer', 'ab1@bc1.com'),
('ab2@bc2,com', 'Administrator', 'ab2@bc2.com');


--
-- Daten für Tabelle `flug`
--

INSERT INTO `flug` (`nummer`, `von`, `nach`, `abflugzeit`, `ankunftzeit`, `dauer`, `businesspreis`, `economypreis`, `geplant`, `flugzeug`) VALUES
('1', 'ADJ', 'FKS', '12:35:00', '13:35:00', '01:00:00', 250, 150, 'täglich', 'Beechcraft Model 17'),
('1B', 'SGD', 'ZHR', '17:35:00', '18:35:00', '01:00:00', 450, 180, 'wöchentlich', 'Boeing 757'),
('3', 'FLF', 'OXF', '08:27:00', '17:35:00', '09:08:00', 1500, 899, 'täglich', 'Boeing 757'),
('4', 'RNS', 'VVO', '04:57:00', '12:07:00', '07:50:00', 1200, 789, 'täglich', 'Blohm & Voss BV 238'),
('5', 'ABS', 'ADJ', '14:35:00', '04:35:00', '14:00:00', 2500, 999, 'wöchentlich', 'Airbus A380');


--
-- Daten für Tabelle `ticket`
--

INSERT INTO `ticket` (`id`, `abflugdatum`, `sitzreihe`, `sitzspalte`, `klasse`, `flug`, `benutzer`) VALUES
(1, '2013-02-22', 2, 4, 'Economy', '1', 'ab1@bc1.com'),
(2, '2013-02-03', 1, 5, 'Economy', '1', 'ab1@bc1.com'),
(3, '2013-02-10', 2, 3, 'Economy', '1', 'ab3@bc3.com'),
(4, '2013-02-05', 4, 5, 'Economy', '1', 'ab5@bc5.com'),
(5, '2013-02-26', 11, 2, 'Economy', '1B', 'ab5@bc5.com'),
(6, '2013-02-07', 7, 5, 'Economy', '1B', 'ab13@bc13.com'),
(7, '2013-02-15', 8, 2, 'Economy', '3', 'ab5@bc5.com'),
(8, '2013-02-04', 3, 7, 'Business', '1B', 'ab14@bc14.com'),
(9, '2013-02-27', 12, 3, 'Business', '1B', 'ab12@bc12.com'),
(10, '2013-02-15', 3, 5, 'Business', '3', 'ab7@bc7.com'),
(11, '2013-02-01', 8, 7, 'Business', '5', 'ab5@bc5.com'),
(12, '2013-02-22', 3, 9, 'Business', '5', 'ab11@bc11.com');


--
-- Admin 
--

INSERT INTO benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) VALUES ('root@air.com', md5('root@air.com'), 'Vorname','Name','Strasse','Ort',0,0,'Land');
INSERT INTO  benutzer (email, passwort, vorname, name, strasse, ort, postleitzahl, telefonnummer, land) VALUES ('user@air.com', md5('user@air.com') ,'UserVorname','UserName','UserStrasse','UserOrt', 8000, 052261000000,'UserLand');
INSERT INTO  rolle (email, Rollen, benutzer) VALUES ('user@air.com','user','user@air.com');
INSERT INTO rolle (email, Rollen, benutzer) VALUES ('root@air.com','manager-gui','root@air.com');