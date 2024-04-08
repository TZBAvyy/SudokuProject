package sudoku;

import javax.swing.*;
import java.awt.event.*;;

public class SudokuMenu extends JMenuBar{
    JMenu fileMenu, optionMenu, helpMenu;
    JMenuItem newGameItem, resetGameItem, exitItem;

    public SudokuMenu(SudokuMain mainProgram) {
        super();

        //Initialise Menu Items
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

            };
        });

        exitItem = new JMenuItem("Exit Game");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            };
        });

        //Init fileManu object and add MenuItems objects into File Menu oject
        fileMenu = new JMenu("File");
        fileMenu.add(newGameItem);
        fileMenu.add(resetGameItem);
        fileMenu.add(exitItem);

        optionMenu = new JMenu("Options");
        helpMenu = new JMenu("Help");

        //Add all Menu objects into MenuBar objecgt
        this.add(fileMenu);
        this.add(optionMenu);
        this.add(helpMenu);
    }
}
