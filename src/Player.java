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


    // Det våben spilleren har equipped lige nu. null betyder, at der ikke er noget våben equipped.
    // Feltet har typen Weapon - Player kender kun superklassen, ikke MeleeWeapon eller RangedWeapon.
    private Weapon equippedWeapon;

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

        // Vis fjender i rummet, hvis der er nogen
        ArrayList<Enemy> enemies = currentRoom.getEnemies();
        if (!enemies.isEmpty()) {
            text.append("\nBeware! Here lurks:");
            for (Enemy enemy : enemies) {
                text.append("\n- ").append(enemy.getLongName());
            }
        }
        return text.toString();
    }

    // Returnerer true, hvis der er fjender i det rum, spilleren står i.
    public boolean hasEnemies() {
        return !currentRoom.getEnemies().isEmpty();
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

    // Leder efter en ting først i inventory og derefter i rummet.
    // Returnerer tingen, hvis den findes et af stederne, ellers null.
    public Item findItemAnywhere(String shortName) {
        Item item = findItem(shortName);
        if (item == null) {
            item = currentRoom.findItem(shortName);
        }
        return item;
    }

    // Tager en ting fra spillerens inventory og lægger den i rummet.
    // Returnerer tingen, hvis den fandtes, ellers null.
    public Item dropItem(String shortName) {
        Item item = findItem(shortName);

        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);

            // Dropper man det våben, man har equipped, har man ikke længere noget equipped.
            if (item == equippedWeapon) {
                equippedWeapon = null;
            }
        }
        return item;
    }

    // Returnerer spillerens nuværende health.
    public int getHealth() {
        return health;
    }

    // Spilleren bliver ramt og mister health svarende til damage.
    public void hit(int damage) {
        health = health - damage;
    }


    // Spiser en ting, hvis den findes i inventory eller i rummet, og hvis den er mad.
    // Returnerer et af tre udfald: NOT_FOUND, NOT_FOOD eller EATEN.
    public EatResult eat(String shortName) {
        Item item = findItemAnywhere(shortName);

        //udfald 1: item findes ikke
        if (item == null) {
            return EatResult.NOT_FOUND;
        }

        //udfald 2: item findes, men er ikke mad:
        if (!(item instanceof Food)) {
            return EatResult.NOT_FOOD;
        }

        //udfald 3: item er mad og bliver spist:
        Food food = (Food) item;
        health = health + food.getHealthPoints();


        //Fjern maden så den ikke spises igen:
        //Den ligger kun et af stederne, og remove gør ingenting, hvis den ikke er i listen
        inventory.remove(food);
        currentRoom.removeItem(food);

        return EatResult.EATEN;

    }

    //equip-metoden
    // Equipper et våben fra spillerens inventory.
    // Returnerer et af tre udfald: NOT_FOUND, NOT_WEAPON eller EQUIPPED.
    public EquipResult equip(String shortName) {

        // Kun inventory - man kan ikke equippe noget, der ligger i rummet.
        Item item = findItem(shortName);

        // Udfald 1: tingen findes ikke i inventory
        if (item == null) {
            return EquipResult.NOT_FOUND;
        }

        // Udfald 2: tingen findes, men er ikke et våben
        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        }

        // Udfald 3: tingen er et våben og bliver equipped
        equippedWeapon = (Weapon) item;
        return EquipResult.EQUIPPED;


    }

    // Returnerer det våben spilleren har equipped, eller null hvis der ikke er noget.
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }


    // Angriber en fjende i rummet, eller den tomme luft, hvis der ingen fjender er.
    // Det er præcis det mit aktivitetsdiagram viser for attack-sekvensen:
    public AttackResult attack(String enemyName) {

        // Trin 1: intet våben equipped
        if (equippedWeapon == null) {
            return AttackResult.NO_WEAPON;
        }

        // Trin 2: våbnet kan ikke bruges
        if (!equippedWeapon.canUse()) {
            return AttackResult.NO_AMMO;
        }

        // Trin 3: find fjenden
        Enemy target = findTarget(enemyName);

        if (target == null) {
            // Der er angivet et navn, men fjenden findes ikke. Der bruges ikke noget skud.
            if (!enemyName.equals("")) {
                return AttackResult.NO_SUCH_ENEMY;
            }
            // Ingen fjender i rummet: angrib luften
            equippedWeapon.use();
            return AttackResult.ATTACKED_AIR;
        }

        // Trin 4 og 5: brug våbnet, og fjenden mister health
        equippedWeapon.use();
        target.hit(equippedWeapon.getDamage());

        // Trin 6: er fjenden død?
        if (target.isDead()) {
            return AttackResult.ENEMY_DIED;
        }

        // Trin 7: fjenden overlevede og slår igen
        target.attack(this);
        return AttackResult.ENEMY_HIT_BACK;
    }

    // Finder den fjende der skal angribes:
    // hvis spilleren bare skriver "attack", vælges den første fjende i rummet eller (null, hvis rummet er tomt).
    //hvis spilleren fx skriver "attack rat", så ledes der efter præcis den fjende (null, hvis den ikke er der).
    public Enemy findTarget(String enemyName) {
        if (enemyName.equals("")) {
            if (currentRoom.getEnemies().isEmpty()) {
                return null;
            }
            return currentRoom.getEnemies().get(0);
        }
        return currentRoom.findEnemy(enemyName);
    }


}