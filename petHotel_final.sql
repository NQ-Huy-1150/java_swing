USE master;
GO

-- Tạo database nếu chưa tồn tại
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'pet_hotel')
BEGIN
    CREATE DATABASE pet_hotel;
END
GO

USE pet_hotel;
GO

-- Xóa bảng nếu tồn tại (theo thứ tự khóa ngoại)
IF OBJECT_ID('dbo.invoices', 'U') IS NOT NULL DROP TABLE dbo.invoices;
IF OBJECT_ID('dbo.bookings', 'U') IS NOT NULL DROP TABLE dbo.bookings;  -- ĐỔI TÊN
IF OBJECT_ID('dbo.pets', 'U') IS NOT NULL DROP TABLE dbo.pets;
IF OBJECT_ID('dbo.services', 'U') IS NOT NULL DROP TABLE dbo.services;
IF OBJECT_ID('dbo.rooms', 'U') IS NOT NULL DROP TABLE dbo.rooms;
IF OBJECT_ID('dbo.customers', 'U') IS NOT NULL DROP TABLE dbo.customers;
GO

-- =====================
-- TABLE: customers
-- =====================
CREATE TABLE customers (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    phoneNumber VARCHAR(20)
);
GO

-- =====================
-- TABLE: rooms
-- =====================
CREATE TABLE rooms (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    status NVARCHAR(255)
);
GO

-- =====================
-- TABLE: services
-- =====================
CREATE TABLE services (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    description NVARCHAR(MAX),
    price FLOAT
);
GO

-- =====================
-- TABLE: pets
-- =====================
CREATE TABLE pets (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    breed NVARCHAR(255),          -- ĐỔI TÊN:  breeds → breed
    gender NVARCHAR(50),
    healthStatus NVARCHAR(255),
    customer_id BIGINT,
    age INT,                      -- THÊM CỘT age
    CONSTRAINT FK_pet_customer 
        FOREIGN KEY (customer_id) REFERENCES customers(id)
);
GO

CREATE INDEX FK_pet_customer_idx ON pets(customer_id);
GO

-- =====================
-- TABLE: bookings (ĐỔI TÊN: booking → bookings)
-- =====================
CREATE TABLE bookings (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    service_id BIGINT,
    room_id BIGINT,
    pet_id BIGINT,
    createTime DATETIME,
    endTime DATETIME,
    note VARCHAR(255),            -- THÊM CỘT note
    CONSTRAINT FK_PET_TICKET 
        FOREIGN KEY (pet_id) REFERENCES pets(id),
    CONSTRAINT room_fk 
        FOREIGN KEY (room_id) REFERENCES rooms(id),
    CONSTRAINT service_fk 
        FOREIGN KEY (service_id) REFERENCES services(id)
);
GO

CREATE INDEX room_fk_idx ON bookings(room_id);
CREATE INDEX service_fk_idx ON bookings(service_id);
CREATE INDEX FK_PET_TICKET_idx ON bookings(pet_id);
GO

-- =====================
-- TABLE: invoices
-- =====================
CREATE TABLE invoices (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ticket_id BIGINT,
    total FLOAT,
    CONSTRAINT FK_INVOICE_TICKET 
        FOREIGN KEY (ticket_id) REFERENCES bookings(id)  -- ĐỔI TÊN
);
GO

CREATE INDEX FK_INVOICE_TICKET_idx ON invoices(ticket_id);
GO

-- =====================
-- DỮ LIỆU MẪU
-- =====================
SET IDENTITY_INSERT customers ON;
INSERT customers (id, name, phoneNumber) VALUES 
(1, N'Nam', N'0123456789'),
(2, N'Giang', N'098321222');
SET IDENTITY_INSERT customers OFF;
GO

SET IDENTITY_INSERT pets ON;
INSERT pets (id, name, breed, gender, healthStatus, customer_id, age) VALUES 
(1, N'kiki', N'dog', N'cai', N'Khoe', 1, NULL),
(2, N'Gold', N'dog', N'duc', N'khoe', 2, NULL);
SET IDENTITY_INSERT pets OFF;
GO

SET IDENTITY_INSERT rooms ON;
INSERT rooms (id, name, status) VALUES 
(1, N'phong 1', N'TRONG'),
(2, N'phong 2', N'DANG_SU_DUNG');
SET IDENTITY_INSERT rooms OFF;
GO

SET IDENTITY_INSERT services ON;
INSERT services (id, name, description, price) VALUES 
(1, N'TAM_SAY_CO_BAN', N'tắm massage thư giãn – vắt tuyến hôi – sấy khô phồng lông. ', 360000),
(2, N'COMBO_SPA_9', N'cắt – mài móng, cạo lông lòng bàn chân, cạo lông bụng – vùng vệ sinh, chải lông', 450000),
(3, N'CAT_TIA_TAO_KIEU', N'gồm combo spa 9 bước thơm tho và cắt tỉa – tạo kiểu theo yêu cầu', 550000);
SET IDENTITY_INSERT services OFF;
GO

SET IDENTITY_INSERT bookings ON;
INSERT bookings (id, service_id, room_id, pet_id, createTime, endTime, note) VALUES 
(2, 1, 1, 1, NULL, NULL, NULL),
(4, 3, 2, 1, CAST(N'2026-01-01T20:48:46. 020' AS DateTime), NULL, N'thit cho 7 mon');
SET IDENTITY_INSERT bookings OFF;
GO