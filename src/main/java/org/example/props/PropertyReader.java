package org.example.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class PropertyReader {
    public static String getConnectionUrlForH2() {
        return Objects.requireNonNull(getProperties())
                .map(properties -> properties.getProperty("hibernate.connection.url")).orElse(null);
    }

    public static String getUserForH2() {
        return Objects.requireNonNull(getProperties())
                .map(properties -> properties.getProperty("hibernate.connection.username")).orElse(null);
    }

    public static String getPasswordForH2() {
        return Objects.requireNonNull(getProperties())
                .map(properties -> properties.getProperty("hibernate.connection.password")).orElse(null);
    }

    private static Optional<Properties> getProperties() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("hibernate.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find hibernate.properties");
                return null;
            }

            prop.load(input);

            return Optional.of(prop);
        } catch (IOException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
