import java.util.HashSet;
import java.util.Set;

public class City {
    private String name;
    private String description;
    private Set<Route> routes;
    private Set<Tik> tiks;

    public City(String name, String description, Set<Route> routes, Set<Tik> tiks) {
        this.name = name;
        this.description = description;
        this.routes = routes;
        this.tiks = tiks;
    }

    public City(String name, String description) {
        this.name = name;
        this.description = description;
        this.routes = new HashSet<>();
        this.tiks = new HashSet<>();
    }

    public void printCityInfo() {
        System.out.println("You are in " + this.name + ", " + this.description);

        printExitInfo();
        printTikInfo();
    }

    private void printTikInfo() {
        System.out.println("Looking around...");
        if (tiks.isEmpty()) {
            System.out.println("There are no TIKS here.");
        } else {
            System.out.println("Some TIKS are located here: ");
            for (Tik tik : tiks) {
                System.out.printf("%s, ", tik.getName());
            }
            System.out.println();
        }
    }

    private void printExitInfo() {
        if (routes.isEmpty()) {
            System.out.println("You can't leave this place.");
        } else {
            System.out.print("You can leave this palce using following routes leading to this city: ");
            for (Route route : this.routes) {
                System.out.print(route.getName() + " - ");
                for (City city : route.getCity()) {
                    if (city != this) {
                        System.out.print(city.getName() + ", ");
                    }
                }
            }
            System.out.println();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public Set<Tik> getTiks() {
        return tiks;
    }

    public void setTiks(Set<Tik> tiks) {
        this.tiks = tiks;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void addTik(Tik tik) {
        this.tiks.add(tik);
    }

    public boolean hasNeighbour(String city) {
        for (Route route : this.routes) {
            if (route.containsCity(city)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsTik(String tikName) {
        for (Tik tik : this.tiks) {
            if (tik.getName().equalsIgnoreCase(tikName)) {
                return true;
            }
        }
        return false;
    }

    public Tik removeTik(String tikName) {
        for (Tik tik : this.tiks) {
            if (tik.getName().equalsIgnoreCase(tikName)) {
                tiks.remove(tik);
                return tik;
            }
        }
        return null;
    }
}
