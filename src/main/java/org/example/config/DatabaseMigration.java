package org.example.config;

import org.example.props.PropertyReader;
import org.flywaydb.core.Flyway;

public class DatabaseMigration {
    public void startMigration() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(PropertyReader.getConnectionUrlForH2(), PropertyReader.getUserForH2(), PropertyReader.getPasswordForH2())
                .load();

        flyway.migrate();
    }
}
