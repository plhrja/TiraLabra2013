package heuristics;

import cell.Cell;

/**
 * A heuristic implementation using Euclidean metric to the power of 2.
 * @author Easysimulation
 */
public class SemiEuclideanHeuristic implements Heuristic{

    @Override
    public double calculateDistance(Cell cell1, Cell cell2) {
        return Math.pow((cell1.getRow()- cell2.getRow()), 2) + 
               Math.pow((cell1.getColumn() - cell2.getColumn()), 2);
    }

}
