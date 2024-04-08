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
    JPanel miscPanel;
    JLabel difficultyLabel;
    SudokuDifficulty difficulty = SudokuDifficulty.NORMAL; //Standard difficulty == NORMAL
    JMenuBar menu;

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER);

        //Initialise the miscPanel object
        miscPanel = new JPanel();
        miscPanel.setLayout(new FlowLayout());

        //Initialise Difficulty Label and add it into miscPanel
        difficultyLabel = new JLabel("Current Difficulty: " + difficulty.name());
        miscPanel.add(difficultyLabel);

        /* [FEATURES CHANGED INTO MENU BAR]
        // DONE Add a button to the south to re-start the game via board.newGame()
        btnNewGame = new JButton("New Game");
        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame(difficulty);
            }
        });
        // Adds a button to the zouth to change difficulty
        btnChangeDiff = new JButton("Change difficulty");
        // Uses custom DifficultySelectListener object to create custom JOptionPane to change difficulty
        btnChangeDiff.addActionListener(new DifficultySelectListener(this));
        miscPanel.add(btnNewGame);
        miscPanel.add(btnChangeDiff);
        */

        //Adds miscPanel into bottom (SOUTH) of the container
        cp.add(miscPanel, BorderLayout.SOUTH);

        //Initialise JMenuBar menu object from SudokuMenu and sets the program's menu bar to it
        menu = new SudokuMenu(this);
        this.setJMenuBar(menu);

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