package gui.components;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import cell.maze.Pathfinder.Astar;
import gui.MPVGUI;
import heuristics.Heuristic;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class JMazeWorker extends SwingWorker<MyArrayList<Cell>, Void> {

    private MPVGUI gui;
    private CellGrid maze;
    private Heuristic heuristic;

    public JMazeWorker(MPVGUI gui, Heuristic heuristic) {
        this.gui = gui;
        this.heuristic = heuristic;
        this.maze = this.gui.getMaze();
    }

    @Override
    public MyArrayList doInBackground() throws Exception {
        try {
            Astar pathfinder = new Astar(this.heuristic);

            int startRow = this.gui.getjCellMaze().getStart().getRow();
            int startColumn = this.gui.getjCellMaze().getStart().getColumn();
            int finishRow = this.gui.getjCellMaze().getFinish().getRow();
            int finishColumn = this.gui.getjCellMaze().getFinish().getColumn();

            return pathfinder.findPath(this.maze.getCell(startRow, startColumn),
                    this.maze.getCell(finishRow, finishColumn),
                    maze);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void done() {
        try {
            MyArrayList<Cell> path = this.get();
            if(path != null){
                this.gui.getjCellMaze().paintPath(path);
            }
            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(JMazeWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
