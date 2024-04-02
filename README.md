# IM1003 Object-Oriented Programming 
## Mini-Project 1: Sudoku

Base Structure of Code written by Professor Chua Hock Chuan

Code Written by Avisena Gibraltar: {

    The entry main() entry method 
    Listener Inner Class for all the editable Cells
    Allocated a common listener as the ActionEvent listener for all the Cells (JTextFields)
    Added this common listener to all editable cells
    Added borders to all cells to better distinguish sudoku squares
    Added button to the south to re-start the game via board.newGame()
    Added difficulty through SudokuDifficulty enum
    Added button to the south to change difficulty of game via DifficultyPaneButton class
    DifficultyPaneButton class implements a JOptionPane to change difficulty
    Added label to show current difficulty level
    Remove hardcoded puzzle, generate puzzles from scratch
    Added code to generate which squares to be given
    Added solver using backtracking algorithm to ensure puzzle has only 1 unique solution [TODO]

}

