import java.util.Iterator;
import java.util.LinkedList;

public class TamaGolem {

    public static int PEBBLE_PER_GOLEM = (int) Math.ceil((Game.NUM_ELEMENT + 1) / 3.0) + 1;
    int healthPoint;
    LinkedList<Integer> pebble = new LinkedList<>();
    Iterator<Integer> pebbleIt;

    public TamaGolem() {
        this.healthPoint = Universe.getSupPower();
        pebbleIt = pebble.iterator();
    }

    public Integer currentPebble() {

        if(!pebbleIt.hasNext()) {
            pebbleIt = pebble.iterator();
        }

        return pebbleIt.next();
    }
}
