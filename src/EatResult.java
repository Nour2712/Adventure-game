//De re mulige udfald af eat-komandoen.
//En boolean kan kun være true/false, med eat har tre udfald. Derfor burger vi enum:
public enum EatResult {
    NOT_FOUND, //item findes hverken i rummet eller i inventory
    NOT_FOOD, //item findes, men er ikke mad
    EATEN, //item er mad og er blevet spist
}
