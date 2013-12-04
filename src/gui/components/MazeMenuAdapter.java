package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A subclass of the {@code JMenuBar} class intended to be used as a menubar used for selecting 
 * maze generation algorithms and maze sizes within the pathfinding visualization GUI. This is an adapter
 * implementation in the sense that this class only defines the layout of the menubar; the functionalities
 * are to be implemented by an extension of this class.
 * @author Easysimulation
 * @see JMenuBar
 */
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

    /**
     * Constructor constructs a new menubar (essentially a {@code JMenuBar} object).
     */
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

    /**
     * Returns the different size options of the mazes (in text form).
     * @return different maze size options.
     */
    public static String[] getGridSizes() {
        return SIZE_OPTIONS;
    }

    /**
     * Defines the functionality of the option "Prim's algorithm for maze generation".
     * To be implemented in an extension of {@code MazeMenuAdapter} class.
     * @param size the size of the maze to be generated.
     * @return An {@code ActionListener} to be given to the Prim's algorithm menu item.
     */
    public ActionListener generatePrimJCellGrid(String size) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    /**
     * Defines the functionality of the option "DFS algorithm for maze generation".
     * To be implemented in an extension of {@code MazeMenuAdapter} class.
     * @param size the size of the maze to be generated.
     * @return An {@code ActionListener} to be given to the DFS algorithm menu item.
     */
    public ActionListener generateDFSJCellGrid(String size) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    /**
     * Defines the functionality of the "exit" menu item. 
     * To be implemented in an extension of {@code MazeMenuAdapter} class.
     * @return An {@code ActionListener} to be given to the "exit" menu item.
     */
    public ActionListener exitFunction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    
    
}
