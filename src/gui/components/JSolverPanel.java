package gui.components;

import gui.MPVGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JSolverPanel extends SolverPanelAdapter{

    private MPVGUI gui;

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
