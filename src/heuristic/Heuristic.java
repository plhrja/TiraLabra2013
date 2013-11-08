package heuristic;

import cell.Cell;


public interface Heuristic {

    public double calculateDistance(Cell cell1, Cell cell2);
    
}
