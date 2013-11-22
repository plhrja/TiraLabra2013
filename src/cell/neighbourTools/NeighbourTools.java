package cell.neighbourTools;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.util.Random;

public class NeighbourTools {

    private static boolean insideBoundaries(int row, int col, CellGrid grid) {
        return row > 0 && col > 0
                && row < grid.getRowLength() - 1
                && col < grid.getColumnLength() - 1;
    }

    public static void addNeighboursToCoordinates(int row, int col, MyArrayList<Cell> neighbourCells, CellGrid grid) {
        if (insideBoundaries(row + 2, col, grid) && !grid.getCell(row + 2, col).isInMaze()) {
            neighbourCells.add(grid.getCell(row + 2, col));
            grid.getCell(row + 2, col).addHasInMazeNeighboursIn('N');
        }
        if (insideBoundaries(row, col + 2, grid) && !grid.getCell(row, col + 2).isInMaze()) {
            neighbourCells.add(grid.getCell(row, col + 2));
            grid.getCell(row, col + 2).addHasInMazeNeighboursIn('W');
        }
        if (insideBoundaries(row - 2, col, grid) && !grid.getCell(row - 2, col).isInMaze()) {
            neighbourCells.add(grid.getCell(row - 2, col));
            grid.getCell(row - 2, col).addHasInMazeNeighboursIn('S');
        }
        if (insideBoundaries(row, col - 2, grid) && !grid.getCell(row, col - 2).isInMaze()) {
            neighbourCells.add(grid.getCell(row, col - 2));
            grid.getCell(row, col - 2).addHasInMazeNeighboursIn('E');
        }
    }

    public static void connectNeighbourToMaze(Cell cell, CellGrid grid) {
        char direction = cell.getHasInMazeNeighboursIn().get(new Random().nextInt(cell.getHasInMazeNeighboursIn().size()));
        int row = cell.getRow();
        int column = cell.getColumn();
        switch (direction) {
            case 'N':
                grid.getCell(row - 1, column).isSolid(false);
                grid.getCell(row, column).isSolid(false);
                break;
            case 'W':
                grid.getCell(row, column - 1).isSolid(false);
                grid.getCell(row, column).isSolid(false);
                break;
            case 'S':
                grid.getCell(row + 1, column).isSolid(false);
                grid.getCell(row, column).isSolid(false);
                break;
            case 'E':
                grid.getCell(row, column + 1).isSolid(false);
                grid.getCell(row, column).isSolid(false);
                break;
        }
        grid.getCell(row, column).InMaze(true);
    }

    public static void connect(Cell cell1, Cell cell2, CellGrid grid) {
        int row = (cell1.getRow() - cell2.getRow() == 0)
                ? cell1.getRow() : Math.min(cell1.getRow(), cell2.getRow()) + 1;
        int col = (cell1.getColumn()- cell2.getColumn()== 0)
                ? cell1.getColumn(): Math.min(cell1.getColumn(), cell2.getColumn()) + 1;
        grid.getCell(row, col).isSolid(false);
    }
}
