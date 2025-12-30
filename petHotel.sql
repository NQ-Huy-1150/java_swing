USE master;
GO

-- 1. Tạo Database (Nếu chưa có)
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'pet_hotel')
BEGIN
    CREATE DATABASE [pet_hotel];
END
GO

USE [pet_hotel];
GO

-- 2. Xóa các bảng cũ nếu tồn tại (theo thứ tự khóa ngoại để tránh lỗi)
IF OBJECT_ID('dbo.invoices', 'U') IS NOT NULL DROP TABLE dbo.invoices;
IF OBJECT_ID('dbo.booking', 'U') IS NOT NULL DROP TABLE dbo.booking;
IF OBJECT_ID('dbo.pets', 'U') IS NOT NULL DROP TABLE dbo.pets;
IF OBJECT_ID('dbo.services', 'U') IS NOT NULL DROP TABLE dbo.services;
IF OBJECT_ID('dbo.rooms', 'U') IS NOT NULL DROP TABLE dbo.rooms;
IF OBJECT_ID('dbo.customers', 'U') IS NOT NULL DROP TABLE dbo.customers;
GO

-- 3. Tạo bảng customers
CREATE TABLE [customers] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [name] NVARCHAR(255) NULL,
    [phoneNumber] VARCHAR(20) NULL, -- Số điện thoại không cần Nvarchar
    PRIMARY KEY CLUSTERED ([id] ASC)
    );
GO

-- 4. Tạo bảng rooms
CREATE TABLE [rooms] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [name] NVARCHAR(255) NULL,
    [status] NVARCHAR(255) NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
    );
GO

-- 5. Tạo bảng services
CREATE TABLE [services] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [name] NVARCHAR(255) NULL,
    [description] NVARCHAR(MAX) NULL, -- Dùng MAX cho mô tả dài
    [price] FLOAT NULL,
    PRIMARY KEY CLUSTERED ([id] ASC)
    );
GO

-- 6. Tạo bảng pets
CREATE TABLE [pets] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [name] NVARCHAR(255) NULL,
    [breeds] NVARCHAR(255) NULL,
    [gender] NVARCHAR(50) NULL,
    [healthStatus] NVARCHAR(255) NULL,
    [customer_id] bigint NULL,
    PRIMARY KEY CLUSTERED ([id] ASC),
    CONSTRAINT [FK_pet_customer] FOREIGN KEY ([customer_id]) REFERENCES [customers] ([id])
    );
GO

-- Tạo Index cho pets (SQL Server tách riêng index, không gộp trong Create Table như MySQL)
CREATE INDEX [FK_pet_customer_idx] ON [pets] ([customer_id]);
GO

-- 7. Tạo bảng booking
CREATE TABLE [booking] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [service_id] bigint NULL,
    [room_id] bigint NULL,
    [pet_id] bigint NULL,
    [createTime] DATETIME NULL,
    [endTime] DATETIME NULL,
    PRIMARY KEY CLUSTERED ([id] ASC),
    CONSTRAINT [FK_PET_TICKET] FOREIGN KEY ([pet_id]) REFERENCES [pets] ([id]),
    CONSTRAINT [room_fk] FOREIGN KEY ([room_id]) REFERENCES [rooms] ([id]),
    CONSTRAINT [service_fk] FOREIGN KEY ([service_id]) REFERENCES [services] ([id])
    );
GO

-- Tạo Index cho booking
CREATE INDEX [room_fk_idx] ON [booking] ([room_id]);
CREATE INDEX [service_fk_idx] ON [booking] ([service_id]);
CREATE INDEX [FK_PET_TICKET_idx] ON [booking] ([pet_id]);
GO

-- 8. Tạo bảng invoices
CREATE TABLE [invoices] (
    [id] bigint IDENTITY(1,1) NOT NULL,
    [ticket_id] bigint NULL,
    [total] FLOAT NULL,
    PRIMARY KEY CLUSTERED ([id] ASC),
    CONSTRAINT [FK_INVOICE_TICKET] FOREIGN KEY ([ticket_id]) REFERENCES [booking] ([id])
    );
GO

-- Tạo Index cho invoices
CREATE INDEX [FK_INVOICE_TICKET_idx] ON [invoices] ([ticket_id]);
GO

-- =============================================
-- INSERT DỮ LIỆU
-- =============================================

-- Insert dữ liệu bảng Services
-- Vì cột id là IDENTITY, ta cần bật chế độ cho phép nhập ID thủ công
SET IDENTITY_INSERT [services] ON;

INSERT INTO [services] ([id], [name], [description], [price]) VALUES
    (1, N'TAM_SAY_CO_BAN', N'tắm massage thư giãn – vắt tuyến hôi – sấy khô phồng lông.', 360000),
    (2, N'COMBO_SPA_9', N'cắt – mài móng, cạo lông lòng bàn chân, cạo lông bụng – vùng vệ sinh, chải lông chết gỡ rối, vệ sinh tai lần 1, tắm massage thư giãn – vắt tuyến hôi, sấy khô – chải phồng lông, vệ sinh tai lần 2, thoa lotion dưỡng lông mềm mại.', 500000),
    (3, N'CAT_TIA_TAO_KIEU', N'gồm combo spa 9 bước thơm tho và cắt tỉa – tạo kiểu theo yêu cầu hoặc theo hiện trạng lông của các bé', 999000);

-- Tắt chế độ nhập ID thủ công để hệ thống tự tăng cho các lần sau
SET IDENTITY_INSERT [services] OFF;
GO