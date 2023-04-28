import java.util.LinkedList;

public class Player {

    private String name;
    private int numberGolem = Game.INITIAL_NUM_GOLEM;

    public void summon(TamaGolem tamaGolem, LinkedList<Integer> pebbleChosen) {
        tamaGolem.pebble = pebbleChosen;
    }

    public int getNumberGolem() {
        return numberGolem;
    }

    public String getName() {
        return name;
    }
}
