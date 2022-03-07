import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private Map<String, City> cities;
    private City startCity;
    private Trunk trunk;

    public Game() {
        this.cities = new HashMap<>();
        this.trunk = new Trunk();
        City prague = new City("Prague", "an one hundred tower city.");
        City berlin = new City("Berlin", "night adventures and clubs.");
        City paris = new City("Paris", "long and calm boulevards.");

        this.startCity = prague;
        this.cities.put(prague.getName(), prague);
        this.cities.put(berlin.getName(), berlin);
        this.cities.put(paris.getName(), paris);

        Route kingsRoute = new Route("Golden route");
//        kingsRoute.addCity(prague);
        kingsRoute.addCity(berlin);
        kingsRoute.addCity(this.cities.get("Prague"));
        Route wolfRoute = new Route("Route of Hunters");
        wolfRoute.addCity(berlin);
        wolfRoute.addCity(paris);

        Tik crown = new Tik("crown", "golden crown for great kings.");
        prague.addTik(crown);
        Tik ring = new Tik("ring", "an old ring from the middle ages.");
        prague.addTik(ring);
        Tik coat = new Tik("coat", "a white coat with some magic unknown power.");
        prague.addTik(coat);
    }

    public void start() {
        String command;
        Scanner scanner = new Scanner(System.in);
        City currentCity = this.startCity;
        System.out.println("Write \'info\' and press ENTER to find out how to play");
        currentCity.printCityInfo();
        gameLoop:
        while (true) {
            System.out.println("What is your move?");
            String commandLine = scanner.nextLine();
            String[] splitCommand = commandLine.split("\\s+");
            command = splitCommand[0];
            switch (command) {
                case "info":
                    printGameInfo();
                    break;
                case "go":
                    currentCity = goTo(currentCity, splitCommand[1]);
                    currentCity.printCityInfo();
                    break;
                case "pick":
                    pickTik(currentCity, splitCommand[1]);
                    break;
                case "put":
                    putTik(currentCity, splitCommand[1]);
                    break;
                case "trunk":
                    trunk.printTrunk();
                    break;
                case "look":
                    currentCity.printCityInfo();
                    break;
                case "exit":
                    System.out.println("Thanks for playing!");
                    break gameLoop;
                default:
                    System.out.println("I don't know this command...");
            }
        }
    }

    private void printGameInfo() {
        System.out.println("Commands to use:");
        System.out.println("\'look\' to show where you are");
        System.out.println("\'go\' + \'name\' of the city to change destination");
        System.out.println("\'trunk\' to look into the trunk what is inside");
        System.out.println("\'pick\' + \'name\' of the TIK to take the TIK and put it into the trunk, then you can go to other city with it");
        System.out.println("\'put\' + \'name\' of the TIK to take the TIK from the trunk and leave it in the current city");
        System.out.println("\'exit\' command exits the game");
    }

    private City goTo(City currentCity, String cityName) {
        if (currentCity.hasNeighbour(cityName)) {
            currentCity = this.cities.get(cityName);
        }
        return currentCity;
    }

    private void putTik(City currentCity, String tikName) {
        Tik tik = trunk.removeTik(tikName);
        if (tik == null) {
            System.out.println("You don't have this TIK.");
        } else {
            currentCity.addTik(tik);
            System.out.printf("You have put %s in %s.\n", tik.getName(), currentCity.getName());
        }
    }

    private void pickTik(City currentCity, String tikName) {
        if (currentCity.containsTik(tikName)) {
            Tik tik = currentCity.removeTik(tikName);
            trunk.addTik(tik);
            System.out.printf("You have picked up %s, %s\n", tik.getName(), tik.getDescription());
        }
    }
}
