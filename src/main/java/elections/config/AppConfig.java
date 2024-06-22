/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author чтепоноза
 */
@Configuration
@EnableJpaRepositories(basePackages = "elections.repository")
@EntityScan(basePackages = "elections.model")
public class AppConfig {
    // Дополнительные конфигурационные бины могут быть здесь
}
