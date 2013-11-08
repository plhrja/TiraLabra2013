package heuristic;

import cell.Cell;


public class EuclideanHeuristic implements Heuristic{

    @Override
    public double calculateDistance(Cell cell1, Cell cell2) {
        return (Math.sqrt(Math.pow((cell1.getRow()- cell2.getRow()), 2) + 
                Math.pow((cell1.getColumn() - cell2.getColumn()), 2)));
    }

}
