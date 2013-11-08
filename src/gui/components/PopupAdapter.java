package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupAdapter extends JPopupMenu {

    private static final String START_MESSAGE = "Mark as startingpoint";
    private static final String FINISH_MESSAGE = "Mark as finishingpoint";
    private JCell cell;
    private JCellGrid grid;
    private JMenuItem setStart;
    private JMenuItem setFinish;

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

    public JCell getCell() {
        return cell;
    }

    public JCellGrid getGrid() {
        return grid;
    }
    
    //to be overwritten
    public ActionListener setStartFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    //to be overwritten
    public ActionListener setFinishFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
            
        };
    }
}
