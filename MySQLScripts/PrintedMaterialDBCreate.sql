-- Створення бази даних
CREATE DATABASE IF NOT EXISTS PrintedMaterialsDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE PrintedMaterialsDB;

-- Таблиця PrintedMaterial
CREATE TABLE PrintedMaterial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    page_count INT NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL
);

-- Таблиця Book
CREATE TABLE Book (
    id INT PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES PrintedMaterial(id) ON DELETE CASCADE
);

-- Таблиця Ebook
CREATE TABLE Ebook (
    id INT PRIMARY KEY,
    fileFormat VARCHAR(50) NOT NULL,
    fileSize DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Book(id) ON DELETE CASCADE
);

-- Таблиця AudioBook
CREATE TABLE AudioBook (
    id INT PRIMARY KEY,
    narrator VARCHAR(255) NOT NULL,
    duration DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES Book(id) ON DELETE CASCADE
);

-- Таблиця Magazine
CREATE TABLE Magazine (
    id INT PRIMARY KEY,
    issueNumber INT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES PrintedMaterial(id) ON DELETE CASCADE
);

-- Таблиця ScienceMagazine
CREATE TABLE ScienceMagazine (
    id INT PRIMARY KEY,
    scientificField VARCHAR(255) NOT NULL,
    peerReviewed BOOLEAN NOT NULL,
    FOREIGN KEY (id) REFERENCES Magazine(id) ON DELETE CASCADE
);

-- Таблиця Calendar
CREATE TABLE Calendar (
    id INT PRIMARY KEY,
    calendarType VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES PrintedMaterial(id) ON DELETE CASCADE
);

-- Таблиця WallCalendar
CREATE TABLE WallCalendar (
    id INT PRIMARY KEY,
    mountType VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Calendar(id) ON DELETE CASCADE
);

-- Таблиця DeskCalendar
CREATE TABLE DeskCalendar (
    id INT PRIMARY KEY,
    pageType VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Calendar(id) ON DELETE CASCADE
);
