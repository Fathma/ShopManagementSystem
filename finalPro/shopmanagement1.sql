-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2017 at 08:16 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopmanagement1`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `uname` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`uname`, `pass`) VALUES
('abc', '123');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `eid` int(50) NOT NULL,
  `attendance` int(50) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`eid`, `attendance`, `date`) VALUES
(3, 1, '2017-10-16'),
(3, 1, '2017-10-20'),
(3, 1, '2017-10-22'),
(3, 1, '2017-10-26'),
(4, 1, '2017-10-28'),
(3, 1, '2017-10-28'),
(3, 1, '2017-11-04'),
(3, 1, '2017-11-29'),
(4, 1, '2017-11-30'),
(3, 1, '2017-11-30'),
(5, 1, '2017-11-30'),
(4, 1, '2017-12-05'),
(5, 1, '2017-12-05');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `receiptNo` int(50) NOT NULL,
  `customername` varchar(50) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `date` date NOT NULL,
  `totalAmount` float NOT NULL,
  `discountonTotal` int(50) NOT NULL,
  `totalwithDiscount` int(50) NOT NULL,
  `cash` int(50) NOT NULL,
  `back` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`receiptNo`, `customername`, `phone`, `date`, `totalAmount`, `discountonTotal`, `totalwithDiscount`, `cash`, `back`) VALUES
(1, '', '', '2017-11-30', 5890, 0, 5890, 6000, 110),
(2, '', '', '2017-11-30', 11780, 0, 11780, 12000, 220),
(3, '', '', '2017-11-30', 1190, 0, 1190, 1200, 10),
(4, 'sameka chowdhury', '1234567899', '2017-11-30', 5890, 0, 5890, 6000, 110);

-- --------------------------------------------------------

--
-- Table structure for table `billingwithitems`
--

CREATE TABLE `billingwithitems` (
  `ReceiptNo` int(100) NOT NULL,
  `pid` int(50) NOT NULL,
  `Color` varchar(50) NOT NULL,
  `Size` varchar(50) NOT NULL,
  `quantity` int(4) NOT NULL,
  `productTotal` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billingwithitems`
--

INSERT INTO `billingwithitems` (`ReceiptNo`, `pid`, `Color`, `Size`, `quantity`, `productTotal`, `date`) VALUES
(1, 34, 'DEEP BLUE', 'S', 1, 5890, '2017-11-30'),
(2, 34, 'DEEP BLUE', 'S', 1, 5890, '2017-11-30'),
(2, 34, 'DEEP BLUE', 'XL', 1, 5890, '2017-11-30'),
(3, 35, 'SOLID GREEN', 'L', 1, 1190, '2017-11-30'),
(4, 34, 'DEEP BLUE', 'L', 1, 5890, '2017-11-30');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `catN` varchar(50) NOT NULL,
  `cid` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`catN`, `cid`) VALUES
('MEN''S', 11);

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

