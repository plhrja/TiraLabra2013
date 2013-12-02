package cell.maze.Pathfinder;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.util.Observable;

/**
 * An interface to be implemented by different pathfinding classes, designed for finding
 * paths between two {@code Cell} objects in a {@code CellGrid} representation of a maze.
 * @author Easysimulation
 */
public abstract class Pathfinder extends Observable{

    /**
     * A method that that, when implemented, should find a path between two {@code Cell} objects
     * ({@code start} and {@code finish}) in a {@code CellGrid} representation of a maze. Note that the delay
     * parameter is intended to be used when animating/multithreading, might be refactored and separated from
     * this method in the future.
     * @param start the {@code Cell} object from which to path is searched.
     * @param finish the {@code Cell} object ti which the path is searched.
     * @param grid the {@code CellGrid} representation of the maze in which the path is searched.
     * @param delay the delay (in ms.) used as a parameter when sleeping the thread.
     *
     * @return a {@code MyArrayList} list containing the {@code Cell} objects that are in the path
     * 
     */
    public abstract MyArrayList findPath(Cell start, Cell finish, CellGrid grid, int delay);

}
