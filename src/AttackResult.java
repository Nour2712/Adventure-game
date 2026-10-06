// De tre mulige udfald af attack-kommandoen.
public enum AttackResult {
    NO_WEAPON,  // spilleren har ikke noget våben equipped
    NO_AMMO,    // våbnet kan ikke bruges (fx ingen skud tilbage)
    NO_SUCH_ENEMY, // der er angivet et navn, men ingen fjende med det navn i rummet
    ATTACKED_AIR, // ingen fjender i rummet, så luften blev angrebet
    ENEMY_DIED, // fjenden blev ramt og døde
    ENEMY_HIT_BACK, // fjenden blev ramt, overlevede og slog igen
}


