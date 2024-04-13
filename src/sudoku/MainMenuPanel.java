package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenuPanel extends JPanel{

    SudokuMain mainProgram; //To reference SudokuMain instance
    JLabel sudokuTitle = new JLabel("Sudoku");
    JButton newGameBtn, optionBtn, exitBtn, continueBtn;

    public MainMenuPanel(SudokuMain mainProgram) {
        super();
        this.mainProgram = mainProgram;

        //Sets the layout of panel to be vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(90));//Spacing

        //Formats the 'Sudoku' text
        sudokuTitle.setFont(new Font("OCR A Extended", Font.PLAIN, 100));
        sudokuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sudokuTitle);

        add(Box.createVerticalStrut(90));//Spacing

        //Creates the continue button but makes it invisible on start
        continueBtn = new JButton("Continue");
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueBtn.setMargin(new Insets(10, 100, 10, 100)); //Custom sizing on button
        continueBtn.setVisible(false);
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Switches to GameBoardPanel
                mainProgram.cardLayout.show(mainProgram.getContentPane(),"Game");
                mainProgram.menu.setVisible(true);
            }
        });
        add(continueBtn);

        add(Box.createVerticalStrut(30));//Spacing

        //Creates the new game button
        newGameBtn = new JButton("New Game");
        newGameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameBtn.setMargin(new Insets(10, 90, 10, 90)); //Custom sizing on button
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Switches to GameBoardPanel but also creates new game
                mainProgram.cardLayout.show(mainProgram.getContentPane(),"Game");
                mainProgram.board.newGame(mainProgram.difficulty);
                mainProgram.menu.setVisible(true);
                continueBtn.setVisible(true);
            }
        });
        add(newGameBtn);

        add(Box.createVerticalStrut(30));//Spacing

        //Creates the options button
        optionBtn = new JButton("Options");
        optionBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionBtn.setMargin(new Insets(10, 90, 10, 90)); //Custom sizing on button
        //Reuses DifficultySelectListener class
        optionBtn.addActionListener(new DifficultySelectListener(mainProgram));
        add(optionBtn);

        add(Box.createVerticalStrut(30));//Spacing

        //Creates the exit button
        exitBtn = new JButton("Exit");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setMargin(new Insets(10, 90, 10, 90)); //Custom sizing on button
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitBtn);



        
    }
}
