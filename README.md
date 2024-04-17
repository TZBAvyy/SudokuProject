# IM1003 Object-Oriented Programming 
## Mini-Project 1: Sudoku

Base Structure of Code written by Professor Chua Hock Chuan
from https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_Sudoku.html

Code Written by Avisena Gibraltar: {

    The entry main() entry method 
    Listener Inner Class for all the editable Cells
    Allocated a common listener as the ActionEvent listener for all the Cells (JTextFields)
    Added this common listener to all editable cells
    Added borders to all cells to better distinguish sudoku squares
    Added button to the south to re-start the game via board.newGame() [CHANGED TO MENU BAR BUTTON IN FILE TAB]
    Added difficulty through SudokuDifficulty enum
    Added button to the south to change difficulty of game
    Implemented DifficultySelectListener to create JOptionPane to change difficulty
    Added label to show current difficulty level
    Remove hardcoded puzzle, generate puzzles from scratch
    Added code to generate which squares to be given
    Created a "menu bar" for options such as "File" ("New Game", "Reset Game", "Exit"), "Options", and "Help" (Use JMenuBar, JMenu, and JMenuItem classes)
    Added status label at bottom of game to see remaining cells left
    Accept any "valid" number at the time of input (no duplicate in row, column and sub-grid), but signal a conflict whenever it is detected. Highlight the conflicting cells. 
    Added Hint system into Help Menu to help player incase they get stuck
    Added Main Menu Screen on start, with option to go back to main menu in Options Tab

}

## Total Lines Written
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [README.md](/README.md) | Markdown | 38 | 0 | 6 | 44 |
| [src/sudoku/Cell.java](/src/sudoku/Cell.java) | Java | 63 | 21 | 5 | 89 |
| [src/sudoku/CellStatus.java](/src/sudoku/CellStatus.java) | Java | 8 | 6 | 0 | 14 |
| [src/sudoku/DifficultySelectListener.java](/src/sudoku/DifficultySelectListener.java) | Java | 57 | 19 | 19 | 95 |
| [src/sudoku/GameBoardPanel.java](/src/sudoku/GameBoardPanel.java) | Java | 235 | 87 | 47 | 369 |
| [src/sudoku/MainMenuPanel.java](/src/sudoku/MainMenuPanel.java) | Java | 62 | 9 | 20 | 91 |
| [src/sudoku/Puzzle.java](/src/sudoku/Puzzle.java) | Java | 85 | 35 | 27 | 147 |
| [src/sudoku/SudokuConstants.java](/src/sudoku/SudokuConstants.java) | Java | 5 | 5 | 0 | 10 |
| [src/sudoku/SudokuDifficulty.java](/src/sudoku/SudokuDifficulty.java) | Java | 4 | 0 | 2 | 6 |
| [src/sudoku/SudokuMain.java](/src/sudoku/SudokuMain.java) | Java | 45 | 31 | 18 | 94 |
| [src/sudoku/SudokuMenu.java](/src/sudoku/SudokuMenu.java) | Java | 62 | 12 | 21 | 95 |
| Total Java | Java | 10 | 626 | 225 | 159 | 1,010 |
