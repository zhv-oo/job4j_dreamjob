package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import java.util.*;

@ThreadSafe
@Service
public class CityService {
    private final Map<Integer, City> cities = new HashMap<>();

    public CityService() {
        cities.put(1, new City(1, "Москва"));
        cities.put(2, new City(2, "СПб"));
        cities.put(3, new City(3, "Екб"));
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public City findById(int id) {
        return cities.get(id);
    }
}