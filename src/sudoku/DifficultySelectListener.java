package sudoku;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class DifficultySelectListener implements ActionListener{

    SudokuMain mainProgram;

    public DifficultySelectListener(SudokuMain mainProgram) {
        super();
        this.mainProgram = mainProgram;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane title
        String title = "Difficulty Option Selector";
        //Array of JButtons containing all the difficulty changing buttons
        JButton[] btns = new JButton[SudokuDifficulty.values().length];
        //JPanel to contain all the JButtons from above
        JPanel btnPanel = new JPanel();
        //JPanel to layout components
        JPanel mainPanel = new JPanel();
        //JLabel to show difficulty change
        JLabel mainText = new JLabel("Select new difficulty");
        //Saves the current difficulty of the Sudoku puzzle
        SudokuDifficulty currentDifficulty = mainProgram.difficulty;

        //Sets frame layout
        mainPanel.setLayout(new BorderLayout());

        //Arrange frame to display
        mainPanel.add(mainText, BorderLayout.NORTH);

        mainPanel.add(btnPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("(WARNING: Clicking OK resets game to new difficulty)"), BorderLayout.SOUTH);

        //Sets panel layout
        btnPanel.setLayout(new FlowLayout());

        //Index to iterate across btns[] array
        int index = 0;
        //For loop to iterate across SudokuDifficulty enum
        for (SudokuDifficulty btnDifficulty : SudokuDifficulty.values()) {
            //Construct each button with its difficulty as its text
            btns[index] = new JButton(btnDifficulty.name());

            //Adds the action listener to change sudoku difficulty to the difficulty shown on button
            btns[index].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainProgram.difficulty = btnDifficulty;
                    if (currentDifficulty != btnDifficulty) {
                        mainText.setText(String.format("%s --> %s", currentDifficulty.name(), btnDifficulty.name()));
                    } else {
                        mainText.setText("NO CHANGE");
                    }
                    
                }
            });

            //Adds the btn to the panel
            btnPanel.add(btns[index]);

            //Increments index for next button
            index++;
        }

        //Creates the JOptionPane with the panel above
        int option = JOptionPane.showOptionDialog(
                    mainProgram.board, 
                    mainPanel, 
                    title, 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    null, 
                    null);

        //Checks whether user selects ok or cancel
        if (option==JOptionPane.CANCEL_OPTION) {
            //Resets difficulty to before
            mainProgram.difficulty = currentDifficulty;
        } else if (option==JOptionPane.OK_OPTION) {
            //Confirms the difficulty and resets the puzzle to new difficulty
            mainProgram.board.newGame(mainProgram.difficulty);
            mainProgram.difficultyLabel.setText("Current Difficulty: " + mainProgram.difficulty.name());
        }
    }
    
}
