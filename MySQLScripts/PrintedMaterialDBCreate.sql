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
