public class Map {  // Single Responsibility Principle: klassen har kun ét ansvar som er  at bygge kortet.

    // Det rum spilleren starter i.
    private final Room startRoom;

    // Konstruktør: opretter de 9 rum, forbinder dem og lægger items i nogle af dem.
    public Map() {
        Room room1 = new Room("Room 1", "A plain room with only two doors ");
        Room room2 = new Room("Room 2", "An ordinary room with two doors ");
        Room room3 = new Room("Room 3", "A quiet room in the corner");
        Room room4 = new Room("Room 4", "A small room with two doors");
        Room room5 = new Room("Room 5", "You found the hidden room");
        Room room6 = new Room("Room 6", "A room with three doors");
        Room room7 = new Room("Room 7", "A room in the corner with two doors");
        Room room8 = new Room("Room 8", "A room with three doors");
        Room room9 = new Room("Room 9", "The last room, with two doors");


        // East/West forbindelser
        room1.setEast(room2);
        room2.setWest(room1);

        room2.setEast(room3);
        room3.setWest(room2);

        room4.setEast(room5);
        room5.setWest(room4);

        room5.setEast(room6);
        room6.setWest(room5);

        room7.setEast(room8);
        room8.setWest(room7);

        room8.setEast(room9);
        room9.setWest(room8);

        // North/South forbindelser
        room1.setSouth(room4);
        room4.setNorth(room1);

        room2.setSouth(room5);
        room5.setNorth(room2);

        room3.setSouth(room6);
        room6.setNorth(room3);

        room4.setSouth(room7);
        room7.setNorth(room4);

        room5.setSouth(room8);
        room8.setNorth(room5);

        room6.setSouth(room9);
        room9.setNorth(room6);


        // Items i rummene
        // Almindelige ting i rummene.
        // Sammen med maden og våbnene nedenfor har rummene 0, 1, 2 eller 3 ting,
        // så vi kan teste alle tilfælde (fx Room 3 = 0, Room 2 = 1, Room 1 = 2, Room 5 = 3).
        room1.addItem(new Item("lamp", "a shiny brass lamp"));
        room1.addItem(new Item("coins", "some gold coins"));


        room5.addItem(new Item("key", "a small golden key"));
        room5.addItem(new Item("map", "an old torn map"));
        room5.addItem(new Item("torch", "a burning torch"));


        // Mad i rummene: positive healthPoints er sund mad, negative er giftig mad.
        // Food-objekter kan lægges i rummenes liste af Item, fordi et Food også er et Item.
        // Derfor virker take, drop og inventory også for mad, uden at koden er ændret.
        room7.addItem(new Food("bread", "a loaf of stale bread", 10));
        room4.addItem(new Food("mushroom", "a pale glowing mushroom", -50));
        room6.addItem(new Food("apple", "a shiny red apple", 20));
        room8.addItem(new Food("berries", "a handful of dark purple berries", -20));
        room9.addItem(new Food("cake", "a slice of chocolate cake", 30));


        //Tilføjer våben i rummene: MeleeWeapon kan bruges uendeligt, RangeWeapon har begrænset ammunition
        // Det er kun her i Map, at subklasserne nævnes. Alle andre steder kender koden kun Weapon.
        room2.addItem(new MeleeWeapon("sword", "a rusty sword"));
        room6.addItem(new MeleeWeapon("axe", "a heavy axe"));
        room8.addItem(new RangedWeapon("revolver", "an old revolver", 6));
        room9.addItem(new RangedWeapon("bow", "a wooden bow", 2));

        // Spilleren starter i rum 1
        startRoom = room1;
    }

    //Giver startrummet videre, så Adventure kan give det til Player.
    public Room getStartRoom() {
        return startRoom;
    }


}
