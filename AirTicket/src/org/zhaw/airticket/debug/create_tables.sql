CREATE TABLE IF NOT EXISTS `benutzer` (
  `email` varchar(50) NOT NULL,
  `passwort` varchar(50) NOT NULL,
  `vorname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `strasse` varchar(50) NOT NULL,
  `ort` varchar(50) NOT NULL,
  `postleitzahl` int(11) NOT NULL,
  `telefonnummer` varchar(50) NOT NULL,
  `land` varchar(50) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `flug` (
  `nummer` varchar(50) NOT NULL,
  `von` varchar(50) NOT NULL,
  `nach` varchar(50) NOT NULL,
  `abflugzeit` time NOT NULL,
  `ankunftzeit` time NOT NULL,
  `dauer` time NOT NULL,
  `businesspreis` int(11) NOT NULL,
  `economypreis` int(11) NOT NULL,
  `geplant` varchar(50) NOT NULL,
  `flugzeug` varchar(50) NOT NULL,
  PRIMARY KEY (`nummer`),
  KEY `flugzeug` (`flugzeug`),
  KEY `von` (`von`),
  KEY `nach` (`nach`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `flughafen` (
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `stadt` varchar(50) NOT NULL,
  `land` varchar(50) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `flugzeug` (
  `modell` varchar(50) NOT NULL,
  `reihe_business` int(11) DEFAULT NULL,
  `sitze_business` int(11) DEFAULT NULL,
  `reihe_economy` int(11) DEFAULT NULL,
  `sitze_economy` int(11) DEFAULT NULL,
  PRIMARY KEY (`modell`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `rolle` (
  `email` varchar(50) NOT NULL,
  `Rollen` varchar(50) NOT NULL,
  `benutzer` varchar(50) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `benutzer` (`benutzer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL,
  `abflugdatum` date NOT NULL,
  `sitzreihe` int(11) NOT NULL,
  `sitzspalte` int(11) NOT NULL,
  `klasse` varchar(50) NOT NULL,
  `flug` varchar(50) NOT NULL,
  `benutzer` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `benutzer` (`benutzer`),
  KEY `flug` (`flug`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `flug`
  ADD CONSTRAINT `flug_ibfk_1` FOREIGN KEY (`flugzeug`) REFERENCES `flugzeug` (`modell`),
  ADD CONSTRAINT `flug_ibfk_5` FOREIGN KEY (`nach`) REFERENCES `flughafen` (`code`),
  ADD CONSTRAINT `flug_ibfk_4` FOREIGN KEY (`von`) REFERENCES `flughafen` (`code`);

ALTER TABLE `rolle`
  ADD CONSTRAINT `rolle_ibfk_1` FOREIGN KEY (`benutzer`) REFERENCES `benutzer` (`email`);

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`flug`) REFERENCES `flug` (`nummer`),
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`benutzer`) REFERENCES `benutzer` (`email`);
