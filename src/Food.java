// Food arver fra Item (arv): Food er en subklasse, Item er superklassen.
// Food får automatisk shortName, longName og deres getters fra Item.
// Et Food-objekt ER også et Item (is-a som Ian og Jakob snakkede om), så det kan samles op og droppes som alle andre ting.
public class Food extends Item {


    // Hvor meget health spilleren får ved at spise maden. Kan være negativ, hvis maden er giftig:
    private int healthPoints;

    // Konstruktør: super(...) kalder Items konstruktør, så navnene bliver gemt i Item-delen af objektet.
    // super(...) skal stå først. healthPoints er Foods egen ekstra ting.
    public Food (String shortName, String longName, int healthPoints){
        super(shortName, longName);
        this.healthPoints = healthPoints;
    }


    //Returnerer madens healthPoints:
    public int getHealthPoints(){
        return healthPoints;
    }


}
