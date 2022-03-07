import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Route {
    private String name;
    private Map<String, City> cities;

    public Route(String name) {
        this.name = name;
        this.cities = new HashMap<>();
    }

    public void addCity(City city) {
        this.cities.put(city.getName(), city);
        city.addRoute(this);
    }

    public boolean containsCity(String city) {
        return this.cities.containsKey(city);
    }

    public String getName() {
        return name;
    }

    public Set<City> getCity() {
        return new HashSet<>(cities.values());
    }
}
