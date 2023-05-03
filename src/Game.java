import java.util.HashMap;

public class Game {

    public static int NUM_ELEMENT = 5;
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

    public void fight() {
        Universe.generateBalance();

        summon(player1);
        summon(player2);

        System.out.println(player1.getTamaGolem().toString());
        System.out.println(player2.getTamaGolem().toString());


        while (player1.getNumberGolem() != 0 || player2.getNumberGolem() != 0) {

            int damage = Universe.calcDamage(player1.getTamaGolem(), player2.getTamaGolem());

            if(damage < 0) {
                player1.getTamaGolem().setHealthPoint(player1.getNumberGolem() - Math.abs(damage));
            } else if (damage > 0) {
                player2.getTamaGolem().setHealthPoint(player2.getNumberGolem() - Math.abs(damage));
            }

            if(player1.getTamaGolem().getHealthPoint() <= 0) {
                summon(player1);
                player1.decreaseGolem();
            } else if(player2.getTamaGolem().getHealthPoint() <= 0) {
                summon(player2);
                player2.decreaseGolem();
            }
        }
    }

    private void initializeHashMap() {
        for (int i = 0; i < NUM_ELEMENT; i++) {
            gems.put(Universe.elements[i], TOT_GEM_BAG/NUM_ELEMENT);
        }
    }

    private void summon(Player player) {
        TamaGolem tamaGolem = new TamaGolem();

        for (int i = 0; i < TamaGolem.GEMS_PER_GOLEM; i++) {

            String gem;

            do {
                gem = UserInterface.choseGem();
            } while(gems.get(gem) > 0);

            tamaGolem.gems.add(i, gems.get(gem));
        }

        player.setTamaGolem(tamaGolem);
    }
}
