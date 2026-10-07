// MeleeWeapon er et nærkampsvåben, fx et sværd. Det arver fra Weapon.
// Et nærkampsvåben kan bruges et uendeligt antal gange modsat et rangeWeapon hvor der er begrænset ammunition .
public class MeleeWeapon extends Weapon {

    // Konstruktør: sender navnene videre til Weapon med super(...), som sender dem videre til Item
    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);

    }


    //Et nærkampsvåben(MeleeWeapon) kan altid bruges:
    @Override
    public boolean canUse() {
        return true;
    }


    //Et nærkampsvåben (MeleeWeapon) løber aldrig tør -1 betyder "ubegrænset":
    @Override
    public int use() {
        return -1;
    }

    // Et nærkampsvåben kan altid bruges igen. -1 betyder "ubegrænset".
    @Override
    public int getUsesLeft(){
        return -1;
    }


}
