import java.util.ArrayList;

// Adventure er controlleren: den starter spillet og sender beskeder videre
// fra Userinterface til Player. Den er "single point of entry" for Userinterface.
public class Adventure {

    // Spilleren, som holder styr på, hvor man er.
    private final Player player;

    // Konstruktør: bygger kortet og placerer spilleren i startrummet.
    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartRoom());
    }

    // Beder spilleren om at kigge rundt og sender teksten videre.
    public String look() {
        return player.look();
    }

    //Spørg spilleren om der er fjender i rummet, og sender svaret videre:
    public boolean hasEnemies(){
        return player.hasEnemies();
    }

    // Beder spilleren om at gå. Adventure tjekker ikke selv noget,
    // den sender bare svaret (true/false) videre (Law of Demeter).
    public boolean goNorth() {
        return player.goNorth();
    }

    public boolean goEast() {
        return player.goEast();
    }

    public boolean goSouth() {
        return player.goSouth();
    }

    public boolean goWest() {
        return player.goWest();
    }

    // Beder spilleren om at tage en ting og sender svaret videre.
    public Item takeItem(String shortName) {
        return player.takeItem(shortName);
    }

    // Beder spilleren om at lægge en ting og sender svaret videre.
    public Item dropItem(String shortName) {
        return player.dropItem(shortName);
    }

    // Beder spilleren om sin inventory-liste og sender den videre.
    public ArrayList<Item> getInventory() {
        return player.getInventory();

    }

    // Beder spilleren om sin health og sender den videre.
    public int getHealth() {
        return player.getHealth();
    }

    // Beder spilleren om at finde en ting i inventory eller i rummet.
    public Item findItemAnywhere(String shortName) {
        return player.findItemAnywhere(shortName);
    }

    // Beder spilleren om at spise en ting og sender udfaldet videre
    public EatResult eat (String shortName){
        return player.eat(shortName);
    }


    // Beder spilleren om at equippe et våben og sender udfaldet videre.
    public EquipResult equip (String shortName){
        return player.equip(shortName);

    }

    // Beder spilleren om det equippede våben og sender det videre.
    public Weapon getEquippedWeapon(){
        return player.getEquippedWeapon();
    }

    // Beder spilleren om at angribe og sender udfaldet videre.
    public AttackResult attack(){
        return player.attack();

    }

}

