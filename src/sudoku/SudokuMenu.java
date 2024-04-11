package sudoku;

import javax.swing.*;
import java.awt.event.*;

public class SudokuMenu extends JMenuBar{
    JMenu fileMenu, optionMenu, helpMenu;
    JMenuItem newGameItem, resetGameItem, exitItem, difficultyItem;

    public SudokuMenu(SudokuMain mainProgram) {
        super();

        //Init fileMenu object and add MenuItems objects into File Menu oject
        fileMenu = new JMenu("File");

        //Initialise Menu Items for fileMenu
        newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProgram.board.newGame(mainProgram.difficulty);
            };
        });

        resetGameItem = new JMenuItem("Reset Game");
        resetGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProgram.board.resetGame();
            };
        });

        exitItem = new JMenuItem("Exit Game");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            };
        });

        //Add MenuItems into fileMenu
        fileMenu.add(newGameItem);
        fileMenu.add(resetGameItem);
        fileMenu.add(exitItem);

        //Init optionMenu object
        optionMenu = new JMenu("Options");

        //Initialise Menu objects for optionMenu
        difficultyItem = new JMenuItem("Change Difficulty");
        //Uses the custom DifficultySelectListener to create a JOptionPane menu to change settings
        difficultyItem.addActionListener(new DifficultySelectListener(mainProgram));

        //Add MenuItems into optionMenu
        optionMenu.add(difficultyItem);

        //Init helpMenu object
        helpMenu = new JMenu("Help");
        //[TODO] CREATE HINT SYSTEM HERE UNDER HELP MENU
        

        //Add all Menu objects into MenuBar objecgt
        this.add(fileMenu);
        this.add(optionMenu);
        this.add(helpMenu);
    }
}
