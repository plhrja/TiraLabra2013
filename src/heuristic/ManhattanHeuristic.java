package heuristic;

import cell.Cell;


public class ManhattanHeuristic implements Heuristic{

    @Override
    public double calculateDistance(Cell cell1, Cell cell2) {
        return Math.abs(cell1.getRow()-cell2.getRow()) + Math.abs(cell1.getColumn()-cell2.getColumn());
    }

}
