package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60;     // Cell width/height in pixels
    public static final int BOARD_WIDTH  = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;
                                                // Board width/height in pixels

    // Define properties
    /** The game board composes of 9x9 Cells (customized JTextFields) */
    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    /** It also contains a Puzzle with array numbers and isGiven */
    private Puzzle puzzle = new Puzzle();

    /** Constructor */
    public GameBoardPanel() {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

        //Initialise border based on types (thickness:1 for default, thickness:3 for seperating squares)
        Border left = BorderFactory.createMatteBorder(1, 3, 1, 1, Color.BLACK);
        Border bottom = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK);
        Border bottomleft = BorderFactory.createMatteBorder(1, 3, 3, 1, Color.BLACK);
        Border standard = BorderFactory.createMatteBorder(1, 1,1, 1, Color.BLACK);

        // Allocate the 2D array of Cell, and added into JPanel.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col] = new Cell(row, col);
                super.add(cells[row][col]);   // JPanel
                //Sets the border for every cell based on position
                if ((col)%3==0 && (row+1)%3==0) {
                    cells[row][col].setBorder(bottomleft);
                } else if ((row+1)%3==0) {
                    cells[row][col].setBorder(bottom);
                } else if ((col)%3==0) {
                    cells[row][col].setBorder(left);
                } else {
                    cells[row][col].setBorder(standard);
                }
            }
        }

        // DONE Allocate a common listener as the ActionEvent listener for all the
        //  Cells (JTextFields)
        CellInputListener listener = new CellInputListener();

        // DONE Adds this common listener to all editable cells
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].addKeyListener(listener);   // For all editable rows and cols
                }
            }
        }

        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
        * Generate a new puzzle; and reset the game board of cells based on the puzzle.
        * You can call this method to start a new game.
        */
    public void newGame(SudokuDifficulty difficulty) {
        // Generate a new puzzle based on difficulty
        if (difficulty==SudokuDifficulty.EASY) {
            //Easy difficulty gives 51 cells as clues
            puzzle.newPuzzle(30);
        } else if (difficulty==SudokuDifficulty.NORMAL) {
            //Normal difficulty gives 41 cells as clues
            puzzle.newPuzzle(40);
        } else if (difficulty==SudokuDifficulty.HARD) {
            //Hard difficulty gives 31 cells as clues
            puzzle.newPuzzle(50);
        } else if (difficulty==SudokuDifficulty.CHALLENGE) {
            //Challenge difficulty gives 26 cells as clues
            puzzle.newPuzzle(55);
        }
        
        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }

    public void resetGame() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (!puzzle.isGiven[row][col]) {
                    cells[row][col].status = CellStatus.TO_GUESS;
                    cells[row][col].paint();
                }
            }
        }
    }

    /**
        * Return true if the puzzle is solved
        * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
        */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                }
            }
        }
        return true;
    }

    // DONE Define a Listener Inner Class for all the editable Cells
    private class CellInputListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {   
        }
        @Override
        public void keyPressed(KeyEvent e) {    
        }
        @Override
        public void keyReleased(KeyEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell)e.getSource();

            //Checks if user inputs an character or presses back space to delete
            if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                sourceCell.status = CellStatus.TO_GUESS;
                sourceCell.paint();
            } else {
                // Retrieve the int entered
                int numberIn = Integer.parseInt(sourceCell.getText());
                // For debugging
                System.out.println("You entered " + numberIn + " at (" + (sourceCell.row+1) + "," + (sourceCell.col+1) + ")");
        
                // DONE
                if (numberIn == sourceCell.number) { //Check the numberIn against sourceCell.number.
                    sourceCell.status = CellStatus.CORRECT_GUESS;
                } else {                            //Update the cell status sourceCell.status
                    sourceCell.status = CellStatus.WRONG_GUESS;
                }
                sourceCell.paint();   // re-paint this cell based on its status
    
                /* DONE
                * Check if the player has solved the puzzle after this move,
                *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
                */
                if (isSolved()) {
                    JOptionPane.showMessageDialog(null, "Congratulations, you won!");
                }
            }
        }
    }
}