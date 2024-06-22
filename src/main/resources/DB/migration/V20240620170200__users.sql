/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  чтепоноза
 * Created: 20 июн. 2024 г.
 */

CREATE TABLE IF NOT EXISTS Users (
    login VARCHAR(255) NOT NULL,
    passwordHash VARCHAR(255) NOT NULL,
    voted BOOLEAN NOT NULL,
    isAdmin BOOLEAN NOT NULL
);
