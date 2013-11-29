package cell.grid;

import MyArrayList.MyArrayList;
import cell.Cell;

/**
 * An implementation of a static printer object that prints an ASCII representation of a <br>
 * {@code CellGrid} object.
 * @author Easysimulation
 * @see CellGrid
 */
public class CellGridPrinter {

    /**
     * Prints an ASCII representation of a specified {@code CellGrid} object.
     * @param grid the {@code CellGrid} object to be printed.
     */
    public static void printGrid(CellGrid grid) {
        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                System.out.print(grid.getCell(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Prints an ASCII representation of a specified {@code CellGrid} object and a path <br>
     * specified in an {@code MyArrayList} list.
     * @param grid the {@code CellGrid} object to be printed.
     * @param path the {@code MyArrayList} list containing the path to be printed.
     */
    public static void printGridWithPath(CellGrid grid, MyArrayList<Cell> path) {
        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                System.out.print(
                        (path.contains(grid.getCell(i, j))) ? 
                        "• " : grid.getCell(i, j) + " ");
            }
            System.out.println();
        }
    }
}
