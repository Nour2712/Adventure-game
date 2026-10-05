// RangedWeapon er et skydevåben, fx en revolver. Det arver fra Weapon.
// Et skydevåben har begrænset ammunition og kan kun bruges, så længe der er skud tilbage
public class RangedWeapon extends Weapon {


    // Hvor mange skud der er tilbage:
    private int ammunition;


    // Konstruktør: navnene sendes videre til Weapon med super(...),
    // og ammunition er RangedWeapons eget ekstra felt (ligesom healthPoints i Food).
    public RangedWeapon(String shortName, String longName, int ammunition) {
        super(shortName, longName);
        this.ammunition = ammunition;

    }

    // Våbnet kan kun bruges, hvis der er skud tilbage.
    @Override
    public boolean canUse() {
        return ammunition > 0;
    }

    // Affyrer et skud og returnerer, hvor mange skud der er tilbage.
    //Så hvis man har 6 skud & bruger 1, så retunere den 5.
    @Override
    public int use() {
        ammunition = ammunition - 1;
        return ammunition;
    }


    // Returnerer hvor mange skud der er tilbage.
    @Override
    public int getUsesLeft() {
        return ammunition;

    }

}
