/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  чтепоноза
 * Created: 20 июн. 2024 г.
 */

CREATE TABLE IF NOT EXISTS Candidates (
    name VARCHAR(255) NOT NULL,
    yearOfBirth INT,
    placeOfLiving VARCHAR(255),
    party VARCHAR(255),
    information TEXT,
    votes INT
);
