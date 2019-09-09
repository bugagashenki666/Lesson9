public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, String name) {
        super(symbol, name);
    }

    @Override
    public int[] makeTurn(Field field) {
        try{
            System.out.println(this.getName() + "("+ this.getSymbol() + ") введите координаты: ");
            return new int[]{Integer.parseInt(scanner.next()),Integer.parseInt(scanner.next())};}
        catch(Exception e)
        {
            System.out.println("Введите 2 целых числа");
        }
        return null;
    }
}
