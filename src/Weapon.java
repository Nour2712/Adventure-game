// Weapon er abstrakt: der findes ikke "bare et våben", kun bestemte slags.
// Derfor kan man aldrig skrive new Weapon(...), kun new MeleeWeapon(...) eller new RangedWeapon(...).
// Weapon arver fra Item, så våben kan samles op og droppes som alle andre ting.
public abstract class Weapon extends Item {


    // Konstruktør: sender navnene videre til Item med super(...), ligesom i Food.
    public Weapon(String shortName, String longName) {
        super(shortName, longName);
    }

    // Kan våbnet bruges lige nu? Hver subklasse bestemmer selv svaret.
    public abstract boolean canUse();

    // Bruger våbnet og returnerer, hvor mange gange det kan bruges igen.
    // Hver subklasse bestemmer selv, hvad der sker.
    public abstract int use();


}
