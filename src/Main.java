import unibs.MenuManager;

public class Main {

    private final static MenuManager menuManager = new MenuManager("What do you wanna do?", new String[]{"Fight",
            "Print equilibrium list", "Print equilibrium table"});

    public static void main(String[] args) {

        Game game = null;
        boolean exit = true;

        while (exit) {
            switch (menuManager.choose()) {
                case 0 -> UserInterface.enterPlayersData(game);
                case 1 -> Universe.printEquilibriumList();
                case 2 -> Universe.printEquilibriumTable();
                case -1 -> exit = false;
            }
        }
        System.out.println("Too bad my man try again!");
    }
}