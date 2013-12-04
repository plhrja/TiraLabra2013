package cell.neighbourTools;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class NeighbourToolsTest {

    private CellGrid grid;
    private MyArrayList<Cell> neighbours;
    
    @Before
    public void setup(){
        grid = new CellGrid(10, 10);
        
        grid.getCell(0, 0).InMaze(true);
        grid.getCell(0, 0).isSolid(false);
        grid.getCell(0, 2).InMaze(true);
        grid.getCell(0, 2).isSolid(false);
        grid.getCell(2, 0).InMaze(true);
        grid.getCell(2, 0).isSolid(false);
        grid.getCell(2, 2).InMaze(true);
        grid.getCell(2, 2).isSolid(false);
        
        neighbours = new MyArrayList<>();
    }
    
    @Test
    public void addInMazeNeighboursToCoordinatesAddsCorrectNeighboursAndAssignsCorrectRelativeDirections(){
        NeighbourTools.addInMazeNeighboursToCoordinates(2, 2, neighbours, grid, 2);
        
        
        assertArrayEquals(new Cell[]{grid.getCell(4, 2), grid.getCell(2, 4)}, neighbours.toArray());
        
        Character north = grid.getCell(4, 2).getHasInMazeNeighboursIn().get(0);
        Character west = grid.getCell(2, 4).getHasInMazeNeighboursIn().get(0);
        
        assertEquals('N', north, 1e-12);
        assertEquals('W', west, 1e-12);
    }
    
    @Test
    public void addInMazeNeighboursToCoordinatesCanHandleBoundaries(){
     NeighbourTools.addInMazeNeighboursToCoordinates(0, 0, neighbours, grid, 1);
     
     assertArrayEquals(new Cell[]{grid.getCell(1, 0), grid.getCell(0, 1)}, neighbours.toArray());
    }
    
    @Test
    public void connectNeighbourToMazeConnectsProperly(){
        
        NeighbourTools.addInMazeNeighboursToCoordinates(2, 2, neighbours, grid, 2);
        NeighbourTools.connectNeighbourToMaze(grid.getCell(4, 2), grid);
        
        assertEquals(false, grid.getCell(3, 2).isSolid());
    }
    
    @Test
    public void connectMethodOpensCellBetweenTwoCellsWithAppropriateDistances(){
        NeighbourTools.connect(grid.getCell(0, 0), grid.getCell(0, 2), grid);
        
        assertEquals(false, grid.getCell(0, 1).isSolid());
    }
    
    @Test
    public void getOpenNeighboursToCoordinatesAndSetDistAndParentWorks(){
        grid.getCell(3, 2).setCostToBeginning(5);
        MyArrayList neighbours = NeighbourTools.getOpenNeighboursToCoordinatesAndSetDistAndParent(grid, grid.getCell(3, 2));
        Cell neighbour = (Cell) neighbours.get(0);
        
        assertEquals(grid.getCell(2, 2), neighbour);
        assertEquals(6, neighbour.getCostToBeginning(), 1e-12);
    }
    
}