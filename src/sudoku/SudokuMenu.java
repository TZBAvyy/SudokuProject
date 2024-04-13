package sudoku;

import javax.swing.*;
import java.awt.event.*;

public class SudokuMenu extends JMenuBar{
    JMenu fileMenu, optionMenu, helpMenu;
    JMenuItem newGameItem, resetGameItem, exitItem, difficultyItem, hintItem, mainMenuItem;

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

        mainMenuItem = new JMenuItem("Back to Main Menu");
        mainMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProgram.cardLayout.show(mainProgram.getContentPane(),"Menu");
                mainProgram.menu.setVisible(false);
            }
        });

        //Add MenuItems into optionMenu
        optionMenu.add(mainMenuItem);
        optionMenu.add(difficultyItem);
        

        //Init helpMenu object
        helpMenu = new JMenu("Help");

        //Initialise hintItem object
        hintItem = new JMenuItem("Hint");

        //Adds hint functionality to hintItem object
        hintItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainProgram.board.isSolved()) {
                    mainProgram.board.reveal(1);
                }
            }
        });

        //Adds hintItem object to helpMenu object
        helpMenu.add(hintItem);
        

        //Add all Menu objects into MenuBar objecgt
        this.add(fileMenu);
        this.add(optionMenu);
        this.add(helpMenu);
    }
}
