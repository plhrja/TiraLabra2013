package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * A subclass of the {@code JPopupMenu} class intended to be used as an aid in defining
 * the starting and finishing points of a path with the {@code JCellMouseListener} within 
 * the pathfinding visualization GUI. Therefore this popup menu will contain only two items:
 * "Set cell as start" and "Set cell as finish".
 * This is an adapter implementation in the sense that
 * this class only defines the layout of the popup menu; the functionalities
 * are to be implemented by an extension of this class.
 * @author rsirvio
 * @see JCellMouseListener
 * @see JPopupMenu
 */
public class PopupAdapter extends JPopupMenu {

    private static final String START_MESSAGE = "Mark as startingpoint";
    private static final String FINISH_MESSAGE = "Mark as finishingpoint";
    private JCell cell;
    private JCellGrid grid;
    private JMenuItem setStart;
    private JMenuItem setFinish;

    /**
     * Constructor constructs a new {@code PopupAdapter} object to be used in 
     * manipulation of the {@code JCell} defined in the parameter.
     * @param cell the {@code JCell} object that this {@code PopupAdapter} object is manipulating.
     * @param grid the {@code JCellGrid} object in which the {@code JCell} object is located.
     */
    public PopupAdapter(JCell cell, JCellGrid grid) {
        super();
        this.cell = cell;
        this.grid = grid;
        setStart = new JMenuItem(START_MESSAGE);
        setFinish = new JMenuItem(FINISH_MESSAGE);

        setStart.addActionListener(setStartFunction());
        setFinish.addActionListener(setFinishFunction());

        this.add(setStart);
        this.add(setFinish);
    }

    /**
     * Returns the {@code JCell} object this {@code PopupAdapter} object is manipulating.
     * @return the {@code Jcell} object this {@code PopupAdapter} object is manipulating.
     */
    public JCell getCell() {
        return cell;
    }

    /**
     * Returns the {@code JCellGrid} object in which the predefined manipulatable {@code JCell}
     * object resides.
     * @return the {@code JCellGrid} object in which the predefined manipulatable {@code JCell} object resides.
     */
    public JCellGrid getGrid() {
        return grid;
    }
    
    /**
     * Defines the functionality of the "Set cell as start" menu item.
     * To be implemented by an extension of {@code PopupAdapter} class.
     * @return an {@code ActionListener} to be added to the above mentioned menu item.
     */
    public ActionListener setStartFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    /**
     * Defines the functionality of the "Set cell as finish" menu item.
     * To be implemented by an extension of {@code PopupAdapter} class.
     * @return an {@code ActionListener} to be added to the above mentioned menu item.
     */
    public ActionListener setFinishFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
            
        };
    }
}
