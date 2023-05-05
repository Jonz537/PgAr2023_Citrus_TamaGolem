import unibs.InputInterface;
import unibs.MenuManager;

import java.util.HashMap;

public class UserInterface {


    private static MenuManager displayMenu = new MenuManager("Choose how you want to display the equilibrium", new String[]{
            "Table", "List"
    });

    /**
     * start a new game
     * @param game new match to play with a different equilibrium
     */
    public static void enterPlayersData(Game game) {

        Player first, second;
        first = new Player(InputInterface.readNotEmptyStringSingleWord("Enter first player's name"));
        second = new Player(InputInterface.readNotEmptyStringSingleWord("Enter second player's name"));
        game = new Game(first, second);

        game.fight();
    }

    /**
     * menu with the gems available to choose
     * @param choices available for each element
     * @return the chosen gem
     */
    public static String menuChooseGem(HashMap<String, Integer> choices, Player player) {

        System.out.println("\n" + player.getName() + " choose a gem:");

        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + " - " + Universe.elements.get(i) + " (" + choices.get(Universe.elements.get(i)) + ")");
        }

        return Universe.elements.get(InputInterface.readInt("Choice: ", 1, choices.size()) - 1);
    }

    /**
     * give the user the chooise between table and list type of visualization for the equilibrium
     */
    public static void chooseBalanceVisualization() {

        switch (displayMenu.chooseNoExit()) {
            case 0 -> Universe.printEquilibriumTable();
            case 1 -> Universe.printEquilibriumList();
        }

    }
}