CREATE TABLE `cost` (
  `date` date NOT NULL,
  `cost` int(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dailyrecord`
--

CREATE TABLE `dailyrecord` (
  `sl` int(11) NOT NULL,
  `date` date NOT NULL,
  `expenses` int(11) DEFAULT NULL,
  `income` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dailyrecord`
--

INSERT INTO `dailyrecord` (`sl`, `date`, `expenses`, `income`) VALUES
(1, '2017-11-30', 450, 24750);

-- --------------------------------------------------------

--
-- Table structure for table `emp`
--

CREATE TABLE `emp` (
  `ename` varchar(50) NOT NULL,
  `eid` int(50) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `address` varchar(60) NOT NULL,
  `nid` varchar(50) DEFAULT NULL,
  `perdaysalary` int(50) NOT NULL,
  `position` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emp`
--

INSERT INTO `emp` (`ename`, `eid`, `phone`, `address`, `nid`, `perdaysalary`, `position`) VALUES
('sharly', 4, '+8801719325678', 'kollanpur', '', 400, 'intern'),
('Fathma Siddique', 5, '+8801686407991', 'Mirpur, Dhaka', '', 333, 'intern'),
('Sameka', 6, '+8801685991678', 'bosila, mohammodpur', '', 500, 'sells man');

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `date` date NOT NULL,
  `amount` int(11) NOT NULL,
  `purpose` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`date`, `amount`, `purpose`) VALUES
('2017-11-30', 400, 'emp 4'),
('2017-11-30', 50, 'snacks');

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE `income` (
  `date` date NOT NULL,
  `amount` int(100) NOT NULL,
  `purpose` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`date`, `amount`, `purpose`) VALUES
('2017-11-30', 5800, 'rcpt1'),
('2017-11-30', 90, 'rcpt1'),
('2017-11-30', 6980, 'rcpt2'),
('2017-11-30', 4800, 'rcpt2'),
('2017-11-30', 1190, 'rcpt3'),
('2017-11-30', 5890, 'rcpt4');

-- --------------------------------------------------------

--
-- Table structure for table `monthly`
--

CREATE TABLE `monthly` (
  `month` varchar(10) NOT NULL,
  `year` year(4) NOT NULL,
  `income` int(60) NOT NULL,
  `expenses` int(60) NOT NULL,
  `profit` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monthly`
--

INSERT INTO `monthly` (`month`, `year`, `income`, `expenses`, `profit`) VALUES
('12', 2017, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `pname` varchar(50) NOT NULL,
  `cid` int(50) NOT NULL,
  `pid` int(50) NOT NULL,
  `color` varchar(50) NOT NULL,
  `size` varchar(50) NOT NULL,
  `wholeSalePrice` int(50) NOT NULL,
  `sellingPrice` int(50) NOT NULL,
  `quantity` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pname`, `cid`, `pid`, `color`, `size`, `wholeSalePrice`, `sellingPrice`, `quantity`) VALUES
('hvhv', 4, 9, 'red', 'small', 300, 400, 5);

-- --------------------------------------------------------

--
-- Table structure for table `productinfo`
--

CREATE TABLE `productinfo` (
  `pname` varchar(50) NOT NULL,
  `pid` int(50) NOT NULL,
  `cid` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productinfo`
--

INSERT INTO `productinfo` (`pname`, `pid`, `cid`) VALUES
('BLAZER', 34, 11),
('T-SHIRT', 35, 11);

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `eid` int(50) NOT NULL,
  `salary` int(50) NOT NULL,
  `month` int(2) NOT NULL,
  `year` int(4) NOT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`eid`, `salary`, `month`, `year`, `status`) VALUES
(3, 30, 10, 2017, 'Pending'),
(4, 400, 10, 2017, NULL),
(3, 30, 11, 2017, NULL),
(4, 400, 11, 2017, 'Paid'),
(5, 333, 11, 2017, NULL),
(4, 400, 12, 2017, NULL),
(5, 333, 12, 2017, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `sl` int(100) NOT NULL,
  `pid` int(50) NOT NULL,
  `size` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `wholeSalePrice` int(50) NOT NULL,
  `sellingPrice` int(50) NOT NULL,
  `quantity` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`sl`, `pid`, `size`, `color`, `wholeSalePrice`, `sellingPrice`, `quantity`) VALUES
(50, 34, 'S', 'DEEP BLUE', 4000, 5890, 13),
(51, 34, 'M', 'DEEP BLUE', 4000, 5890, 15),
(52, 34, 'L', 'DEEP BLUE', 4000, 5890, 13),
(53, 34, 'XL', 'DEEP BLUE', 4000, 5890, 4),
(54, 35, 'L', 'STRIPPED BLUE', 600, 1090, 10),
(55, 35, 'M', 'STRIPPED BLUE', 600, 1090, 10),
(56, 35, 'S', 'STRIPPED BLUE', 600, 1090, 10),
(57, 35, 'S', 'SOLID GREEN', 700, 1190, 10),
(61, 35, 'L', 'SOLID GREEN', 700, 1190, 9),
(62, 35, 'M', 'SOLID GREEN', 700, 1190, 10);

-- --------------------------------------------------------

--
-- Table structure for table `yearly`
--

CREATE TABLE `yearly` (
  `year` int(4) NOT NULL,
  `income` int(100) NOT NULL,
  `expenses` int(100) NOT NULL,
  `profit` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `yearly`
--

INSERT INTO `yearly` (`year`, `income`, `expenses`, `profit`) VALUES
(2017, 24750, 450, 24300);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`receiptNo`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `dailyrecord`
--
ALTER TABLE `dailyrecord`
  ADD PRIMARY KEY (`sl`);

--
-- Indexes for table `emp`
--
ALTER TABLE `emp`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `productinfo`
--
ALTER TABLE `productinfo`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`sl`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `cid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `dailyrecord`
--
ALTER TABLE `dailyrecord`
  MODIFY `sl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `emp`
--
ALTER TABLE `emp`
  MODIFY `eid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `productinfo`
--
ALTER TABLE `productinfo`
  MODIFY `pid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `sl` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
