import java.util.Iterator;
import java.util.LinkedList;

public class TamaGolem {

    public static int GEMS_PER_GOLEM = (int) Math.ceil((Game.NUM_ELEMENT + 1) / 3.0) + 1;
    private int healthPoint;
    LinkedList<Integer> gems = new LinkedList<>();
    Iterator<Integer> gemIterator;

    public TamaGolem() {
        this.healthPoint = Universe.getSupPower();
        gemIterator = null;
    }

    public Integer currentGem() {

        if(!gemIterator.hasNext()) {
            gemIterator = gems.iterator();
        }

        return gemIterator.next();
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public String toString() {
        return "Golem: " + healthPoint + " hp, " + currentGem() + " " + currentGem() + " " + currentGem();
    }
}
