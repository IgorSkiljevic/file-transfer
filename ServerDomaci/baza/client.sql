-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 14, 2017 at 12:45 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `client`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `numberOfDownloads` int(11) NOT NULL,
  `numberOfUploads` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `username`, `password`, `numberOfDownloads`, `numberOfUploads`) VALUES
(21, 'a', '3', 6, 2),
(22, 'janaradivojevoic', 'janajana', 0, 0),
(23, 'jana', '2', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `fajl`
--

DROP TABLE IF EXISTS `fajl`;
CREATE TABLE IF NOT EXISTS `fajl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(10000) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `postavioUser` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fajl`
--

INSERT INTO `fajl` (`id`, `text`, `ime`, `code`, `postavioUser`) VALUES
(19, 'sdsdd', 'asdasd', '9', 'a'),
(20, 'dkfdsgfuodshfkjdshf', 'janin fajl', '4', 'jana'),
(21, 'sdsd', 'sd', 'vPjNgiQsUH', 'a');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
