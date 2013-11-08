package cell.grid;

import cell.Cell;
import java.util.ArrayList;

public class CellGridPrinter {

    public static void printGrid(CellGrid grid) {
        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                System.out.print(grid.getCell(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    public static void printGridWithPath(CellGrid grid, ArrayList<Cell> path) {
        for (int i = 0; i < grid.getRowLength(); i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                System.out.print(
                        (path.contains(grid.getCell(i, j))) ? 
                        "• " : grid.getCell(i, j) + " ");
            }
            System.out.println();
        }
    }

    //print-testing
    public static void main(String[] args) {
        CellGrid cellGrid = new CellGrid(15, 15);
        ArrayList<Cell> path = new ArrayList<>();
        
        for (int i = 0; i < cellGrid.getRowLength(); i++) {
            cellGrid.getCell(i, 5).isSolid(false);
            cellGrid.getCell(9, i).isSolid(false);
            path.add(cellGrid.getCell(9, i));
        }
        printGridWithPath(cellGrid, path);
    }
}
