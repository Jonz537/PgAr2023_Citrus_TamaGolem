import unibs.InputInterface;
import unibs.MenuManager;

public class UserInterface {

    private static MenuManager choseGems = new MenuManager("Chose your gem", Universe.elements);

    public static void enterPlayersData(Game game) {

        Player first, second;

        first = new Player(InputInterface.readNotEmptyString("Enter first player's name"));
        second = new Player(InputInterface.readNotEmptyString("Enter first player's name"));

        game = new Game(first, second);

    }

    public static String choseGem() {
        return choseGems.chooseStringNoExit();
    }

}
