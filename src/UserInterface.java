import java.util.ArrayList;

public class UserInterface { // Userinterface står for al kommunikation med brugeren.

    // Userinterface kender kun Adventure (controlleren) - ikke Player, Map eller Room (lav kobling)
    private final Adventure adventure;

    // Konstruktør: opretter spillet.
    public UserInterface() {
        adventure = new Adventure();
    }


    // Starter spillet og kører, indtil brugeren skriver "exit".
    public void start() {

        boolean playing = true;

        // Velkomsttekst
        IO.println("Welcome to Adventure time");
        IO.println("Can you find your way around the caves of Ooo, maybe there is a hidden treasure?");
        IO.println("You can traverse around the cave and use different commands, for example 'help'");


        // Spil-løkken: læser en kommando, udfører den og starter forfra.
        while (playing) {
            String kommando = IO.readln("> ");

            switch (kommando) {
                case "exit" -> {
                    IO.println("Goodbye!");
                    playing = false;
                }
                case "look" -> IO.println(adventure.look());//kalder look metoden fra vores adventure klasse

                case "inventory", "inv", "invent" -> {
                    ArrayList<Item> inventory = adventure.getInventory();

                    if (inventory.isEmpty()) {
                        IO.println("You are not carrying anything");
                    } else {
                        IO.println("You are carrying:");
                        for (Item item : inventory) {
                            IO.println("- " + item.getLongName());
                        }
                    }
                    // Vis det equippede våben, hvis der er et
                    if (adventure.getEquippedWeapon() != null) {
                        IO.println("Equipped: " + adventure.getEquippedWeapon().getLongName());
                    }
                }


                //Viser spilleren health som tal og en forklarende tekst.
                //Den høje grænse står først, fordi java stopper ved den første betingelse, som passer:
                case "health" -> {
                    int health = adventure.getHealth();
                    if (health >= 100) {
                        IO.println("health: " + health + " - you are in perfect health");
                    } else if (health >= 50) {
                        IO.println("health: " + health + " - you are in good health, but avoid fighting right now");
                    } else if (health >= 25) {
                        IO.println("health: " + health + " - you are hurt, be careful");
                    } else {
                        IO.println("health: " + health + " - you are in critical condition");
                    }
                }


                // Bruger det equippede våben mod den tomme luft.
                // UserInterface kender kun Weapon - ikke MeleeWeapon eller RangedWeapon.
                case "attack" -> {
                    AttackResult result = adventure.attack();

                    switch (result) {
                        case NO_WEAPON -> IO.println("You have no weapon equipped");
                        case NO_AMMO -> IO.println("You try to use " + adventure.getEquippedWeapon().getLongName() + ", but it is out of ammunition");
                        case ATTACKED -> {
                            Weapon weapon = adventure.getEquippedWeapon();
                            int usesLeft = weapon.getUsesLeft();

                            // -1 betyder ubegrænset brug (nærkampsvåben), ellers er det skud tilbage.
                            if (usesLeft == -1) {
                                IO.println("You swing " + weapon.getLongName() + " at the empty air.");
                            } else {
                                IO.println("You fire " + weapon.getLongName() + " into the empty air. " + usesLeft + " shots left.");
                            }
                        }
                    }
                }


                case "help" -> {
                    IO.println("To move in a direction you have 4 options:");
                    IO.println("n or north" + ", e or east" + ", s or south" + ", w or west");
                    IO.println("look = information about your current whereabouts");
                    IO.println("take <item> = pick up an item, for example 'take lamp'");
                    IO.println("drop <item> = leave an item in the room, for example 'drop lamp'");
                    IO.println("eat <item> = eat some food, for example 'eat bread'");
                    IO.println("equip <weapon> = get a weapon from your inventory ready to use");
                    IO.println("attack = use your equipped weapon");
                    IO.println("inventory (or inv) = show what you are carrying");
                    IO.println("health = show your current health");
                    IO.println("exit = quit the game ");
                }

                // Her oversættes både "n" og "north" til goNorth()
                // Metoderne returnerer true, hvis spilleren flyttede sig, ellers false.
                case "n", "north" -> {
                    if (adventure.goNorth()) {
                        IO.println("You moved north");
                        if(adventure.hasEnemies()){
                            IO.println("Beware! There are enemies here.");
                        }
                    } else {
                        IO.println("You cannot go that way");
                    }
                }
                case "e", "east" -> {
                    if (adventure.goEast()) {
                        IO.println("You moved east");
                        if(adventure.hasEnemies()){
                            IO.println("Beware! There are enemies here.");
                        }
                    } else {
                        IO.println("You cannot go that way");
                    }
                }

                case "s", "south" -> {
                    if (adventure.goSouth()) {
                        IO.println("You moved south");
                        if(adventure.hasEnemies()){
                            IO.println("Beware! There are enemies here.");
                        }
                    } else {
                        IO.println("You cannot go that way");
                    }
                }

                case "w", "west" -> {
                    if (adventure.goWest()) {
                        IO.println("You moved west");
                        if(adventure.hasEnemies()){
                            IO.println("Beware! There are enemies here.");
                        }
                    } else {
                        IO.println("You cannot go that way");
                    }
                }

                // "take", "drop", "eat" og "equip" kan ikke være almindelige cases, fordi teksten er forskellig
                // hver gang (take lamp, eat bread...). Derfor tjekker vi med startsWith,
                // og substring klipper kommandoen af, så vi har navnet på tingen tilbage.
                default -> {
                    if (kommando.startsWith("take ")) {
                        String itemName = kommando.substring(5);
                        Item item = adventure.takeItem(itemName);

                        if (item != null) {
                            IO.println("You have taken " + item.getLongName());
                        } else {
                            IO.println("There is nothing like " + itemName + " to take around here");
                        }
                    } else if (kommando.startsWith("drop ")) {
                        String itemName = kommando.substring(5);
                        Item item = adventure.dropItem(itemName);

                        if (item != null) {
                            IO.println("You have dropped " + item.getLongName());
                        } else {
                            IO.println("You don't have anything like " + itemName + " in your inventory");
                        }
                    } else if (kommando.startsWith("eat ")) {
                        String itemName = kommando.substring(4);

                        // Find tingen før den bliver spist, så vi kan skrive dens lange navn bagefter
                        Item item = adventure.findItemAnywhere(itemName);

                        // Gem health før, så vi kan se, om maden var sund eller giftig
                        int healthBefore = adventure.getHealth();
                        EatResult result = adventure.eat(itemName);

                        // eat har tre mulige udfald. En boolean kan kun være true/false,
                        // derfor bruger vi en enum, og switch vælger beskeden ud fra udfaldet.
                        switch (result) {
                            case NOT_FOUND -> IO.println("There is nothing like " + itemName + " to eat around here");
                            case NOT_FOOD -> IO.println("You cannot eat " + item.getLongName());
                            case EATEN -> {
                                if (adventure.getHealth() > healthBefore) {
                                    IO.println("You eat " + item.getLongName() + ". You feel a little better.");
                                } else {
                                    IO.println("You eat " + item.getLongName() + ". That was a mistake.");
                                }
                            }
                        }
                    } else if (kommando.startsWith("equip ")) {
                        String itemName = kommando.substring(6);

                        // Find Item først, så vi kan skrive dens lange navn i beskeden
                        Item item = adventure.findItemAnywhere(itemName);
                        EquipResult result = adventure.equip(itemName);

                        // equip har tre mulige udfald, ligesom eat.
                        switch (result) {
                            case NOT_FOUND ->
                                    IO.println("You don't have anything like " + itemName + " in your inventory");
                            case NOT_WEAPON ->
                                    IO.println("You cannot equip " + item.getLongName() + ", it is not a weapon");
                            case EQUIPPED -> IO.println("You have equipped " + item.getLongName());
                        }
                    }
                }
            }
        }
    }
}





