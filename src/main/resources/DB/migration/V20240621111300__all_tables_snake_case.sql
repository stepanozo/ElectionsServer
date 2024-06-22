/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  чтепоноза
 * Created: 21 июн. 2024 г.
 */


DROP TABLE users;
DROP TABLE Candidates;
DROP TABLE ElectionsTime;

CREATE TABLE IF NOT EXISTS users (
    login VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    voted BOOLEAN NOT NULL,
    is_admin BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS candidates (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    year_of_birth INT,
    place_of_living VARCHAR(255),
    party VARCHAR(255),
    information TEXT,
    votes INT
);

CREATE TABLE IF NOT EXISTS elections_time (
    date_time_of_begining TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    date_time_of_ending TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

INSERT INTO users (login, password_hash, voted, is_admin)
VALUES ('admin', 'admin', false, true);