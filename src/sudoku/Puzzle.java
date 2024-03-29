package sudoku;

import java.lang.reflect.Array;

/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super();
    }

    // Generate a new puzzle given the number of cells to be guessed, which can be used
    // to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle(int cellsToGuess) {
        int[][] template =
            {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        //Randomly shuffle the template solution
        //This generates an almost completely randomised new puzzle
        numbers = shuffle(template);         
        
        // [TODO EX2 Randomize which cells is given]
        boolean[][] hardcodedIsGiven =
            {{true, true, true, true, true, false, true, true, true},
            {true, true, true, true, true, true, true, true, false},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true}};

        // Copy from hardcodedIsGiven into array "isGiven"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                isGiven[row][col] = hardcodedIsGiven[row][col];
            }
        }
    }

    //Sudoku shuffler
    //Shuffles rows and cols
    //Result is a nearly new random sudoku puzzle
    public int[][] shuffle(int[][] sudoku) {
        //Shuffles original rows first
        int[][] result = shuffleRows(sudoku);
        //Flips the puzzle rows to be its cols and its cols to be rows
        int[][] flipped = flip(result);
        //Shuffles the new rows (originally cols)
        result = shuffleRows(flipped);
        return result;
    }

    //Sudoku row shuffler 
    //Swaps rows among 1-2-3, 4-5-6, 7-8-9
    public int[][] shuffleRows(int[][] sudoku) {
        int[][] result = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        int randomPos;
        int[] row;

        for (int range=0 ; range < SudokuConstants.GRID_SIZE ; range += SudokuConstants.GRID_SIZE/3) {
            for (int indexInRange=0 ; indexInRange < SudokuConstants.GRID_SIZE/3 ; indexInRange++ ) {
                //Gets the first row of the input sudoku puzzle
                row = sudoku[indexInRange+range]; 

                //(int)(Math.random()*3) gives random number between [0,2] inclusive
                randomPos = (int)(Math.random()*3)+range;

                if (indexInRange==0) {
                    //IF index in range is 0 (i.e. for range (3-5)), put row in any random position
                    result[randomPos] = row;
                } else {
                    //IF index is range > 0, ensure row is empty before inserting
                    while (result[randomPos][0] != 0) { //int in 1st col is 0 if empty
                        //Rerolls the randomPos until it finds an empty
                        randomPos = (int)(Math.random()*(range+3));
                    }
                    //Inserts row in random position that is empty
                    result[randomPos] = row;
                }
            }
        }

        return result;
    }

    //Flips sudoku puzzle 
    //Rows -> Col, Col -> Rows
    public int[][] flip(int[][] sudoku) {
        int[][] result = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

        for (int row=0 ; row < SudokuConstants.GRID_SIZE ; row++) {
            for (int col=0 ; col < SudokuConstants.GRID_SIZE ; col++) {
                result[row][col] = sudoku[col][row];
            }
        }

        return result;
    }





    //(For advanced students) use singleton design pattern for this class
}