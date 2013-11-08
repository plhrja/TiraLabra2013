package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MazeMenuAdapter extends JMenuBar {

    private static final String[] SIZE_OPTIONS = {"Small", "Medium", "Large"};
    private static final String HEAD_MENU_TEXT = "Maze";
    private static final String PRIM_MENU_TEXT = "Generate with Prim's algorithm";
    private static final String DFS_MENU_TEXT = "Generate with DFS";
    private static final String EXIT_TEXT = "Exit";
    private JMenu headMenu;
    private JMenu primMenu;
    private JMenu DFSMenu;
    private JMenuItem exit;

    public MazeMenuAdapter() {
        headMenu = new JMenu(HEAD_MENU_TEXT);
        primMenu = new JMenu(PRIM_MENU_TEXT);
        DFSMenu = new JMenu(DFS_MENU_TEXT);
        exit = new JMenuItem(EXIT_TEXT);

        for (int i = 0; i < SIZE_OPTIONS.length; i++) {
            JMenuItem primItem = new JMenuItem(SIZE_OPTIONS[i]);
            JMenuItem DFSItem = new JMenuItem(SIZE_OPTIONS[i]);
            
            primItem.addActionListener(generatePrimJCellGrid(SIZE_OPTIONS[i]));
            DFSItem.addActionListener(generateDFSJCellGrid(SIZE_OPTIONS[i]));
             
            primMenu.add(primItem);
            DFSMenu.add(DFSItem);
        }
        
        exit.addActionListener(exitFunction());
        
        DFSMenu.setMnemonic(VK_N);
        headMenu.setMnemonic(VK_M);

        headMenu.add(primMenu);
        headMenu.add(DFSMenu);
        headMenu.addSeparator();
        headMenu.add(exit);

        this.add(headMenu);
    }

    public static String[] getGridSizes() {
        return SIZE_OPTIONS;
    }

    //to be overridden
    public ActionListener generatePrimJCellGrid(String size) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    //to be overridden
    public ActionListener generateDFSJCellGrid(String size) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    //to be overwritten
    public ActionListener exitFunction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    
    
}
