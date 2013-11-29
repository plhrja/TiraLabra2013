package heuristics;

import cell.Cell;
import cell.maze.Pathfinder.Pathfinder;


/**
 * Heuristics are used for estimating distances between two {@code Cell} objects. This
 * is used especially in pathfinding.
 * @author Easysimulation
 * @see Pathfinder
 */
public interface Heuristic {

    /**
     * Returns an estimation between two {@code Cell} objects. Note that the result should
     * be commutative.
     * @param cell1 the first {@code Cell} object.
     * @param cell2 the other {@code Cell} object.
     * @return the estimation between the two specified {@code Cell} objects.
     */
    public double calculateDistance(Cell cell1, Cell cell2);
    
}
