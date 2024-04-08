package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame, btnChangeDiff;
    JPanel miscPanel = new JPanel();
    JLabel difficultyLabel;
    SudokuDifficulty difficulty = SudokuDifficulty.NORMAL; //Standard difficulty == NORMAL
    SudokuMenu menu;

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        miscPanel.setLayout(new FlowLayout());

        // DONE Add a button to the south to re-start the game via board.newGame()
        /* 
        btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame(difficulty);
            }
        });
        */

        //Initialise menu object from SudokuMenu
        menu = new SudokuMenu(this);
        this.setJMenuBar(menu);

        //Initialise the miscPanel object
        btnChangeDiff = new DifficultyPaneButton("Change difficulty",this);
        difficultyLabel = new JLabel("Current Difficulty: " + difficulty.name());
        //miscPanel.add(btnNewGame);
        miscPanel.add(btnChangeDiff);
        miscPanel.add(difficultyLabel);

        cp.add(miscPanel, BorderLayout.SOUTH);

        // Initialize the game board to start the game
        board.newGame(difficulty);

        pack();     // Pack the UI components, instead of using setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
        setTitle("Sudoku");
        setVisible(true);
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new SudokuMain();  // Let the constructor do the job
            }
         });
    }

    
}