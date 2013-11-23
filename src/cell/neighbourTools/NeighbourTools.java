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

    public static void addInMazeNeighboursToCoordinates(int row, int col, MyArrayList<Cell> neighbourCells, CellGrid grid, int steps) {
        if (insideBoundaries(row + steps, col, grid) && !grid.getCell(row + steps, col).isInMaze()) {
            neighbourCells.add(grid.getCell(row + steps, col));
            grid.getCell(row + steps, col).addHasInMazeNeighboursIn('N');
        }
        if (insideBoundaries(row, col + steps, grid) && !grid.getCell(row, col + steps).isInMaze()) {
            neighbourCells.add(grid.getCell(row, col + steps));
            grid.getCell(row, col + steps).addHasInMazeNeighboursIn('W');
        }
        if (insideBoundaries(row - steps, col, grid) && !grid.getCell(row - steps, col).isInMaze()) {
            neighbourCells.add(grid.getCell(row - steps, col));
            grid.getCell(row - steps, col).addHasInMazeNeighboursIn('S');
        }
        if (insideBoundaries(row, col - steps, grid) && !grid.getCell(row, col - steps).isInMaze()) {
            neighbourCells.add(grid.getCell(row, col - steps));
            grid.getCell(row, col - steps).addHasInMazeNeighboursIn('E');
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
    
    public static MyArrayList getOpenNeighboursToCoordinates(CellGrid grid, Cell parent) {
        MyArrayList<Cell> openNeighbours = new MyArrayList<>();
        int row = parent.getRow();
        int column = parent.getColumn();

        Cell south = grid.getCell(row + 1, column);
        Cell east = grid.getCell(row, column + 1);
        Cell north = grid.getCell(row - 1, column);
        Cell west = grid.getCell(row, column - 1);


        if (south != null && !south.isSolid()) {
            setDistAndAddParent(south, parent, openNeighbours);
        }
        if (east != null && !east.isSolid()) {
            setDistAndAddParent(east, parent, openNeighbours);
        }
        if (north != null && !north.isSolid()) {
            setDistAndAddParent(north, parent, openNeighbours);
        }
        if (west != null && !west.isSolid()) {
            setDistAndAddParent(west, parent, openNeighbours);
        }

        return openNeighbours;
    }
    
    private static void setDistAndAddParent(Cell cellToAdd, Cell parent, MyArrayList<Cell> openNeighbours) {
        double newCost = parent.getCostToBeginning() + 1;
        if ((cellToAdd.getParentCell() == null) || newCost < cellToAdd.getCostToBeginning()) {
            cellToAdd.setCostToBeginning(newCost);
            cellToAdd.setParentCell(parent);
            openNeighbours.add(cellToAdd);
        }
    }
}
