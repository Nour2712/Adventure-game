import java.util.ArrayList;

public class Room {

    //opretter felter:
    private final String name;
    private final String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    // Listen over de ting, der ligger i rummet.
    // Den oprettes tom med det samme, så den aldrig er null.
    private final ArrayList<Item> items = new ArrayList<>();

    //liste til fjender
    private final ArrayList<Enemy> enemies = new ArrayList<>();

    // Konstruktør: opretter et rum med navn og beskrivelse.
    // Naboerne er ikke med her, fordi alle rum skal findes, før man kan forbinde dem.
    // Derfor forbindes de bagefter med setters (i Map).
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters:
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }


    // Setters: bruges til at SÆTTE/ændre værdier (i det her tilfælde bruger vi det til at forbinde rum til hina

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setWest(Room west) {
        this.west = west;
    }


    // ----- Items -----
    //tilføjer addItem metoden - Lægger en ting i rummet.
    public void addItem(Item item) {
        items.add(item);
    }

    //tilføjer removeItem metoden
    // fjerner en ting fra rummet:
    public void removeItem(Item item) {
        items.remove(item);
    }

    //tilføjer geItems metoden - Returnerer listen over alle ting i rummet.
    public ArrayList<Item> getItems() {
        return items;
    }

    // Leder efter en ting i rummet ud fra dens korte navn.
    // Returnerer tingen, hvis den findes, ellers null.
    public Item findItem(String shortName) {
        for (Item item : items) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }
        return null;
    }

    // ----- Enemies -----
    //addEnemy(Enemy enemy), lægger en fjende i listen
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    //removeEnemy(Enemy enemy),fjerner en fjende fra listen
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    //getEnemies(), returnerer listen
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // Leder efter en fjende i rummet ud fra dens korte navn.
    // Returnerer fjenden, hvis den findes, ellers null.
    public Enemy findEnemy(String shortName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equals(shortName)) {
                return enemy;
            }
        }
        return null;
    }












}