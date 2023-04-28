import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Universe {

    final static public String[] elements = new String[]{
            "Calabrium", "Mike Oxlong", "Lester Mo", "Nick Gah", // "Gabe Itch",
            // "Ped O' Phil", "Hugh Janus", "Bo Nerr", "Ray Pist", "Nick Her"
    };
    static private int balance[][] = new int[elements.length][elements.length];
    static private Set<Integer> powerSet = new HashSet<>();

    public static int randomNegative() {
        Random random = new Random();
        if (random.nextInt(0, 2) % 2 == 0) {

            return 1;
        }
        return -1;
    }

    public static void generateBalance() {
        Random random = new Random();

        for (int i = 0; i < balance.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                balance[i][j] = random.nextInt(1, 5) * randomNegative();
                balance[j][i] = -balance[i][j];
                powerSet.add(Math.abs(balance[i][j]));
            }
        }

        for (int i = 0; i < balance.length - 1; i++) {
            int colSum = sumNegColumn(i);
            if (colSum == 0) {
                balance[balance.length - 2][i] = balance[balance.length - 2][i] - 1;
                balance[balance.length - 1][i] = 1;
                balance[i][balance.length - 2] = balance[balance.length - 2][i] - 1;
                balance[i][balance.length - 1] = 1;
            } else {
                balance[balance.length - 1][i] = colSum;
                balance[i][balance.length - 1] = colSum;
            }
        }
    }

    public static int sumNegColumn(int index) {
        int tot = 0;
        for (int i = 0; i < balance.length - 1; i++) {
            tot += balance[i][index];
        }
        return -tot;
    }

    public static void printBalance() {
        for (int i = 0; i < balance.length; i++) {
            for (int j = 0; j < balance.length; j++) {
                System.out.print(String.format( "%2d ", balance[i][j]));
            }
            System.out.println();
        }
    }

//    public static void main(String[] args) {
//        generateBalance();
//        printBalance();
//    }

    public static int getSupPower() {
        return 0;
    }
}
