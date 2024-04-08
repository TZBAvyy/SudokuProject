package sudoku;

/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    int[][] numbers = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    // The clues - isGiven (no need to guess) or need to guess
    boolean[][] isGiven;

    // Constructor
    public Puzzle() {
        super();
    }

    // Generate a new puzzle given the number of cells to be guessed, which can be used
    // to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void newPuzzle(int cellsToGuess) {
        //Initialise isGiven in newPuzzle to reset puzzle every time newPuzzle is run
        isGiven = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        
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
        
        //Generates the coordinates to be given
        int[][] givenCoor = generateClues(81-cellsToGuess);

        //Set isGiven array to match the coordinates in given coordinates (givenCoor)
        //[TODO EX3] Make a backtracking solver for sudoku
        //[TODO EX4] Use solver to ensure only 1 solution for puzzle 
        for (int[] coordinate : givenCoor) {
            isGiven[coordinate[0]][coordinate[1]] = true;
        }
        

    }

    //Sudoku shuffler
    //Shuffles rows and cols
    //Result is a nearly new random sudoku puzzle
    public int[][] shuffle(int[][] sudoku) {
        //Shuffles original rows first
        int[][] result = shuffleRows(sudoku);

        //Flips the puzzle rows to be its cols and its cols to be rows
        int[][] flippedResult = flip(result);

        //Shuffles the new rows (originally cols)
        result = shuffleRows(flippedResult);

        return result;
    }

    //Sudoku Puzzle Flipper (Flips rows to cols)
    public int[][] flip(int[][] sudoku) {
        int[][] flippedResult = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

        for (int row=0 ; row < SudokuConstants.GRID_SIZE ; row++) {
            for (int col=0 ; col < SudokuConstants.GRID_SIZE ; col++) {
                flippedResult[row][col] = sudoku[col][row];
            }
        }
        
        return flippedResult;
    }

    //Sudoku row shuffler 
    //Swaps rows among 1-2-3, 4-5-6, 7-8-9
    public int[][] shuffleRows(int[][] sudoku) {
        int[][] result = new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        int randomPos;
        int[] row;

        for (int boxIndex=0 ; boxIndex < SudokuConstants.GRID_SIZE ; boxIndex += SudokuConstants.SUBGRID_SIZE) {
            for (int boxRow=0 ; boxRow < SudokuConstants.SUBGRID_SIZE ; boxRow++ ) {
                //Gets the row of the input sudoku puzzle
                //boxIndex is the first index of each box in the puzzle
                //boxRow is the row of each box
                row = sudoku[boxRow+boxIndex]; 

                //(int)(Math.random()*3) gives random number between [0,2] inclusive
                randomPos = (int)(Math.random()*3)+boxIndex;

                if (boxRow==0) {
                    //IF index in range is 0 (i.e. for range (3-5)), put row in any random position
                    result[randomPos] = row;
                } else {
                    //IF index is range > 0, ensure row is empty before inserting
                    while (result[randomPos][0] != 0) { //int in 1st col is 0 if empty
                        //Rerolls the randomPos until it finds an empty
                        randomPos = (int)(Math.random()*3)+boxIndex;
                    }
                    //Inserts row in random position that is empty
                    result[randomPos] = row;
                }
            }
        }
        return result;
    }

    public int[][] generateClues(int numberOfClues) {
        int[][] coordinates = new int[SudokuConstants.GRID_SIZE*SudokuConstants.GRID_SIZE][2];
        int[][] clues = new int[numberOfClues][2];
        int randomPos;

        //Populate coordinates array
        for (int row=0 ; row < SudokuConstants.GRID_SIZE*SudokuConstants.GRID_SIZE ; row += 9) {
            for (int col=0 ; col < SudokuConstants.GRID_SIZE ; col++) {
                coordinates[row+col][0] = row/9;
                coordinates[row+col][1] = col;
            }
        }

        for (int index=0 ; index < numberOfClues ; index++) {
            randomPos = (int)(Math.random()*(SudokuConstants.GRID_SIZE*SudokuConstants.GRID_SIZE));

            //Checks if current random coordinates has been used
            //Rerolls randomPos if coordinates has been used (aka has been inserted into clues)
            while (coordinates[randomPos][0]==-1) {
                randomPos = (int)(Math.random()*(SudokuConstants.GRID_SIZE*SudokuConstants.GRID_SIZE));
            }

            clues[index][0] = coordinates[randomPos][0];
            clues[index][1] = coordinates[randomPos][1];
            coordinates[randomPos][0] = -1; //INDICATE THAT COOR HAS BEEN USED
        }

        return clues;
    }

    //(For advanced students) use singleton design pattern for this class
}