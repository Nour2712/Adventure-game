// Weapon er abstrakt: der findes ikke "bare et våben", kun bestemte slags.
// Derfor kan man aldrig skrive new Weapon(...), kun new MeleeWeapon(...) eller new RangedWeapon(...).
// Weapon arver fra Item, så våben kan samles op og droppes som alle andre ting.
public abstract class Weapon extends Item {

    // Hvor meget skade våbnet giver, når det rammer. Alle våben har damage,
    // derfor ligger feltet her og ikke i subklasserne.
    private int damage;

    // Konstruktør: sender navnene videre til Item med super(...), ligesom i Food.
    //og damage er Weapons eget ekstra felt.
    public Weapon(String shortName, String longName, int damage) {
        super(shortName, longName);
        this.damage = damage;
    }

    // Returnerer hvor meget skade våbnet giver.
    public int getDamage(){
        return damage;
    }

    // Kan våbnet bruges lige nu? Hver subklasse bestemmer selv svaret.
    public abstract boolean canUse();

    // Bruger våbnet og returnerer, hvor mange gange det kan bruges igen.
    // Hver subklasse bestemmer selv, hvad der sker.
    public abstract int use();


    // Returnerer hvor mange gange våbnet kan bruges endnu. -1 betyder ubegrænset.
    public abstract int getUsesLeft();






}
