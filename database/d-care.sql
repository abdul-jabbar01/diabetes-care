-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jul 19, 2019 at 01:06 PM
-- Server version: 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `d-care`
--
CREATE DATABASE IF NOT EXISTS `d-care` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `d-care`;

-- --------------------------------------------------------

--
-- Table structure for table `device_configurations`
--

CREATE TABLE `device_configurations` (
  `deviceId` int(11) NOT NULL,
  `remGlucagonLevel` int(11) NOT NULL,
  `remInsulinLevel` int(11) NOT NULL,
  `remBatteryLevel` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `autoMode` tinyint(1) NOT NULL,
  `maxDose` int(11) DEFAULT NULL,
  `sensitivityModel` int(11) DEFAULT NULL,
  `deviceSetupByDoc` tinyint(1) DEFAULT NULL,
  `patientId` int(11) NOT NULL,
  `breakFast` varchar(200) DEFAULT NULL,
  `breakFastCarb` int(11) DEFAULT NULL,
  `Lunch` varchar(200) DEFAULT NULL,
  `LunchCarb` int(11) DEFAULT NULL,
  `Dinner` varchar(200) DEFAULT NULL,
  `DinnerCarb` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `device_configurations`
--

INSERT INTO `device_configurations` (`deviceId`, `remGlucagonLevel`, `remInsulinLevel`, `remBatteryLevel`, `state`, `autoMode`, `maxDose`, `sensitivityModel`, `deviceSetupByDoc`, `patientId`, `breakFast`, `breakFastCarb`, `Lunch`, `LunchCarb`, `Dinner`, `DinnerCarb`) VALUES
(21, 100, 100, 100, 1, 1, 50, 1500, 1, 14, 'Wheat roti-62', 89, 'Brown rice, boiled-68', 104, 'Yogurt, fruit-41', 70),
(22, 100, 100, 100, 1, 1, 50, 1500, 1, 15, 'Wheat roti-62', 80, 'Brown rice, boiled-68', 75, 'Potato, french fries-63', 104);

-- --------------------------------------------------------

--
-- Table structure for table `patientHistory`
--

CREATE TABLE `patientHistory` (
  `id` int(11) NOT NULL,
  `patientId` int(11) DEFAULT NULL,
  `time` varchar(200) DEFAULT NULL,
  `bglValue` int(11) DEFAULT NULL,
  `injectedUnits` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patientHistory`
--

INSERT INTO `patientHistory` (`id`, `patientId`, `time`, `bglValue`, `injectedUnits`) VALUES
(205, 11, 'Wednesday,17 July 2019 8:00', 112, 0),
(206, 11, 'Wednesday,17 July 2019 8:10', 120, 0.25),
(207, 11, 'Wednesday,17 July 2019 8:20', 125, 0.44),
(208, 11, 'Wednesday,17 July 2019 8:30', 125, 0.55),
(209, 11, 'Wednesday,17 July 2019 8:40', 135, 1),
(210, 11, 'Wednesday,17 July 2019 8:50', 135, 0),
(211, 11, 'Wednesday,17 July 2019 9:00', 136, 0),
(212, 11, 'Wednesday,17 July 2019 9:10', 145, 0),
(213, 11, 'Wednesday,17 July 2019 9:20', 140, 0),
(214, 11, 'Wednesday,17 July 2019 9:30', 140, 0),
(215, 11, 'Wednesday,17 July 2019 9:40', 140, 0),
(216, 11, 'Wednesday,17 July 2019 9:50', 140, 0),
(217, 11, 'Wednesday,17 July 2019 10:00', 142, 0.9),
(218, 11, 'Wednesday,17 July 2019 10:10', 144, 1.1),
(219, 11, 'Wednesday,17 July 2019 10:20', 145, 0),
(220, 11, 'Wednesday,17 July 2019 10:30', 155, 0),
(221, 11, 'Wednesday,17 July 2019 10:40', 156, 0),
(222, 11, 'Wednesday,17 July 2019 10:50', 155, 0),
(223, 11, 'Wednesday,17 July 2019 11:00', 156, 1.5),
(224, 11, 'Wednesday,17 July 2019 11:10', 157, 0),
(225, 11, 'Wednesday,17 July 2019 11:20', 159, 0.4),
(226, 11, 'Wednesday,17 July 2019 11:30', 166, 0.5),
(227, 11, 'Wednesday,17 July 2019 11:40', 170, 0),
(228, 11, 'Wednesday,17 July 2019 11:50', 179, 1),
(229, 11, 'Wednesday,17 July 2019 12:00', 164, 0.2);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patientId` int(11) NOT NULL,
  `patientUserId` int(11) NOT NULL,
  `patientDoctorEmail` varchar(100) NOT NULL,
  `patientCareTakerEmail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patientId`, `patientUserId`, `patientDoctorEmail`, `patientCareTakerEmail`) VALUES
(14, 27, 'doctor@gmail.com', ''),
(15, 31, 'doctor@gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `userFirstName` varchar(250) NOT NULL,
  `userLastName` varchar(250) NOT NULL,
  `userEmail` varchar(250) NOT NULL,
  `userPassword` varchar(250) NOT NULL,
  `userType` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userFirstName`, `userLastName`, `userEmail`, `userPassword`, `userType`) VALUES
(26, 'Alex', 'John', 'doctor@gmail.com', '1f0160076c9f42a157f0a8f0dcc68e02ff69045b', 'Doctor'),
(27, 'Abdul', 'Jabbar', 'abdul@gmail.com', '3fb86591025780f719fcd21d6fd06a8330659670', 'Patient'),
(28, 'Elizabeth', 'John', 'nurse@gmail.com', '285f9a003f671c2486a3f87ea1ad5e37699ebc38', 'Nurse'),
(29, 'Maria', 'John', 'care@gmail.com', 'ec1deb95d610f97b2e9fd3d7246c133ad07b26cf', 'Care Taker'),
(30, 'Abdul', 'Jabbar', 'abdul1@gmail.com', '3fb86591025780f719fcd21d6fd06a8330659670', 'Doctor'),
(31, 'patient', 'patient', 'patient@gmail.com', 'b1b0b8de8a6228f6501c0560365d3a7d74ffcd8e', 'Patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `device_configurations`
--
ALTER TABLE `device_configurations`
  ADD PRIMARY KEY (`deviceId`);

--
-- Indexes for table `patientHistory`
--
ALTER TABLE `patientHistory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patientId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `device_configurations`
--
ALTER TABLE `device_configurations`
  MODIFY `deviceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `patientHistory`
--
ALTER TABLE `patientHistory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=230;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patientId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
