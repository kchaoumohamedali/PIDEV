-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 21 fév. 2022 à 22:26
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `camping`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_id` int(11) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `valide` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UNIQ_6EEAA67DF347EFB` (`produit_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `produit_id`, `prix`, `date`, `valide`) VALUES
(29, 6, NULL, '2022-02-21', NULL),
(30, 6, NULL, '2022-02-21', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220214224517', '2022-02-14 22:45:26', 183),
('DoctrineMigrations\\Version20220216172340', '2022-02-16 17:23:48', 150),
('DoctrineMigrations\\Version20220221215821', '2022-02-21 21:58:26', 52);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_produit` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix_produit` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_expo` date NOT NULL,
  `date_fin` date NOT NULL,
  `disponibilite` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type_action` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imgproduit` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `type_produit`, `prix_produit`, `date_expo`, `date_fin`, `disponibilite`, `type_action`, `imgproduit`) VALUES
(6, 'tente 1', '11111', '2017-01-01', '2017-01-01', 'disponible', '1111111', 'tente-620ae8d29765a.jpeg'),
(8, '55', '66', '2017-01-01', '2017-01-01', 'Disponible', 'vente', '270569409_232143835786925_8214798758997306104_n-620d36984fee9.jpeg'),
(9, '99', '99', '2017-01-01', '2017-01-01', 'non disponible', 'location', '270569409_232143835786925_8214798758997306104_n-620d36a4da1b9.jpeg');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67DF347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
