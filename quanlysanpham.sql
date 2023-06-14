-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 04, 2023 lúc 11:32 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlysanpham`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hang`
--

CREATE TABLE `hang` (
  `mahang` int(15) NOT NULL,
  `tenhang` varchar(100) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hang`
--

INSERT INTO `hang` (`mahang`, `tenhang`, `diachi`, `email`) VALUES
(1, 'Samsung', 'Han Quoc', 'samsung@mail.com'),
(2, 'Oppo', 'Trung Quoc', 'oppo@gmail.com'),
(3, 'Apple', 'America', 'apple@mail.com'),
(4, 'Xiaomi', 'Trung Quoc', 'xiaomi@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(100) NOT NULL,
  `loaisp` varchar(100) NOT NULL,
  `giasp` int(11) NOT NULL,
  `soluongsp` int(11) NOT NULL,
  `mahang` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `loaisp`, `giasp`, `soluongsp`, `mahang`) VALUES
(1, 'Samsung s23', 'Dien thoai', 19, 22, 1),
(2, 'Xiaomi 13 pro', 'Dien Thoai', 15, 13, 4),
(3, 'Ipad Pro 11', 'May tinh bang', 17, 7, 3),
(4, 'Xiaomi miband 6', 'Dong ho', 9, 51, 4),
(5, 'SamSung Watch 5', 'Dong ho', 3, 11, 1),
(6, 'Xiaomi pad 5', 'May tinh bang', 15, 15, 4),
(7, 'Iphone 13 Pro', 'Dien thoai', 18, 10, 3),
(8, 'Oppo Reno 8', 'May tinh bang', 11, 10, 2),
(9, 'Oppo Find X5 Pro', 'Dien Thoai', 16, 4, 2),
(10, 'Iphone 14', 'Dien thoai', 22, 10, 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hang`
--
ALTER TABLE `hang`
  ADD PRIMARY KEY (`mahang`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hang`
--
ALTER TABLE `hang`
  MODIFY `mahang` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
