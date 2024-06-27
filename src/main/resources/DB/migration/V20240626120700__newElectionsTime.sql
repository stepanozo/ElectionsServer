/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  чтепоноза
 * Created: 26 июн. 2024 г.
 */


ALTER TABLE elections_time
ADD id SERIAL PRIMARY KEY,
ADD ended BOOLEAN NOT NULL;
