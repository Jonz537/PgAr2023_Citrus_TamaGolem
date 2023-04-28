import unibs.InputInterface;

import java.util.LinkedList;

public class Game {

    public static int NUM_ELEMENT = 5;
    public static int INITIAL_NUM_GOLEM = (int) Math.ceil(((Game.NUM_ELEMENT - 1) * (Game.NUM_ELEMENT - 2)) / (2.0 * TamaGolem.PEBBLE_PER_GOLEM));
    public static int TOT_PEBBLE_BAG = (int) Math.ceil((2.0 * INITIAL_NUM_GOLEM * TamaGolem.PEBBLE_PER_GOLEM) / NUM_ELEMENT) * NUM_ELEMENT;
    Player player1;
    Player player2;

    public void fight() {
        Universe.generateBalance();

        while (player1.getNumberGolem() != 0 || player2.getNumberGolem() != 0) {

            LinkedList<Integer> listPebble1 = new LinkedList<>();
            LinkedList<Integer> listPebble2 = new LinkedList<>();

            System.out.println(player1.getName() + " Choose the element stones for your tamagolem");

            for (int i = 0; i < listPebble1.size(); i++) {
                listPebble1.add(InputInterface.readInt("nigga choose"));
            }

            System.out.println(player2.getName() + " Choose the element stones for your tamagolem");

            for (int i = 0; i < listPebble2.size(); i++) {
                listPebble2.add(InputInterface.readInt("nigga choose"));
            }

            //player1.summon();
            //player2.summon();
        }
    }

}
