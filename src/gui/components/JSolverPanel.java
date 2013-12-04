package gui.components;

import gui.MPVGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * An extension of the {@code SolverPanelAdapter} class where the functionalities of the
 * predefined wrapped items are implemented.
 * @author rsirvio
 * @see SolverPanelAdapter
 * @see MPVGUI
 */
public class JSolverPanel extends SolverPanelAdapter{

    private MPVGUI gui;

    /**
     * Constructor constructs a new panel with the layout defined by the {@code SolverPanelAdapter} class.
     * Attaches it to the {@code MPVGUI} object given in the parameter.
     * @param gui the {@code MPVGUI} object that this object is to be attached to.
     */
    public JSolverPanel(MPVGUI gui) {
        super();
        this.gui = gui;
    }
    
    @Override
    public ActionListener solveFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JMazeWorker(gui).execute();
            }
        };
    }
    
    @Override
    public ActionListener clearFunction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getjCellMaze().clean();
            }
        };
    }
    
}
