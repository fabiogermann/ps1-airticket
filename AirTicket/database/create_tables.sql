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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `flug` (
  `nummer` varchar(50) NOT NULL,
  `von` varchar(50) NOT NULL,
  `nach` varchar(50) NOT NULL,
  `abflugzeit` time NOT NULL,
  `ankunftzeit` time NOT NULL,
  `dauer` time NOT NULL,
  `businesspreis` int(11) NOT NULL,
  `economypreis` int(11) NOT NULL,
  `flugzeug` varchar(50) NOT NULL,
  PRIMARY KEY (`nummer`),
  KEY `flugzeug` (`flugzeug`),
  KEY `von` (`von`),
  KEY `nach` (`nach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `flughafen` (
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `stadt` varchar(50) NOT NULL,
  `land` varchar(50) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `flugzeug` (
  `modell` varchar(50) NOT NULL,
  `reihe_business` int(11) DEFAULT NULL,
  `sitze_business` int(11) DEFAULT NULL,
  `reihe_economy` int(11) DEFAULT NULL,
  `sitze_economy` int(11) DEFAULT NULL,
  PRIMARY KEY (`modell`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rolle` (
  `rollen` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  KEY `benutzer` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `geplant` (
  `nummer` varchar(50) NOT NULL,
  `geplant` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE  `ticket` CHANGE  `id`  `id` INT( 11 ) NOT NULL AUTO_INCREMENT;

ALTER TABLE `flug`
  ADD CONSTRAINT `flug_flugzeug` FOREIGN KEY (`flugzeug`) REFERENCES `flugzeug` (`modell`),
  ADD CONSTRAINT `flug_flughafen_n` FOREIGN KEY (`nach`) REFERENCES `flughafen` (`code`),
  ADD CONSTRAINT `flug_flughafen_v` FOREIGN KEY (`von`) REFERENCES `flughafen` (`code`);

ALTER TABLE `rolle`
  ADD CONSTRAINT `rolle_benutzer` FOREIGN KEY (`email`) REFERENCES `benutzer` (`email`);

ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_flug` FOREIGN KEY (`flug`) REFERENCES `flug` (`nummer`),
  ADD CONSTRAINT `ticket_benutzer` FOREIGN KEY (`benutzer`) REFERENCES `benutzer` (`email`);

ALTER TABLE `geplant`
  ADD CONSTRAINT `geplant_flug` FOREIGN KEY (`nummer`) REFERENCES `flug` (`nummer`);