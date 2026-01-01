USE [master]
GO
/****** Object:  Database [pet_hotel]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE DATABASE [pet_hotel]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'pet_hotel', FILENAME = N'E:\New folder\MSSQL17.SQLEXPRESS02\MSSQL\DATA\pet_hotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'pet_hotel_log', FILENAME = N'E:\New folder\MSSQL17.SQLEXPRESS02\MSSQL\DATA\pet_hotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [pet_hotel] SET COMPATIBILITY_LEVEL = 170
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [pet_hotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [pet_hotel] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [pet_hotel] SET ANSI_NULLS OFF
GO
ALTER DATABASE [pet_hotel] SET ANSI_PADDING OFF
GO
ALTER DATABASE [pet_hotel] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [pet_hotel] SET ARITHABORT OFF
GO
ALTER DATABASE [pet_hotel] SET AUTO_CLOSE ON
GO
ALTER DATABASE [pet_hotel] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [pet_hotel] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [pet_hotel] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [pet_hotel] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [pet_hotel] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [pet_hotel] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [pet_hotel] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [pet_hotel] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [pet_hotel] SET  ENABLE_BROKER
GO
ALTER DATABASE [pet_hotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [pet_hotel] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [pet_hotel] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [pet_hotel] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [pet_hotel] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [pet_hotel] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [pet_hotel] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [pet_hotel] SET RECOVERY SIMPLE
GO
ALTER DATABASE [pet_hotel] SET  MULTI_USER
GO
ALTER DATABASE [pet_hotel] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [pet_hotel] SET DB_CHAINING OFF
GO
ALTER DATABASE [pet_hotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [pet_hotel] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [pet_hotel] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [pet_hotel] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
ALTER DATABASE [pet_hotel] SET OPTIMIZED_LOCKING = OFF
GO
ALTER DATABASE [pet_hotel] SET QUERY_STORE = ON
GO
ALTER DATABASE [pet_hotel] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [pet_hotel]
GO
/****** Object:  Table [dbo].[bookings]    Script Date: 1/1/2026 9:14:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bookings](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [service_id] [bigint] NULL,
    [room_id] [bigint] NULL,
    [pet_id] [bigint] NULL,
    [createTime] [datetime] NULL,
    [endTime] [datetime] NULL,
    [note] [varchar](255) NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[customers]    Script Date: 1/1/2026 9:14:28 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[customers](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](255) NULL,
    [phoneNumber] [varchar](20) NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[invoices]    Script Date: 1/1/2026 9:14:28 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[invoices](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [ticket_id] [bigint] NULL,
    [total] [float] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[pets]    Script Date: 1/1/2026 9:14:28 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[pets](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](255) NULL,
    [breed] [nvarchar](255) NULL,
    [gender] [nvarchar](50) NULL,
    [healthStatus] [nvarchar](255) NULL,
    [customer_id] [bigint] NULL,
    [age] [int] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[rooms]    Script Date: 1/1/2026 9:14:28 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[rooms](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](255) NULL,
    [status] [nvarchar](255) NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[services]    Script Date: 1/1/2026 9:14:28 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[services](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [name] [nvarchar](255) NULL,
    [description] [nvarchar](max) NULL,
    [price] [float] NULL,
    PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
    SET IDENTITY_INSERT [dbo].[bookings] ON
    GO
    INSERT [dbo].[bookings] ([id], [service_id], [room_id], [pet_id], [createTime], [endTime], [note]) VALUES (2, 1, 1, 1, NULL, NULL, NULL)
    GO
    INSERT [dbo].[bookings] ([id], [service_id], [room_id], [pet_id], [createTime], [endTime], [note]) VALUES (4, 3, 2, 1, CAST(N'2026-01-01T20:48:46.020' AS DateTime), NULL, N'thit cho 7 mon')
    GO
    SET IDENTITY_INSERT [dbo].[bookings] OFF
    GO
    SET IDENTITY_INSERT [dbo].[customers] ON
    GO
    INSERT [dbo].[customers] ([id], [name], [phoneNumber]) VALUES (1, N'Nam', N'0123456789')
    GO
    INSERT [dbo].[customers] ([id], [name], [phoneNumber]) VALUES (2, N'Giang', N'098321222')
    GO
    SET IDENTITY_INSERT [dbo].[customers] OFF
    GO
    SET IDENTITY_INSERT [dbo].[pets] ON
    GO
    INSERT [dbo].[pets] ([id], [name], [breed], [gender], [healthStatus], [customer_id], [age]) VALUES (1, N'kiki', N'dog', N'cai', N'Khoe', 1, NULL)
    GO
    INSERT [dbo].[pets] ([id], [name], [breed], [gender], [healthStatus], [customer_id], [age]) VALUES (2, N'Gold', N'dog', N'duc', N'khoe', 2, NULL)
    GO
    SET IDENTITY_INSERT [dbo].[pets] OFF
    GO
    SET IDENTITY_INSERT [dbo].[rooms] ON
    GO
    INSERT [dbo].[rooms] ([id], [name], [status]) VALUES (1, N'phong 1', N'TRONG')
    GO
    INSERT [dbo].[rooms] ([id], [name], [status]) VALUES (2, N'phong 2', N'DANG_SU_DUNG')
    GO
    SET IDENTITY_INSERT [dbo].[rooms] OFF
    GO
    SET IDENTITY_INSERT [dbo].[services] ON
    GO
    INSERT [dbo].[services] ([id], [name], [description], [price]) VALUES (1, N'TAM_SAY_CO_BAN', N'tắm massage thư giãn – vắt tuyến hôi – sấy khô phồng lông.', 360000)
    GO
    INSERT [dbo].[services] ([id], [name], [description], [price]) VALUES (2, N'COMBO_SPA_9', N'cắt – mài móng, cạo lông lòng bàn chân, cạo lông bụng – vùng vệ sinh, chải lông chết gỡ rối, vệ sinh tai lần 1, tắm massage thư giãn – vắt tuyến hôi, sấy khô – chải phồng lông, vệ sinh tai lần 2, thoa lotion dưỡng lông mềm mại.', 500000)
    GO
    INSERT [dbo].[services] ([id], [name], [description], [price]) VALUES (3, N'CAT_TIA_TAO_KIEU', N'gồm combo spa 9 bước thơm tho và cắt tỉa – tạo kiểu theo yêu cầu hoặc theo hiện trạng lông của các bé', 999000)
    GO
    SET IDENTITY_INSERT [dbo].[services] OFF
    GO
/****** Object:  Index [FK_PET_TICKET_idx]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE NONCLUSTERED INDEX [FK_PET_TICKET_idx] ON [dbo].[bookings]
(
	[pet_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [room_fk_idx]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE NONCLUSTERED INDEX [room_fk_idx] ON [dbo].[bookings]
(
	[room_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [service_fk_idx]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE NONCLUSTERED INDEX [service_fk_idx] ON [dbo].[bookings]
(
	[service_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK_INVOICE_TICKET_idx]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE NONCLUSTERED INDEX [FK_INVOICE_TICKET_idx] ON [dbo].[invoices]
(
	[ticket_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [FK_pet_customer_idx]    Script Date: 1/1/2026 9:14:28 PM ******/
