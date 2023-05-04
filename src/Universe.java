import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Universe {

    final static public ArrayList<String> elements = new ArrayList<>(List.of("Calabrium", "Mike Oxlong", "Mo Lester", "Nick Gah", "Gabe Itch"
//            "Ped O' Phil", "Hugh Janus", "Bo Nerr", "Ray Pist", "Nick Her"
    ));

    static private int equilibrium[][] = new int[elements.size()][elements.size()]; //SANENESS BALLAST EQUIPOISE
    static private int maxDamage = 0;

    public static void main(String[] args) {
        generateEquilibrium();
        printEquilibriumTable();
        System.out.println();
        printEquilibriumList();
    }

    public static int getMaxDamage() {
        return maxDamage;
    }

    /**
     * random generation of the equilibrium for each game
     */
    public static void generateEquilibrium() {
        Random random = new Random();

        for (int i = 0; i < equilibrium.length - 1; i++) {

            for (int j = i + 1; j < equilibrium.length - 1; j++) {

                // genera casuale riga per riga
                equilibrium[i][j] = random.nextInt(1, 5) * randomNegative();
                equilibrium[j][i] = -equilibrium[i][j];

                // controllo valore di danno massimo per la vita del golem
                if (Math.abs(equilibrium[i][j]) > maxDamage) {
                    maxDamage = Math.abs(equilibrium[i][j]);
                }
            }

            // calcola la somma della colonna per generare l'ultimo numero
            int colSum = sumNegColumn(i);

            if (colSum > maxDamage) {
                maxDamage = colSum;
            }

            if (colSum == 0 && i != equilibrium.length - 2) {
                if (equilibrium[equilibrium.length - 2][i] != 1) {
                    equilibrium[equilibrium.length - 2][i] = equilibrium[equilibrium.length - 2][i] - 1;
                    equilibrium[equilibrium.length - 1][i] = 1;
                    equilibrium[i][equilibrium.length - 2] = -equilibrium[equilibrium.length - 2][i];
                    equilibrium[i][equilibrium.length - 1] = -1;
                } else {
                    equilibrium[equilibrium.length - 2][i] = equilibrium[equilibrium.length - 2][i] + 1;
                    equilibrium[equilibrium.length - 1][i] = -1;
                    equilibrium[i][equilibrium.length - 2] = -equilibrium[equilibrium.length - 2][i];
                    equilibrium[i][equilibrium.length - 1] = 1;
                }
            } else if (colSum == 0) {
                // caso particolare della modifica della penultima colonna, si rigenera il numero per
                // non generare una reazione a cascata
                i = i - 2;
            } else {
                equilibrium[equilibrium.length - 1][i] = colSum;
                equilibrium[i][equilibrium.length - 1] = -colSum;
            }
        }
    }

    /**
     *
     * @return
     */
    public static int randomNegative() {
        Random random = new Random();
        if (random.nextInt(0, 2) % 2 == 0) {

            return 1;
        }
        return -1;
    }

    /**
     *
     * @param index
     * @return
     */
    public static int sumNegColumn(int index) {
        int tot = 0;
        for (int i = 0; i < equilibrium.length - 1; i++) {
            tot += equilibrium[i][index];
        }
        return -tot;
    }

    /**
     * calculation of the damage based on the given gems
     * @param golem1 gem
     * @param golem2 gem
     * @return damage inflicted
     */
    public static int calcDamage(TamaGolem golem1, TamaGolem golem2) {
        // damage = 0 --> equal gem
        // damage < 0 --> second better
        // damage > 0 --> first better

        int gem1 = golem1.currentGem(), gem2 = golem2.currentGem();

        if (gem1 == gem2) {
            return 0;
        } else {
            return equilibrium[gem1][gem2];
        }
    }

    /**
     * ligma balls wanted to do an efficient algorithm and check if worked
     */
    public static void check() {
        int counter = 0, sum = 0;
        for (int i = 0; i < equilibrium.length; i++) {
            for (int j = 0; j < equilibrium.length; j++) {
                if (equilibrium[i][j] == 0) {
                    counter++;
                }
                sum += equilibrium[i][j];
            }
            if (sum != 0) {
                System.out.println("ERROR");
                printEquilibriumTable();
            }
        }

        if (counter != equilibrium.length) {
            System.out.println("ERROR");
            printEquilibriumTable();
        }
    }

    /**
     * print equilibrium with a table where is printed for each element the damage inflicted and received
     */
    public static void printEquilibriumTable() {
        for (int i = 0; i < equilibrium.length; i++) {
            for (int j = 0; j < equilibrium.length * 5 + 14; j++) {
                System.out.print("-");
            }

            System.out.println("+");
            System.out.print(String.format("%12s  ", elements.get(i)));

            for (int j = 0; j < equilibrium.length; j++) {
                if (equilibrium[i][j] > 0) {
                    System.out.print(String.format( "| %2d ", equilibrium[i][j]));
                } else {
                    System.out.print("| // ");
                }
            }
            System.out.println("|");
        }

        for (int j = 0; j < equilibrium.length * 5 + 14; j++) {
            System.out.print("-");
        }

        System.out.print("+");
    }

    /**
     * print equilibrium with a list where each element have only the damage inflicted to other's elements
     */
    public static void printEquilibriumList() {
        for (int i = 0; i < equilibrium.length; i++) {
            System.out.println(elements.get(i));
            for (int j = 0; j < equilibrium.length; j++) {
                if (equilibrium[i][j] > 0) {
                    System.out.println("\t-" + elements.get(j) + ": " + equilibrium[i][j]);
                }
            }
        }
    }
}
