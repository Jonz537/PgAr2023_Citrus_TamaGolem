import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Universe {

    final static public String[] elements = new String[]{
            "Calabrium", "Mike Oxlong", "Lester Mo", "Nick Gah", "Gabe Itch",
//             "Ped O' Phil", "Hugh Janus", "Bo Nerr", "Ray Pist", "Nick Her"
    };
    static private int balance[][] = new int[elements.length][elements.length];
    static private int maxDamage = 0;

    public static void main(String[] args) {
        
            for (int i = 0; i < 1; i++) {
                generateBalance();
                printBalance();
            }
    }

    public static void check() {
        int counter = 0, sum = 0;
        for (int i = 0; i < balance.length; i++) {
            for (int j = 0; j < balance.length; j++) {
                if (balance[i][j] == 0) {
                    counter++;
                }
                sum += balance[i][j];
            }
            if (sum != 0) {
                System.out.println("ERROR");
                printBalance();
            }
        }

        if (counter != balance.length) {
            System.out.println("ERROR");
            printBalance();
        }

    }

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

            for (int j = i + 1; j < balance.length - 1; j++) {
                balance[i][j] = random.nextInt(1, 5) * randomNegative();
                balance[j][i] = -balance[i][j];
                if (Math.abs(balance[i][j]) > maxDamage) {
                    maxDamage = Math.abs(balance[i][j]);
                }
            }

            int colSum = sumNegColumn(i);
            if (colSum == 0 && i != balance.length - 2) {
                if (balance[balance.length - 2][i] != 1) {
                    balance[balance.length - 2][i] = balance[balance.length - 2][i] - 1;
                    balance[balance.length - 1][i] = 1;
                    balance[i][balance.length - 2] = -balance[balance.length - 2][i];
                    balance[i][balance.length - 1] = -1;
                } else {
                    balance[balance.length - 2][i] = balance[balance.length - 2][i] + 1;
                    balance[balance.length - 1][i] = -1;
                    balance[i][balance.length - 2] = -balance[balance.length - 2][i];
                    balance[i][balance.length - 1] = 1;
                }
            } else if (colSum == 0) {
                i = i - 2;
            } else {
                balance[balance.length - 1][i] = colSum;
                balance[i][balance.length - 1] = -colSum;
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

            for (int j = 0; j < balance.length * 5; j++) {
                if(j % 5 == 0){
                    System.out.print("+");
                } else {
                    System.out.print("-");
                }
            }

            System.out.print("+ \n");

            for (int j = 0; j < balance.length; j++) {
                System.out.print(String.format( "| %2d ", balance[i][j]));
            }
            System.out.print("| \n");
        }

        for (int j = 0; j < balance.length * 5; j++) {
            if(j % 5 == 0){
                System.out.print("+");
            } else {
                System.out.print("-");
            }
        }

        System.out.print("+");
    }

    public static int getMaxDamage() {
        return maxDamage;
    }

// damage = 0 --> equal gem
// damage < 0 --> first better
// damage > 0 --> second better
    public static int calcDamage(TamaGolem golem1, TamaGolem golem2) {

        int gem1 = golem1.currentGem(), gem2 = golem2.currentGem();

        if (gem1 == gem2) {
            return 0;
        } else {
            return balance[gem1 - 1][gem2 - 1];
        }
    }
}
