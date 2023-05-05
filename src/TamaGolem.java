import java.util.Iterator;
import java.util.LinkedList;

public class TamaGolem {

    public static int GEMS_PER_GOLEM = (int) Math.ceil((Game.NUM_ELEMENT + 1) / 3.0) + 1;
    private int healthPoint;
    private LinkedList<Integer> gems = new LinkedList<>();
    private Iterator<Integer> gemIterator;

    /**
     * Create a tamaGolem with healthPoint of the max damage in the equilibrium
     */
    public TamaGolem() {
        this.healthPoint = Universe.getMaxDamage();
        gemIterator = null;
    }

    /**
     * cycle the tamaGolem's gems
     * @return current gem
     */
    public int currentGem() {

        if(gemIterator == null) {
            gemIterator = gems.iterator();
        }

        if((!gemIterator.hasNext()) ) {
            gemIterator = gems.iterator();
        }

        return gemIterator.next();
    }

    /**
     * add gems chosen by the player to his tamagolem
     * @param index gem index
     * @param gemType gem type to add in the golem
     */
    public void addGemToGolem(int index, int gemType) {
        gems.add(index, gemType);
    }

    /**
     * @return tamagolem health points
     */
    public int getHealthPoint() {
        return Math.max(healthPoint, 0);
    }

    /**
     * subtract health point based on damageTaken
     * @param damageHealthPoints damage taken from the tamagolem
     */
    public void receiveDamage(int damageHealthPoints) {
        healthPoint -= Math.abs(damageHealthPoints);
    }

    /**
     * @return a String with important tamagolem's stats
     */
    // TODO fix this
    public String toString() {
        StringBuilder string = new StringBuilder("Golem ");

        string.append(healthPoint).append(" hp, ");

        for (int i = 0; i < GEMS_PER_GOLEM; i++) {
            string.append(Universe.elements.get(currentGem()));

            if(i < GEMS_PER_GOLEM - 1){
                string.append(", ");
            }
        }

        return string.toString();
    }
}
