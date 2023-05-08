import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Game {

    public static int NUM_ELEMENT = Universe.elements.size();
    public static int INITIAL_NUM_GOLEM = (int) Math.ceil(((Game.NUM_ELEMENT - 1) * (Game.NUM_ELEMENT - 2)) / (2.0 * TamaGolem.GEMS_PER_GOLEM));
    public static int TOT_GEM_BAG = (int) Math.ceil((2.0 * INITIAL_NUM_GOLEM * TamaGolem.GEMS_PER_GOLEM) / NUM_ELEMENT) * NUM_ELEMENT;
    private HashMap<String, Integer> gems = new HashMap<>();
    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        initializeHashMap();
    }

    /**
     * first and second step which generate the equilibrium and start the fight
     * print the winner of the fight
     */
    public void fight() {
        Universe.generateEquilibrium();

        summon(player1);
        summon(player2);

        while (player1.getNumberGolem() > 0 && player2.getNumberGolem() > 0) {
            //System.out.println("N golem " + player1.getName() + ": " + player1.getNumberGolem() + " \n N golem " + player2.getName() +": " + player2.getNumberGolem());

            int damage = Universe.calcDamage(player1.getTamaGolem(), player2.getTamaGolem());

            System.out.println(player1.getName() + " " + player1.getTamaGolem().toString());
            System.out.println(player2.getName() + " " + player2.getTamaGolem().toString());
            System.out.println();

            if (damage < 0) {
                // player 1 got damaged
                player1.getTamaGolem().receiveDamage(damage);
                System.out.println(player2.getName() + "'s tamagolem inflicted " + (-damage) + " damage to the " + player1.getName() + "'s tamagolem");
                System.out.println(player1.getName() + " your tamagolem has " + player1.getTamaGolem().getHealthPoint() + " hp left");
                System.out.println();
            } else if (damage > 0) {
                // player 2 got damaged
                player2.getTamaGolem().receiveDamage(damage);
                System.out.println(player1.getName() + "'s tamagolem inflicted " + damage + " damage to the " + player2.getName() + "'s tamagolem");
                System.out.println(player2.getName() + " your tamagolem has " + player2.getTamaGolem().getHealthPoint() + " hp left");
                System.out.println();
            }

            // tamagolem death
            if (player1.getTamaGolem().getHealthPoint() <= 0) {
                System.out.println(player1.getName() + "'s tamagolem is dead (tamagolem's left " + (player1.getNumberGolem() - 1) + ")");
                System.out.println();
                player1.decreaseGolem();

                if(player1.getNumberGolem() > 0) {
                    summon(player1);
                }

            } else if (player2.getTamaGolem().getHealthPoint() <= 0) {
                System.out.println(player2.getName() + "'s tamagolem is dead (tamagolem's left " + (player2.getNumberGolem() - 1) + ")");

                player2.decreaseGolem();

                if(player2.getNumberGolem() > 0) {
                    summon(player2);
                }
            }

            // waiting
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // declare winner
        if (player1.getNumberGolem() == 0) {
            finale(1);
        } else if (player2.getNumberGolem() == 0){
            finale(2);
        }
    }

    /**
     * end of the game where is declared the winner
     */
    public void finale(int winner) {
        if (winner == 1) {
            System.out.println(player2.getName() + " wins");
        } else if (winner == 2) {
            System.out.println(player1.getName() + " wins");
        }

        UserInterface.chooseBalanceVisualization();
    }

    /**
     * initialize elements HashMap, "name of the element": "number of this element's gem"
     */
    private void initializeHashMap() {
        for (int i = 0; i < NUM_ELEMENT; i++) {
            gems.put(Universe.elements.get(i), TOT_GEM_BAG/NUM_ELEMENT);
        }
    }

    /**
     * summon a new tamagolem
     * @param player to assign the tamagolem
     */
    private void summon(Player player) {
        HashMap<String, Integer> saveGems = new HashMap<>(gems);

        do {
            TamaGolem tamaGolem = new TamaGolem();

            for (int i = 0; i < TamaGolem.GEMS_PER_GOLEM; i++) {

                String gemKey;

                do {
                    gemKey = UserInterface.menuChooseGem(gems, player);

                    if(gems.get(gemKey) <= 0) {
                        System.out.println("There are no more gems of \"" + gemKey + "\", you Bafoon!");
                    }
                } while(gems.get(gemKey) <= 0);

                tamaGolem.addGemToGolem(i, Universe.elements.indexOf(gemKey));
                gems.put(gemKey, gems.get(gemKey) - 1);
            }
            player.setTamaGolem(tamaGolem);
            if (sameGems()) {
                System.out.println("You choose the same gems as the other player, Fool!");
                gems = new HashMap<>(saveGems);
            }
        } while (sameGems());
    }

    /**
     * check if player1's golem has the same gems as the second's
     * @return true if the golems have same gems in same order
     */
    private boolean sameGems() {
        return player1.getTamaGolem().compareGems(player2.getTamaGolem());
    }
}
