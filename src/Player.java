import java.util.LinkedList;

public class Player {

    private String name;
    private int numberGolem = Game.INITIAL_NUM_GOLEM;
    private TamaGolem tamaGolem = new TamaGolem();

    public Player(String name) {
        this.name = name;
    }

    public void setTamaGolem(TamaGolem tamaGolem) {
       this.tamaGolem = tamaGolem;
    }

    public int getNumberGolem() {
        return numberGolem;
    }

    public String getName() {
        return name;
    }

    public TamaGolem getTamaGolem() {
        return tamaGolem;
    }

    public void decreaseGolem() {
        this.numberGolem --;
    }
}
