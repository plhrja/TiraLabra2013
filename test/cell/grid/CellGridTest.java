package cell.grid;

import cell.Cell;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CellGridTest {

    private CellGrid grid;

    @Before
    public void setup() {
        grid = new CellGrid(10, 10);
    }

    @Test
    public void constructorCreatesRightSizedGrid() {
        int counter = 0;
        for (Object object : grid) {
            counter++;
        }
        
        assertEquals(100, counter);
    }

    @Test
    public void constructorSetsCorrectRowAndColumnValuesToTheCells() {
        boolean correctCoordinates = true;
        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                Cell currentCell = grid.getCell(i, j);
                correctCoordinates = (i == currentCell.getRow() && j == currentCell.getColumn())
                        ? true : false;
            }
            
            assertEquals(true, correctCoordinates);
        }
    }

    @Test
    public void constructorAssignsRightTypesForTheCells() {
        CellGrid gridWithOpenCells = new CellGrid(10, 10, false);
        boolean correctTypes = true;

        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                Cell currentClosedCell = grid.getCell(i, j);
                Cell currentOpenCell = gridWithOpenCells.getCell(i, j);
                correctTypes = (currentClosedCell.isSolid() && !currentOpenCell.isSolid())
                        ? true : false;
            }
            
            assertEquals(true, correctTypes);
        }
    }
    
    @Test
    public void getCellMethodReturnsCorrectCellOrNullIfIncorrectParametersAreGiven(){
        Cell cell = grid.getCell(0, 0);
        
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getColumn());
        
        assertEquals(null, grid.getCell(-1, -1));
    }
}