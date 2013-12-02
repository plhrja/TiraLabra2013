
package cell;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Roope
 */
public class cellTest {
    
    @Test
    public void constructorsWorkProperly(){
        Cell cell1 = new Cell(1,1, false);
        Cell cell2 = new Cell(1,1);
        Cell cell3 = new Cell();
        
        assertEquals(false, cell1.isSolid());
        assertEquals(1, cell1.getRow());
        assertEquals(1, cell1.getColumn());
        
        assertEquals(true, cell2.isSolid());
        assertEquals(1, cell2.getRow());
        assertEquals(1, cell2.getColumn());
        
        assertEquals(true, cell3.isSolid());
        assertEquals(-1, cell3.getRow());
        assertEquals(-1, cell3.getColumn());
    }
    
    @Test
    public void settingDifferentCellTypesWork(){
        Cell cell = new Cell();
        assertEquals(true, cell.isSolid());
        
        cell.isSolid(false);
        assertEquals(false, cell.isSolid());
    }
    
    @Test
    public void settingSumOfCostsBelowMaxReturnsTheSumOtherwiseMax(){
        Cell cell = new Cell();
        
        cell.setCostToBeginning(5.5);
        cell.setCostToFinish(5);
        assertEquals(cell.getTotalCost(), 10.5, 1e-12);
        
        cell.setCostToBeginning(Integer.MAX_VALUE);
        cell.setCostToFinish(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, (int) cell.getTotalCost());
    }
    
    @Test
    public void equalsMethodWorkingAsDefinedBySpecs(){
        Cell cell = new Cell();
        Cell differentCell = new Cell();
        Cell differentCellWithDifferentRows = new Cell(1,1);
        
        cell.setCostToBeginning(5);
        cell.setCostToFinish(5);
        
        differentCell.setCostToBeginning(5);
        differentCell.setCostToFinish(5);
        assertEquals(true, cell.equals(differentCell));
        
        differentCell.setCostToFinish(Integer.MAX_VALUE);
        assertEquals(false, cell.equals(differentCell));
        
        differentCellWithDifferentRows.setCostToBeginning(5);
        differentCellWithDifferentRows.setCostToFinish(5);
        
        assertEquals(false, cell.equals(differentCellWithDifferentRows));
    }
    
    @Test
    public void compareToMethodWorkingAsDefinedBySpecs(){
        Cell cell1 = new Cell();
        Cell cell2 = new Cell();
        
        cell1.setCostToBeginning(5);
        cell1.setCostToFinish(5);
        
        cell2.setCostToBeginning(5);
        cell2.setCostToFinish(4);
        assertEquals(1, cell1.compareTo(cell2));
        assertEquals(-1, cell2.compareTo(cell1));
        
        cell2.setCostToFinish(5);
        assertEquals(0, cell2.compareTo(cell1));
        assertEquals(0, cell1.compareTo(cell2));
    }

}