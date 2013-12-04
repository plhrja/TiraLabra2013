package cell.neighbourTools;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NeighbourToolsTest {

    private CellGrid grid;
    
    @Before
    public void setup(){
        grid = new CellGrid(10, 10);
        grid.getCell(1, 1).InMaze(true);
        grid.getCell(2, 0).InMaze(true);
    }
    
    @Test
    public void addInMazeNeighboursToCoordinatesAddsCorrectNeighboursAndAssignsCorrectRelativeDirections(){
        MyArrayList<Cell> neighbours = new MyArrayList<>();
        NeighbourTools.addInMazeNeighboursToCoordinates(2, 1, neighbours, grid, 1);
        
        
        assertArrayEquals(new Cell[]{grid.getCell(3, 1), grid.getCell(2, 2)}, neighbours.toArray());
        
        Character north = grid.getCell(3, 1).getHasInMazeNeighboursIn().get(0);
        Character west = grid.getCell(2, 2).getHasInMazeNeighboursIn().get(0);
        
        assertEquals('N', north, 1e-12);
        assertEquals('W', west, 1e-12);
    }
}