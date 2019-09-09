import java.util.Scanner;

public class TicTackToe {
    public static void main(String[] args) {
        while (true)
        {
            Controller controller = new Controller();
            controller.play();
            System.out.println("Хотите сыграть еще раз [любую фигню]yes or [n]o: ");
            if(controller.getScanner().next().trim().toLowerCase().equals("n")) return;
        }
    }
}
