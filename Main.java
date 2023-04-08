public class Main {
    public static void main(String[] args) {
        int rows = 30, columns = 30;
        GameOfLifeGUI gui = new GameOfLifeGUI(rows, columns);
        Game game = new Game(rows, columns, gui);
        game.startGame(); 
    }
}