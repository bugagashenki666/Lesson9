import java.util.Random;

public class RandomPlayer extends Player {
    public RandomPlayer(char symbol, String name) {
        super(symbol, name);
    }

    @Override
    public int[] makeTurn(Field field) {
        Random random = new Random();
        return new int[]{random.nextInt(field.getData()[0].length),random.nextInt(field.getData().length)};
    }
}
