-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 31 mars 2022 à 05:13
-- Version du serveur :  10.4.19-MariaDB
-- Version de PHP : 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `systable`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(5) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `phone_code` int(5) NOT NULL,
  `phone_number` int(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(50) NOT NULL,
  `profile` enum('ADMIN','ASSISTANT','CHIEF','RESPONSIBLE','TEACHER','ACCOUNTANT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `last_name`, `phone_code`, `phone_number`, `email`, `address`, `profile`) VALUES
(1, 'admin', 'passer', 'John', 'DOE', 221, 770000000, 'john@exemple.com', 'Addr', 'ADMIN'),
(2, 'baby', 'pass', 'Baby', 'DOE', 0, 0, 'baby@example.com', 'Addr', 'RESPONSIBLE'),
(8, 'bien', 'aze', 're', 'rer', 4554, 58585, 'ree@.com', 'erre', 'RESPONSIBLE'),
(15, 'nuit', 'aqw', 'ajout', 'ajouter', 245, 784964, 'ajout@.com', 'mjkgr', 'CHIEF'),
(18, 'test10', 'tester', 'test8', 'TEST8', 7, 7, '0@email.com', 'addr', 'ACCOUNTANT'),
(19, 'assistant1', '123', 'Admin2', 'CRUD', 213, 487952, 'admin2@.com', 'addr', 'ASSISTANT'),
(22, 'admin5', 'az', 'azer', 'bzer', 54874, 586455, '11@.', 'addre', 'ASSISTANT'),
(23, 'teacher', 'aa', 'a', 'a', 548787, 465878, '454@.', 'a', 'TEACHER'),
(24, 'acc', 'p', '546486', '5468468', 545848, 84678864, '468@.', 'z', 'ACCOUNTANT'),
(26, 'admin27', 'a', 'Admin', 'Admin', 546584, 4898748, '489@.', 'uua', 'ADMIN'),
(27, 'admin3', 'aze', 'test', 'ter', 23, 46878, 'f@.c', 'azer', 'ADMIN'),
(28, 'chef2', 'aqw', 'Chief', 'chef', 120, 58492, '@q.c', 'fred', 'CHIEF'),
(29, 'admin8', 'aze', 'administrateur', 'ade', 452, 368, 'ad@.', 'azed', 'ADMIN'),
(32, '4554', '1', '477867', '48848', 48864, 48848, '4848@.', '4584467', 'TEACHER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `phone_number` (`phone_number`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
