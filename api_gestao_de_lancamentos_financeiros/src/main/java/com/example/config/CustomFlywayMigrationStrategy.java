package com.example.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomFlywayMigrationStrategy {

    @Autowired
    private Flyway flyway;

    public void runRepair() {
        flyway.repair();
    }
}
