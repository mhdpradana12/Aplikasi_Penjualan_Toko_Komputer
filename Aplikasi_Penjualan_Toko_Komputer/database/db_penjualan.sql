-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2022 at 12:57 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penjualan`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `kode_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `vendor_barang` varchar(15) NOT NULL,
  `stok_barang` int(15) NOT NULL,
  `harga_barang` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`kode_barang`, `nama_barang`, `vendor_barang`, `stok_barang`, `harga_barang`) VALUES
('BR000001', 'Apple iMac Pro MQ2Y2ID/A', 'Apple', 9, 22150000),
('BR000002', 'Lenovo Yoga A940 AIO Desktop', 'Lenovo', 40, 50155250),
('BR000003', 'Lenovo AIO IdeaCentre 3 22ADA05', 'Lenovo', 24, 6003500),
('BR000004', 'Asus AIO V241EPT-BA7811TS /Core i7', 'Asus', 43, 20680000),
('BR000005', 'Lenovo IdeaCentre AIO520', 'Lenovo', 63, 12007500);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detail_transaksi`
--

CREATE TABLE `tbl_detail_transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `kode_barang` varchar(10) NOT NULL,
  `harga` int(15) NOT NULL,
  `banyak_barang` int(15) NOT NULL,
  `sub_total` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_detail_transaksi`
--

INSERT INTO `tbl_detail_transaksi` (`id_transaksi`, `kode_barang`, `harga`, `banyak_barang`, `sub_total`) VALUES
('IT000001', 'BR000001', 22150000, 2, 44300000),
('IT000002', 'BR000001', 22150000, 4, 88600000),
('IT000002', 'BR000002', 50155250, 2, 100310500),
('IT000001', 'BR000002', 50155250, 3, 150465750),
('IT000003', 'BR000001', 22150000, 2, 44300000),
('IT000004', 'BR000005', 12007500, 2, 24015000),
('IT000004', 'BR000004', 20680000, 2, 41360000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pembeli`
--

CREATE TABLE `tbl_pembeli` (
  `id_pembeli` varchar(10) NOT NULL,
  `nama_pembeli` varchar(100) NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_pembeli`
--

INSERT INTO `tbl_pembeli` (`id_pembeli`, `nama_pembeli`, `no_telp`, `alamat`) VALUES
('KP000001', 'Muhammad Pradana', '089662380814', 'JL. Sutan Syahrir Gg. Teratai'),
('KP000002', 'Denny Kurniawan', '089562341080', 'JL. Manggis 2 '),
('KP000003', 'M. Dicky Wahyudi', '089644225680', 'JL. Anggur 2');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `id_pembeli` varchar(10) NOT NULL,
  `tgl_beli` date NOT NULL,
  `total` int(15) NOT NULL,
  `cash` int(15) NOT NULL,
  `kembali` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`id_transaksi`, `id_pembeli`, `tgl_beli`, `total`, `cash`, `kembali`) VALUES
('IT000001', 'KP000001', '2022-07-26', 194765750, 200000000, 5234250),
('IT000002', 'KP000002', '2022-07-26', 188910500, 200000000, 11089500),
('IT000003', 'KP000001', '2022-07-26', 44300000, 45000000, 700000),
('IT000004', 'KP000003', '2022-07-27', 65375000, 66375000, 1000000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `tbl_detail_transaksi`
--
ALTER TABLE `tbl_detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`,`kode_barang`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indexes for table `tbl_pembeli`
--
ALTER TABLE `tbl_pembeli`
  ADD PRIMARY KEY (`id_pembeli`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pembeli` (`id_pembeli`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_detail_transaksi`
--
ALTER TABLE `tbl_detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `tbl_barang` (`kode_barang`);

--
-- Constraints for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_detail_transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_pembeli`) REFERENCES `tbl_pembeli` (`id_pembeli`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