CREATE NONCLUSTERED INDEX [FK_pet_customer_idx] ON [dbo].[pets]
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[bookings]  WITH CHECK ADD  CONSTRAINT [FK_PET_TICKET] FOREIGN KEY([pet_id])
    REFERENCES [dbo].[pets] ([id])
    GO
ALTER TABLE [dbo].[bookings] CHECK CONSTRAINT [FK_PET_TICKET]
    GO
ALTER TABLE [dbo].[bookings]  WITH CHECK ADD  CONSTRAINT [room_fk] FOREIGN KEY([room_id])
    REFERENCES [dbo].[rooms] ([id])
    GO
ALTER TABLE [dbo].[bookings] CHECK CONSTRAINT [room_fk]
    GO
ALTER TABLE [dbo].[bookings]  WITH CHECK ADD  CONSTRAINT [service_fk] FOREIGN KEY([service_id])
    REFERENCES [dbo].[services] ([id])
    GO
ALTER TABLE [dbo].[bookings] CHECK CONSTRAINT [service_fk]
    GO
ALTER TABLE [dbo].[invoices]  WITH CHECK ADD  CONSTRAINT [FK_INVOICE_TICKET] FOREIGN KEY([ticket_id])
    REFERENCES [dbo].[bookings] ([id])
    GO
ALTER TABLE [dbo].[invoices] CHECK CONSTRAINT [FK_INVOICE_TICKET]
    GO
ALTER TABLE [dbo].[pets]  WITH CHECK ADD  CONSTRAINT [FK_pet_customer] FOREIGN KEY([customer_id])
    REFERENCES [dbo].[customers] ([id])
    GO
ALTER TABLE [dbo].[pets] CHECK CONSTRAINT [FK_pet_customer]
    GO
    USE [master]
    GO
ALTER DATABASE [pet_hotel] SET  READ_WRITE
GO
