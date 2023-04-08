import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer timer;
    private Boolean[][] gameState;
    private int rows, columns;
    private GameOfLifeGUI gui;

    public Game(int rows, int columns, GameOfLifeGUI gui) {
        this.rows = rows;
        this.columns = columns;
        this.gameState = new Boolean[rows][columns];
        this.gui = gui;
        randomizeGameState();
    }

    public void randomizeGameState() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean randomBoolean = random.nextBoolean();
                gameState[i][j] = randomBoolean;
            }
        }
        gui.updateGameState(gameState);
    }

    public void startGame() {
        // create a new timer that updates the game board every 0.1 seconds
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // update the game board here
                updateGameState();
            }
        }, 0, 100);
    }

    private void updateGameState() {
        Boolean[][] tempGameState = new Boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int neighbours = countNeighbours(i, j);
                if (gameState[i][j] == false && neighbours == 3) {
                    tempGameState[i][j] = true; 
                } else if (gameState[i][j] == true && neighbours < 2) {
                    tempGameState[i][j] = false;
                } else if (gameState[i][j] == true && neighbours > 3) {
                    tempGameState[i][j] = false;
                } else {
                    tempGameState[i][j] = gameState[i][j];
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gameState[i][j] = tempGameState[i][j];
            }
        }
        gui.updateGameState(gameState);
    }

    private int countNeighbours(int i, int j) {
        int counter = 0;
        if(i-1 >= 0) {
            if(gameState[i-1][j]) {
                counter++;
            }
        }
        if(i-1 >= 0 && j-1 >= 0) {
            if(gameState[i-1][j-1]) {
                counter++;
            }
        }
        if(j-1 >= 0) {
            if(gameState[i][j-1]) {
                counter++;
            }
        }
        if(i+1 < rows && j-1 >= 0) {
            if(gameState[i+1][j-1]) {
                counter++;
            }
        }
        if(i+1 < rows) {
            if(gameState[i+1][j]) {
                counter++;
            }
        }
        if(i+1 < rows && j+1 < columns) {
            if(gameState[i+1][j+1]) {
                counter++;
            }
        }
        if(j+1 < columns) {
            if(gameState[i][j+1]) {
                counter++;
            }
        }
        if(i-1 >= 0 && j+1 < columns) {
            if(gameState[i-1][j+1]) {
                counter++;
            }
        }
        return counter;
    }
}
