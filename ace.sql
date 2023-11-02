-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 02 nov. 2023 à 20:48
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ace`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(100) NOT NULL AUTO_INCREMENT,
  `id_user` int(100) NOT NULL,
  `login` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id_admin`),
  KEY `fk_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `id_user`, `login`, `pwd`, `email`) VALUES
(27, 18, 'tn', '123', 'tn@g.com');

-- --------------------------------------------------------

--
-- Structure de la table `categories_sponsor`
--

DROP TABLE IF EXISTS `categories_sponsor`;
CREATE TABLE IF NOT EXISTS `categories_sponsor` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `categories` varchar(20) NOT NULL,
  `id_sponsor` int(11) NOT NULL,
  PRIMARY KEY (`id_cat`),
  KEY `fk_isspo` (`id_sponsor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categories_sponsor`
--

INSERT INTO `categories_sponsor` (`id_cat`, `categories`, `id_sponsor`) VALUES
(3, 'foodtazt', 3);

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

DROP TABLE IF EXISTS `destination`;
CREATE TABLE IF NOT EXISTS `destination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pays` varchar(25) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `id_weather` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `destination`
--

INSERT INTO `destination` (`id`, `pays`, `ville`, `id_weather`) VALUES
(40, 'bizerte', 'ras  jebel', 0),
(41, 'tuniss', 'ariana', 0),
(42, 'bizerte', 'rafraf', 0),
(43, 'tunis', 'bardo', 0),
(44, 'bizerte', 'mateur', 0);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `duree` float NOT NULL,
  `prix` float NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `nom_event` varchar(25) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_event`),
  KEY `f_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_event`, `duree`, `prix`, `date_deb`, `date_fin`, `nom_event`, `id`) VALUES
(2, 1, 11, '2023-03-16', '2023-03-24', 'rr', 25);

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `etoile` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `place_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_place` (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `nom`, `etoile`, `type`, `place_id`) VALUES
(22, 'tulib2', '*****', 'touristique', NULL),
(42, 'zaeazrar', '***', 'azeaze', NULL),
(43, 'aezaz', '***', 'zeee', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `id_post` int(11) NOT NULL,
  `contenu` varchar(1000) NOT NULL,
  `note` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id_message`),
  KEY `f_post` (`id_post`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id_message`, `id_post`, `contenu`, `note`) VALUES
(1, 3, 'azrazr', 1),
(2, 2, 'zetzet', 1);

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id_post` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(256) NOT NULL,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id_client` int(11) NOT NULL,
  `id_prom` int(11) NOT NULL AUTO_INCREMENT,
  `remise` int(11) NOT NULL,
  PRIMARY KEY (`id_prom`),
  KEY `fr_userrr` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `publicite`
--

DROP TABLE IF EXISTS `publicite`;
CREATE TABLE IF NOT EXISTS `publicite` (
  `id_pub` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) NOT NULL,
  `id_event` int(11) NOT NULL,
  PRIMARY KEY (`id_pub`),
  KEY `ffff_event` (`id_event`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_res` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(100) NOT NULL,
  `id_event` int(11) NOT NULL,
  `qte` int(11) NOT NULL,
  PRIMARY KEY (`id_res`),
  KEY `f_user` (`id_user`),
  KEY `f_event` (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_res`, `id_user`, `id_event`, `qte`) VALUES
(1, 18, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
CREATE TABLE IF NOT EXISTS `sponsor` (
  `id_sponsor` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` varchar(20) NOT NULL,
  `duree_contrat` int(11) NOT NULL,
  `date_debc` date NOT NULL,
  `date_finc` date NOT NULL,
  PRIMARY KEY (`id_sponsor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `sponsor`
--

INSERT INTO `sponsor` (`id_sponsor`, `intitule`, `duree_contrat`, `date_debc`, `date_finc`) VALUES
(3, 'ara', 1, '2023-03-09', '2023-03-10');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_user` int(100) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `number_user` int(200) NOT NULL,
  `address_user` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `user_name`, `pwd`, `email`, `login`, `number_user`, `address_user`, `role`) VALUES
(18, 'foulen', '123456', 'foulen@foulen.com', 'foulennnn', 26579201, 'heyy', 'Admin'),
(19, 'mohaled aziz', '123456789', 'azyyzazyzy16@gmail.com', '123', 7, 'nahj allisa', 'admin'),
(20, 'mohaled aziz', '123456789', 'azyyzazyzy16@gmail.com', '123', 7, 'nahj allisa', 'User'),
(22, 'nidhal', '123456', 'nidhal@gmail.com', 'nidhal', 29225165, 'maroc', 'User'),
(23, 'aze', '68ff94e22742c52cf9439fcb176791a8edb4ab41142344f5cb506a2e2c998cab', 'a@gm.com', 'avc', 29225165, 'marco', 'user');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id_user`);

--
-- Contraintes pour la table `categories_sponsor`
--
ALTER TABLE `categories_sponsor`
  ADD CONSTRAINT `fk_isspo` FOREIGN KEY (`id_sponsor`) REFERENCES `sponsor` (`id_sponsor`);

--
-- Contraintes pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `fk_place` FOREIGN KEY (`place_id`) REFERENCES `destination` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `fr_userrr` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
