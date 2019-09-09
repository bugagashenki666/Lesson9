import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    Scanner scanner;

    public Player()
    {

    }

    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public int[]makeTurn(Field field)
    {
        return null;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
