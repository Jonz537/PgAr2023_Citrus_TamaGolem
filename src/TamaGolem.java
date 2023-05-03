import java.util.Iterator;
import java.util.LinkedList;

public class TamaGolem {


    public static int GEMS_PER_GOLEM = (int) Math.ceil((Game.NUM_ELEMENT + 1) / 3.0) + 1;

    private int healthPoint;
    private LinkedList<Integer> gems = new LinkedList<>();
    private Iterator<Integer> gemIterator;

    /**
     * Create a tamaGolem with healthPoint the max damage in the equilibrium
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
     *
     * @param index gem index
     * @param gemType gem type to add in the golem
     */
    public void addGemToGolem(int index, int gemType) {
        gems.add(gemType);
    }

    /**
     * @return tamagolem health points
     */
    public int getHealthPoint() {
        return this.healthPoint;
    }

    /**
     * subtract health point based on damageTaken
     * @param damageHealthPoints damage taken from the tamagolem
     */
    public void receiveDamage(int damageHealthPoints) {
        healthPoint -= Math.abs(damageHealthPoints);
    }

    public String toString() {
        return "Golem: " + healthPoint + " hp, " + currentGem() + " " + currentGem() + " " + currentGem();
    }
}
