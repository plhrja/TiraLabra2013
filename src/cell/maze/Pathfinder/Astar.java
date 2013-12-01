package cell.maze.Pathfinder;

import MyArrayList.MyArrayList;
import MyPriorityQueue.MyPriorityQueue;
import cell.Cell;
import cell.grid.CellGrid;
import cell.grid.CellGridPrinter;
import cell.maze.CellMazeGenerator;
import cell.maze.PrimCellMazeGenerator;
import cell.neighbourTools.NeighbourTools;
import heuristics.Heuristic;
import heuristics.ManhattanHeuristic;
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
        queue.addAll(NeighbourTools.getOpenNeighboursToCoordinates(grid, start));

        while (!queue.isEmpty() && head != finish) {
            head = queue.poll();
            if (head != finish) {
                queue.addAll(NeighbourTools.getOpenNeighboursToCoordinates(grid, head));
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

    //test-main
    public static void main(String[] args) {
        Astar astar = new Astar(new ManhattanHeuristic());
        CellMazeGenerator gen = new PrimCellMazeGenerator();
        CellGrid maze = gen.generateSmallMaze();
        CellGridPrinter.printGridWithPath(maze, astar.findPath(maze.getCell(1, 1),
                maze.getCell(maze.getRowLength() - 2, maze.getColumnLength() - 2), maze, 0));
    }
}
