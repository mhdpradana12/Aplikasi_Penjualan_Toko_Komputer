-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Jul 2022 pada 04.39
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_aplikasi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id_user` int(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `no_telp` varchar(75) NOT NULL,
  `alamat` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_admin`
--

INSERT INTO `tbl_admin` (`id_user`, `username`, `password`, `nama_lengkap`, `jenis_kelamin`, `no_telp`, `alamat`) VALUES
(1, 'admin', 'admin', 'Muhammad Pradana', 'Laki-Laki', '089662380814', 'JL. Sutan Syahrir No. 12'),
(2, 'denny', 'denny', 'Denny Kurniawan', 'Laki-Laki', '089662380015', 'JL. Manggis 2'),
(3, 'siti', 'siti', 'Siti ', 'Perempuan', '089645700321', 'JL Anggur 1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `kode_barang` int(15) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `vendor_barang` varchar(50) NOT NULL,
  `stok_barang` int(11) NOT NULL,
  `harga_barang` int(11) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `id_user` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_barang`
--

INSERT INTO `tbl_barang` (`kode_barang`, `nama_barang`, `vendor_barang`, `stok_barang`, `harga_barang`, `tanggal_masuk`, `id_user`) VALUES
(1, 'Apple iMac Pro MQ2Y2ID/A ', 'Apple', 15, 22150000, '2022-07-24', 1),
(2, 'Lenovo Yoga A940 AIO Desktop', 'Lenovo', 45, 50155250, '2022-07-24', 1),
(3, 'Asus Vivo AIO V24', 'Asus', 65, 13125000, '2022-07-24', 1),
(4, 'Lenovo IdeaCentre AIO A54', 'Lenovo', 125, 9500000, '2022-07-24', 1),
(5, 'HP Pavilion All-in-One 24-XA0115D', 'HP', 25, 12450000, '2022-07-24', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_detail_transaksi`
--

CREATE TABLE `tbl_detail_transaksi` (
  `id_transaksi` int(15) NOT NULL,
  `kode_barang` int(15) NOT NULL,
  `bayar` int(15) NOT NULL,
  `kembalian` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_detail_transaksi`
--

INSERT INTO `tbl_detail_transaksi` (`id_transaksi`, `kode_barang`, `bayar`, `kembalian`) VALUES
(1, 1, 25000000, 2850000),
(2, 3, 27000000, 750000),
(3, 4, 20000000, 1000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` int(15) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `kode_barang` int(15) NOT NULL,
  `harga_satuan` int(15) NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `total_bayar` int(15) NOT NULL,
  `tanggal_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`id_transaksi`, `nama_barang`, `kode_barang`, `harga_satuan`, `jumlah_beli`, `total_bayar`, `tanggal_transaksi`) VALUES
(1, 'Apple iMac Pro MQ2Y2ID/A ', 1, 22150000, 1, 22150000, '2022-07-24'),
(2, 'Asus Vivo AIO V24', 3, 13125000, 2, 26250000, '2022-07-25'),
(3, 'Lenovo IdeaCentre AIO A54', 4, 9500000, 2, 19000000, '2022-07-25');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `IDUser` (`id_user`);

--
-- Indeks untuk tabel `tbl_detail_transaksi`
--
ALTER TABLE `tbl_detail_transaksi`
  ADD KEY `KodeBarang` (`kode_barang`),
  ADD KEY `IDTransaksi` (`id_transaksi`);

--
-- Indeks untuk tabel `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`) USING BTREE;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD CONSTRAINT `IDUser` FOREIGN KEY (`id_user`) REFERENCES `tbl_admin` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ketidakleluasaan untuk tabel `tbl_detail_transaksi`
--
ALTER TABLE `tbl_detail_transaksi`
  ADD CONSTRAINT `IDTransaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `KodeBarang` FOREIGN KEY (`kode_barang`) REFERENCES `tbl_barang` (`kode_barang`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
