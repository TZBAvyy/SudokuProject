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
    // Listener class
    CellInputListener listener = new CellInputListener();

    //For referencing SudokuMain instance
    SudokuMain mainProgram;

    /** Constructor */
    public GameBoardPanel(SudokuMain mainProgram) {
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));  // JPanel

        //Saves current game instance
        this.mainProgram = mainProgram;
        
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
        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
        * Generate a new puzzle; and reset the game board of cells based on the puzzle.
        * You can call this method to start a new game.
        */
    public void newGame(SudokuDifficulty difficulty) {
        int cellsToGuess = 0;
        // Generate a new puzzle based on difficulty
        if (difficulty==SudokuDifficulty.EASY) {
            //Easy difficulty gives 51 cells as clues
            cellsToGuess = 30;
        } else if (difficulty==SudokuDifficulty.NORMAL) {
            //Normal difficulty gives 41 cells as clues
            cellsToGuess = 40;
        } else if (difficulty==SudokuDifficulty.HARD) {
            //Hard difficulty gives 31 cells as clues
            cellsToGuess = 50;
        } else if (difficulty==SudokuDifficulty.CHALLENGE) {
            //Challenge difficulty gives 26 cells as clues
            cellsToGuess = 55;
        }
        puzzle.newPuzzle(cellsToGuess);
        mainProgram.statusLabel.setText("Cells remaining: " + cellsToGuess);
        
        // Initialize all the 9x9 cells, based on the puzzle.
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                cells[row][col].removeKeyListener(listener);
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
                if (cells[row][col].isEditable()) {
                    cells[row][col].addKeyListener(listener);   // For all editable rows and cols
                }
            }
        }

        // DONE Allocate a common listener as the ActionEvent listener for all the
        //  Cells (JTextFields)
    }

    public void resetGame() {
        SudokuDifficulty difficulty = mainProgram.difficulty;
        int cellsToGuess = 0;

        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (!puzzle.isGiven[row][col]) {
                    cells[row][col].status = CellStatus.TO_GUESS;
                    cells[row][col].paint();
                }
            }
        }
        if (difficulty==SudokuDifficulty.EASY) {
            //Easy difficulty gives 51 cells as clues
            cellsToGuess = 30;
        } else if (difficulty==SudokuDifficulty.NORMAL) {
            //Normal difficulty gives 41 cells as clues
            cellsToGuess = 40;
        } else if (difficulty==SudokuDifficulty.HARD) {
            //Hard difficulty gives 31 cells as clues
            cellsToGuess = 50;
        } else if (difficulty==SudokuDifficulty.CHALLENGE) {
            //Challenge difficulty gives 26 cells as clues
            cellsToGuess = 55;
        }
        mainProgram.statusLabel.setText("Cells remaining: " + cellsToGuess);
    }

    /**
        * Return true if the puzzle is solved
        * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
        */
    public boolean isSolved() {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {

                //[CHANGED FROM THIS TO ACCOMODATE DYNAMICALLY UPDATING VALIDITY]
                /* if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
                    return false;
                } */

                if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].conflict) {
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
                //IF BACKSPACE
                //Remove everything in cell by setting status to TO_GUESS & conflict to false
                sourceCell.status = CellStatus.TO_GUESS;
                sourceCell.conflict = false;
                sourceCell.paint();

                //Check row, col and 3x3 for cells with conflict == true
                //Then updateConflict() those cells
                //IF only conflict was previously erased cell => it will remove conflict
                //IF conflict still exists after cell was erased => keep conflict
                for (int row=0 ; row < SudokuConstants.GRID_SIZE ; ++row) {
                    if (cells[row][sourceCell.col].conflict) {
                        updateConflict(cells[row][sourceCell.col]);
                    }
                }
                for (int col=0 ; col < SudokuConstants.GRID_SIZE ; ++col) {
                    if (cells[sourceCell.row][col].conflict) {
                        updateConflict(cells[sourceCell.row][col]);
                    }
                } //Same algorithm concept as in updateConflict()
                for (int row=3*(sourceCell.row/3) ; row < 3*(sourceCell.row/3)+SudokuConstants.SUBGRID_SIZE ; ++row) {
                    for (int col=3*(sourceCell.col/3) ; col < 3*(sourceCell.col/3)+SudokuConstants.SUBGRID_SIZE ; ++col) {
                        if (cells[row][col].conflict) {
                            updateConflict(cells[row][col]);
                        }
                    }
                }
                updateStatus();

            //[TODO] CHECK FOR INPUT LENGTH > 1 AND DENY IT
            //IF USER ENTERS 1-9
            } else if (e.getKeyCode()>=KeyEvent.VK_1 && e.getKeyCode()<=KeyEvent.VK_9) {
                /* [CHANGED TO NEW DYNAMIC GUESSING]
                if (numberIn == sourceCell.number) { //Check the numberIn against sourceCell.number.
                    sourceCell.status = CellStatus.CORRECT_GUESS;
                } else {                            //Update the cell status sourceCell.status
                    sourceCell.status = CellStatus.WRONG_GUESS;
                }
                */
                
                sourceCell.status = CellStatus.GUESSED;
                updateConflict(sourceCell);
                updateStatus();
               
                //Checks if user has solved puzzle after move, if so => Congratulation JOptionPane
                if (isSolved()) {
                    JOptionPane.showMessageDialog(null, "Congratulations, you won!");
                    //Disables input after winning
                    for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
                        for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                            cells[row][col].setEditable(false);
                            cells[row][col].removeKeyListener(listener);
                        }
                    }
                }
            } else { //[TODO] IF USER TYPES INVALID INPUT, MAKE PREVENTIONS
                System.out.println("Invalid Input Detected");
            }
        }
    }

    //Checks and updates any conflict from the cell of specified row and col after input
    public void updateConflict(Cell sourceCell) {
        int sourceRow = sourceCell.row;
        int sourceCol = sourceCell.col;
        int sourceInput = Integer.parseInt(sourceCell.getText());
        int cellNumber;
        boolean conflictExists = false;

        //Check for same number in row
        for (int row=0 ; row < SudokuConstants.GRID_SIZE ; ++row) {
            //If cell has yet to have any input, SKIP
            if (!cells[row][sourceCol].getText().isEmpty()) {

                //Checks the number on the cell with the sourceCell number
                cellNumber = Integer.parseInt(cells[row][sourceCol].getText());

                if (cellNumber == sourceInput && row!=sourceRow) { //Skips cell if it is sourceCell
                    //Any cell that isnt sourceCell but has same number
                    //set its conflict = true and paint()
                    cells[row][sourceCol].conflict = true;
                    cells[row][sourceCol].paint();
                    conflictExists = true;
                }
            }
        }

        //Check for same number in col
        for (int col=0 ; col < SudokuConstants.GRID_SIZE ; ++col) {
            //If cell has yet to have any input, SKIP
            if (!cells[sourceRow][col].getText().isEmpty()) {

                //Checks the number on the cell with the sourceCell number
                cellNumber = Integer.parseInt(cells[sourceRow][col].getText());

                if (cellNumber == sourceInput && col!=sourceCol) { //Skips cell if it is sourceCell
                    //Any cell that isnt sourceCell but has same number
                    //set its conflict = true and paint()
                    cells[sourceRow][col].conflict = true;
                    cells[sourceRow][col].paint();
                    conflictExists = true;
                }
            }
        }

        //Check for same number in 3x3
        // Where the first number is the row in range and the second number is the col in range
        // (0-2,0-2) | (0-2,3-5) | (0-2,6-8)
        // (3-5,0-2) | (3-5,3-5) | (3-5,6-8)
        // (6-8,0-2) | (6-8,3-5) | (6-8,6-8)
        // e.g. Middle 3x3 starts at row 3, col 3 and ends with row 5, col 5
        for (int row=3*(sourceRow/3) ; row < 3*(sourceRow/3)+SudokuConstants.SUBGRID_SIZE ; ++row) {
            for (int col=3*(sourceCol/3) ; col < 3*(sourceCol/3)+SudokuConstants.SUBGRID_SIZE ; ++col) {
                //If cell has yet to have any input, SKIP
                if (!cells[row][col].getText().isEmpty()) {

                    //Checks the number on the cell with the sourceCell number
                    cellNumber = Integer.parseInt(cells[row][col].getText());

                    //Skips cell if it its row or col is same as sourceCell, since it has been check previously
                    if (cellNumber == sourceInput && col!=sourceCol && row!=sourceRow) { 
                        //Any cell that isnt sourceCell but has same number
                        //set its conflict = true and paint()
                        cells[row][col].conflict = true;
                        cells[row][col].paint();
                        conflictExists = true;
                    }
                }
            }
        }

        //Update sourceCell last, if exists atleast 1 conflict => conflictExists = true, else false
        cells[sourceRow][sourceCol].conflict = conflictExists;
        cells[sourceRow][sourceCol].paint();
    }

    public void updateStatus() {
        int cellsLeft = 0;
        for (int row=0 ; row < SudokuConstants.GRID_SIZE ; ++row) {
            for (int col=0 ; col < SudokuConstants.GRID_SIZE ; ++col) {
                if (cells[row][col].status==CellStatus.TO_GUESS) {
                    cellsLeft += 1;
                }
            }
        }
        mainProgram.statusLabel.setText("Cells remaining: " + cellsLeft);
    }

}