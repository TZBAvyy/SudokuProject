package sudoku;

import java.awt.*;
import javax.swing.*;
/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // private variables
    GameBoardPanel board = new GameBoardPanel(this);
    JMenuBar menu = new SudokuMenu(this);
    SudokuDifficulty difficulty = SudokuDifficulty.NORMAL; //Standard difficulty == NORMAL
    //JButton btnNewGame, btnChangeDiff;
    JPanel miscPanel;
    JLabel difficultyLabel = new JLabel(), statusLabel = new JLabel();
    

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        //Sets the program's menu bar
        setJMenuBar(menu);

        cp.add(board, BorderLayout.CENTER);

        //Initialize the game board to start the game
        board.newGame(difficulty);

        //Initialise the miscPanel object
        miscPanel = new JPanel();
        miscPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));

        //Initialise Difficulty Label and add it into miscPanel
        difficultyLabel.setText("Current Difficulty: " + difficulty.name());
        difficultyLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        miscPanel.add(difficultyLabel);

        //Label changes when this is triggered {Cell Input, New Game, Reset Game, Difficulty Change (New Game)}
        statusLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        miscPanel.add(statusLabel);

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