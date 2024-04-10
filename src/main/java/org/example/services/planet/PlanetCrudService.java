package org.example.services.planet;

import org.example.entities.Planet;

import java.util.List;

public interface PlanetCrudService {
    Planet createPlanet(Planet planet);
    Planet updatePlanet(Planet planet);
    List<Planet> getAllPlanets();
    Planet getPlanetById(String planetId);
    void deletePlanetById(String planetId);
}
