package gui.components;

import gui.MPVGUI;
import heuristics.EuclideanHeuristic;
import heuristics.Heuristic;
import heuristics.ManhattanHeuristic;
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
        final String heuristicsString = this.getHeuristicsValue();
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setEnabled(false);
                Heuristic heuristic = (heuristicsString.equals("Man"))
                        ? new ManhattanHeuristic() : new EuclideanHeuristic();
                new JMazeWorker(gui, heuristic).execute();
                gui.setEnabled(true);
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
