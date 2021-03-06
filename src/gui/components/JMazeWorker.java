package gui.components;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import cell.maze.Pathfinder.Astar;
import cell.maze.Pathfinder.Pathfinder;
import gui.MPVGUI;
import heuristics.DijkstraHeuristic;
import heuristics.EuclideanHeuristic;
import heuristics.Heuristic;
import heuristics.ManhattanHeuristic;
import heuristics.SemiEuclideanHeuristic;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * An extension of the {@code SwingWorker} class that handles the updating of the 
 * {@code MPVGUI} object when a pathsolving algorithm is running.
 * @author rsirvio
 * @see SwingWorker
 * @see Pathfinder
 * @see Observer
 * @see MPVGUI
 */
public class JMazeWorker extends SwingWorker<MyArrayList<Cell>, Void> implements Observer {

    private MPVGUI gui;
    private CellGrid maze;
    private Heuristic heuristic;
    private int delay;

    /**
     * Constructs a new {@code JMazeWorker} object that handles the updating of the {@code MPVGUI}
     * object defined in the parameter,
     * @param gui the {@code MPVGUI} object that is updated by this {@code JMazeWorker} object.
     */
    public JMazeWorker(MPVGUI gui) {
        this.gui = gui;
        String heuristicsString = this.gui.getSolverPanel().getHeuristicsValue();
        this.heuristic = (heuristicsString.equals("Manhattan"))
                ? new ManhattanHeuristic() : (heuristicsString.equals("Euclidean"))
                ? new EuclideanHeuristic() : (heuristicsString.equals("SemiEuc"))
                ? new SemiEuclideanHeuristic() : new DijkstraHeuristic();
        this.delay = this.gui.getSolverPanel().getDelayValue();
        this.maze = this.gui.getMaze();
    }

    @Override
    public MyArrayList doInBackground(){
        try {
            this.gui.setEnabled(false);
            Pathfinder pathfinder = new Astar(this.heuristic);
            pathfinder.addObserver(this);

            int startRow = this.gui.getjCellMaze().getStart().getRow();
            int startColumn = this.gui.getjCellMaze().getStart().getColumn();
            int finishRow = this.gui.getjCellMaze().getFinish().getRow();
            int finishColumn = this.gui.getjCellMaze().getFinish().getColumn();

            return pathfinder.findPath(this.maze.getCell(startRow, startColumn),
                    this.maze.getCell(finishRow, finishColumn),
                    maze, delay);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void done() {
        try {
            this.gui.setEnabled(true);
            MyArrayList<Cell> path = this.get();
            if (path != null) {
                this.gui.getjCellMaze().paintPath(path);
            }

        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(JMazeWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (delay != 0) {
            Cell c = (Cell) arg;
            if (this.gui.getjCellMaze().getStart().getRow() != c.getRow()
                    || this.gui.getjCellMaze().getStart().getColumn() != c.getColumn()) {
                this.gui.getjCellMaze().getJCell(c.getRow(), c.getColumn()).processed();
            }
        }
    }
}
