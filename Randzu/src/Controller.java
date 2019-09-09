import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    Scanner scanner;
    Player players[];
    Field field;

    public Scanner getScanner() {
        return scanner;
    }

    public Controller()
    {
        this.scanner = new Scanner(System.in);
        Random r = new Random();
        System.out.println("Введите имя первого пользователя:");
        Player p1 = this.createPlayer(null,r.nextBoolean()?'X':'O', this.scanner.next());
        System.out.println("Введите имя второго пользователя:");
        this.players = new Player[]{p1, createPlayer(p1, p1.getSymbol()=='X'?'O':'X', this.scanner.next())};
        System.out.println(this.players[0].getName() + " играет " + this.players[0].getSymbol());
        System.out.println(this.players[1].getName() + " играет " + this.players[1].getSymbol());
        int dim[];
        int countToWin = this.getCountOfWin();
        dim = this.getDimensions(countToWin);
        this.field = new Field(countToWin, dim[0], dim[1]);
    }

    private Player createPlayer(Player player1, char symbol, String name)
    {
        if(name.trim().toLowerCase().equals("bot")) return new Bot(symbol, player1 == null?name + "1":name + "2");
        else if(name.trim().toLowerCase().equals("random")) return new RandomPlayer(symbol, player1 == null?name + "1":name + "2");
        else return new HumanPlayer(symbol, name);
    }

    private int[] getDimensions(int countToWin)
    {
        int m, n;
        while (true) {
            try {
                System.out.println("Введите размер поля(2 целых числа:)");
                m = Integer.parseInt(this.scanner.next());
                n = Integer.parseInt(this.scanner.next());
                if(m < 0 || n < 0 ) throw new Exception("Одно или оба из введенных значений отрицательны");
                if(m < countToWin && n < countToWin) throw new Exception("Размер поля не позволяет кому-нибудь выйграть");
                break;
            }
            catch (Exception e)
            {
                System.out.println("Введены некорректные значения/е. " + e.getMessage());
            }
        }
        return new int[]{m,n};
    }

    private int getCountOfWin()
    {
        int countToWin = 0;
        while (true) {
            try {
                System.out.println("Введите количество символов 'X' или 'O' подряд необходимых для выигрыша: ");
                countToWin = Integer.parseInt(this.scanner.next());
                if(countToWin < 0) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Введено некорректное значение.");
            }
        }
        return countToWin;
    }
    public void play()
    {
        Player currentPlayer = this.getPlayer();
        do {
            int point[];
            do {
                point = this.getPoint(currentPlayer);
            } while (!this.field.setValue(point[0], point[1], currentPlayer.getSymbol()));
            char winner = field.checkWinner(point[1], point[0], currentPlayer.getSymbol());
            if (winner == Field.O || winner == Field.X) {
                System.out.println("Победил игрок играющий " + currentPlayer.getSymbol() + " " + currentPlayer.getName());
                field.printField();
                return;
            }
            field.printField();
            currentPlayer = this.nextPlayer(currentPlayer);
        } while (!this.field.isFull());
        System.out.println("Ничья");
    }

    private Player nextPlayer(Player currentPlayer)
    {
        if(currentPlayer.equals(this.players[0])) return this.players[1];
        return this.players[0];
    }

    private Player chooseFirstPlayer()// Если 1 то ходит первый игрок,
    {
        try {
            System.out.println("Введите кто ходит первым : [1]" + this.players[0].getName() +
                                  " или [любую фигню]" + this.players[1].getName());
            return Integer.parseInt(this.scanner.next()) == 1 ? this.players[0] : this.players[1];
        } catch (Exception e) {
            return this.players[1];
        }
    }
    private Player getPlayer()
    {
        Player currentPlayer = null;
        while(currentPlayer == null){
            currentPlayer = this.chooseFirstPlayer();
        }
        return currentPlayer;
    }
    private int[] getPoint(Player currentPlayer)
    {
        int point[];
        do {
            point = currentPlayer.makeTurn(this.field);
        } while (point == null);
        return point;
    }
}
