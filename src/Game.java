import java.util.HashMap;

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

    public int fight() {
        Universe.generateEquilibrium();

        summon(player1);
        summon(player2);

        System.out.println(player1.getTamaGolem().toString());
        System.out.println(player2.getTamaGolem().toString());

        while (player1.getNumberGolem() != 0 || player2.getNumberGolem() != 0) {

            int damage = Universe.calcDamage(player1.getTamaGolem(), player2.getTamaGolem());

            if (damage < 0) {
                player1.getTamaGolem().receiveDamage(damage);
                System.out.println(player2.getName() + "'s tamagolem inflicted " + (-damage) + " damage to the " + player1.getName() + "'s tamagolem");
            } else if (damage > 0) {
                player2.getTamaGolem().receiveDamage(damage);
                System.out.println(player1.getName() + "'s tamagolem inflicted " + damage + " damage to the " + player2.getName() + "'s tamagolem");
            }

            if (player1.getTamaGolem().getHealthPoint() <= 0) {
                System.out.println(player1.getName() + "'s tamagolem is dead " + player1.getNumberGolem());
                player1.decreaseGolem();

                if(player1.getNumberGolem() > 0) {
                    summon(player1);
                }
            } else if (player2.getTamaGolem().getHealthPoint() <= 0) {
                System.out.println(player2.getName() + "'s tamagolem is dead " + player2.getNumberGolem());
                player2.decreaseGolem();

                if(player2.getNumberGolem() > 0) {
                    summon(player2);
                }
            }
        }

        if (player1.getNumberGolem() == 0) {
            return 1;
        } else if (player2.getNumberGolem() == 0){
            return 2;
        }

        return 0;
    }

    public void finale() {

        int winner = fight();

        if (winner == 1) {
            System.out.println(player2.getName() + " wins");
        } else if (winner == 2) {
            System.out.println(player1.getName() + " wins");
        }
    }

    private void initializeHashMap() {
        for (int i = 0; i < NUM_ELEMENT; i++) {
            gems.put(Universe.elements.get(i), TOT_GEM_BAG/NUM_ELEMENT);
        }
    }

    /**
     *
     * @param player
     */
    private void summon(Player player) {
        TamaGolem tamaGolem = new TamaGolem();

        for (int i = 0; i < TamaGolem.GEMS_PER_GOLEM; i++) {

            String gemKey;

            do {
                gemKey = UserInterface.menuChooseGem(gems);

                if(gems.get(gemKey) <= 0) {
                    System.out.println("Brutto frocio svegliati fuori mongoloide di merda ;)");
                }
            } while(gems.get(gemKey) <= 0);

            tamaGolem.addGemToGolem(i, Universe.elements.indexOf(gemKey));
            gems.put(gemKey, gems.get(gemKey) - 1);
        }

        player.setTamaGolem(tamaGolem);
    }
}
