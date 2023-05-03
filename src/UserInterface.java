import unibs.InputInterface;
import unibs.MenuManager;

import java.util.HashMap;

public class UserInterface {

    private static MenuManager choseGems = new MenuManager("Choose your gem", Universe.elements.toArray(String[]::new));
            //("Choose your gem");

    public static void enterPlayersData(Game game) {

        Player first, second;

        first = new Player(InputInterface.readNotEmptyString("Enter first player's name"));
        second = new Player(InputInterface.readNotEmptyString("Enter second player's name"));
        game = new Game(first, second);

        game.finale();
    }

    public static String chooseGem(HashMap<String, Integer> gems) {
        return choseGems.chooseStringNoExit();
    }

    public static String menuChooseGem(HashMap<String, Integer> choices) {

        System.out.println("\n Choose gem:");

        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + 1 + " - " + Universe.elements.get(i) + " (" + choices.get(Universe.elements.get(i)) + ")");
        }

        return Universe.elements.get(InputInterface.readInt("Choice: ", 1, choices.size()) - 1);
    }
}
