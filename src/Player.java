import java.util.ArrayList;

public class Player {



    // Det rum spilleren står i lige nu
    // Feltet er private, så kun Player selv kan ændre det (indkapsling).
    private Room currentRoom;


    // Spillerens inventory: de ting, spilleren bærer rundt på.
    private final ArrayList<Item> inventory = new ArrayList<>();

    //Spillerens helbred starter på 100
    // (Ligesom i et normalt spil hvor man kan "miste liv/point" det samme princip gælder her:
    private int health = 100;


    // Konstruktør: spilleren får sit startrum med, når den bliver oprettet.
    // Player ved ikke selv, hvilket rum der er startrummet - det bestemmer Map
    public Player(Room startRoom) {
        currentRoom = startRoom;
    }


    // Returnerer navn og beskrivelse af rummet, plus de ting/ items der ligger i det.
    public String look() {
        StringBuilder text = new StringBuilder(currentRoom.getName() + "\n" + currentRoom.getDescription());
        ArrayList<Item> items = currentRoom.getItems();
        if (items.isEmpty()) {
            text.append("\nThere is nothing here.");

        } else {
            text.append("\nHere you see:");
            for (Item item : items) {
                text.append("\n- ").append(item.getLongName());
            }
        }
        return text.toString();
    }


    // Hver metode tjekker om der er et rum i den retning (altså ikke null)
    // Er der et rum, returnerer metoden true hvis ikke bliver den false og spiller flytter ikke.
    // Adventure kan bare kalde player.goNorth() og behøver ikke vide,
    // hvordan rummene hænger sammen (Law of Demeter / lav kobling).
    public boolean goNorth() {
        if (currentRoom.getNorth() != null) {
            currentRoom = currentRoom.getNorth();
            return true;
        }
        return false;

    }

    public boolean goEast() {
        if (currentRoom.getEast() != null) {
            currentRoom = currentRoom.getEast();
            return true;
        }
        return false;
    }

    public boolean goWest() {
        if (currentRoom.getWest() != null) {
            currentRoom = currentRoom.getWest();
            return true;
        }
        return false;
    }


    public boolean goSouth() {
        if (currentRoom.getSouth() != null) {
            currentRoom = currentRoom.getSouth();
            return true;
        }
        return false;
    }

    // Tager en ting fra rummet og lægger den i spillerens inventory.
    // Returnerer tingen, hvis den fandtes, ellers null.
    public Item takeItem(String shortName) {
        Item item = currentRoom.findItem(shortName);

        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }
        return item;
    }

    // Returnerer listen over de ting, spilleren bærer på.
    public ArrayList<Item> getInventory() {
        return inventory;

    }

    // Leder efter en ting i spillerens inventory ud fra dens korte navn.
    // Returnerer tingen, hvis den findes, ellers null
    public Item findItem(String shortName) {
        for (Item item : inventory) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }
        return null;
    }

    // Tager en ting fra spillerens inventory og lægger den i rummet.
    // Returnerer tingen, hvis den fandtes, ellers null.
    public Item dropItem(String shortName) {
        Item item = findItem(shortName);

        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }
        return item;
    }

    // Returnerer spillerens nuværende health.
    public int getHealth() {
        return health;
    }

}
