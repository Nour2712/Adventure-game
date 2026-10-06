// Enemy er en fjende i et rum. Den arver IKKE fra Item, fordi fjender ikke kan samles op.
// En fjende kan angribe spilleren og blive angrebet, men kan ikke flytte sig
public class Enemy {

    //Opretter felter:
    // Fjendens navne, ligesom ved Item: kort navn til kommandoer, langt navn til beskeder
    private String shortName;
    private String longName;
    private String description;


    // Fjendens helbred. (Når den når 0, dør fjenden)
    private int health;

    // Fjendens våben (En fjende har altid sit eneste våben equipped)
    private Weapon weapon;

    // Det rum fjenden står i, så den kan droppe sit våben og forsvinde derfra når den dør
    private Room currentRoom;


    // Konstruktør: en fjende får navn, beskrivelse, health, sit våben og det rum, den står i.
    public Enemy(String shortName, String longName, String description, int health, Weapon weapon, Room currentRoom) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }


    //getters
    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public int getHealth(){
        return health;
    }
    // Returnerer fjendens våben
    public Weapon getWeapon() {
        return weapon;
    }



    //hit or die enemy
    // Fjenden bliver ramt og mister health svarende til damage.
    // Når health når 0 eller derunder, dør fjenden.
    public void hit(int damage){
        health = health - damage;

        if (health <= 0) {
            die();
        }
    }

    // Fjenden dør: den dropper sit våben i rummet og forsvinder selv fra rummet.
    private void die(){
        currentRoom.addItem(weapon);
        currentRoom.removeEnemy(this);
    }


    // Returnerer true, hvis fjenden er død
    public boolean isDead(){
        return health <= 0;
    }

    // Fjenden angriber spilleren med sit våben
    public void attack(Player player){
        player.hit(weapon.getDamage());
    }





}


