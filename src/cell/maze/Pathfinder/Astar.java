package cell.maze.Pathfinder;

import MyArrayList.MyArrayList;
import MyPriorityQueue.MyPriorityQueue;
import cell.Cell;
import cell.grid.CellGrid;
import cell.neighbourTools.NeighbourTools;
import heuristics.Heuristic;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Astar extends Pathfinder {

    private Heuristic heuristic;

    public Astar(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public MyArrayList findPath(Cell start, Cell finish, CellGrid grid, int delay) {
        MyArrayList<Cell> pathToFinish = new MyArrayList<>();
        MyPriorityQueue<Cell> queue = new MyPriorityQueue();
        Cell head = null;

        initCells(start, finish, grid);
        queue.addAll(NeighbourTools.getOpenNeighboursToCoordinatesAndSetDistAndParent(grid, start));

        while (!queue.isEmpty() && head != finish) {
            head = queue.poll();
            if (head != finish) {
                queue.addAll(NeighbourTools.getOpenNeighboursToCoordinatesAndSetDistAndParent(grid, head));
                if (delay != 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Astar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setChanged();
                notifyObservers(head);
            }
        }

        if (head != null && head == finish) {
            pathToFinish = iteratePath(head, start);
        }

        return pathToFinish;
    }

    private void initCells(Cell start, Cell finish, CellGrid grid) {
        for (Cell cell : grid) {
            if (!cell.isSolid()) {
                cell.setCostToFinish(this.heuristic.calculateDistance(cell, finish));
                cell.setCostToBeginning(Integer.MAX_VALUE);
                cell.setParentCell(null);
            }
            start.setCostToBeginning(0);
        }
    }

    private MyArrayList<Cell> iteratePath(Cell head, Cell start) {
        MyArrayList<Cell> pathToFinish = new MyArrayList<>();
        head = head.getParentCell();
        while (head != start) {
            pathToFinish.add(head);
            head = head.getParentCell();
        }
        return pathToFinish;
    }
}
