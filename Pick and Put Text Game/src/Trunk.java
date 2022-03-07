import java.util.HashMap;
import java.util.Map;

public class Trunk {
    private Map<String, Tik> tiks;

    public Trunk() {
        this.tiks = new HashMap<>();
    }

    public void addTik(Tik tik) {
        this.tiks.put(tik.getName(), tik);
    }

    public Tik removeTik(String tikName) {
        return this.tiks.remove(tikName);
    }

    public void printTrunk() {
        if (this.tiks.isEmpty()) {
            System.out.println("Your trunk is empty.");
        } else {
            for (Tik tik : this.tiks.values()) {
                System.out.printf("%s (%s)\n", tik.getName(), tik.getDescription());
            }
        }
    }
}
