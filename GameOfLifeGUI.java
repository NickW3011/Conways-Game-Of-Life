import javax.swing.*;
import java.awt.*;

public class GameOfLifeGUI extends JFrame {

    private int rows;
    private int columns;
    private JButton[][] gridButtons;

    public GameOfLifeGUI(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.gridButtons = new JButton[rows][columns];
        initComponents();
    }

    private void initComponents() {
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(20, 20));
                button.setBackground(Color.WHITE);
                mainPanel.add(button);
                gridButtons[i][j] = button;
            }
        }

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateGameState(Boolean[][] gameState) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gameState[i][j] == false) {
                    gridButtons[i][j].setBackground(Color.WHITE);
                } else {
                    gridButtons[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }
}
