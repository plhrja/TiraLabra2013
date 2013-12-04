package cell.neighbourTools;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.util.Random;

/**
 * A collection of static methods used in analysis of neighbouring {@code Cell} objects nested in a
 * {@code CellGrid} object.
 * @author rsirvio
 * @see CellGrid
 * @see Cell
 */
public class NeighbourTools {

    private static boolean insideBoundaries(int row, int col, CellGrid grid) {
        return row > 0 && col > 0
                && row < grid.getRowLength() - 1
                && col < grid.getColumnLength() - 1;
    }

    /**
     * Adds neighbouring {@code Cell} objects to the {@code Cell} object located at the position defined by
     * the row and column positions in the parameters into a list (defined in the parameters) that are not marked
     * as a part of the maze (i.e. that are unconnected). Also adds the neighbouring {@code Cell} objects relative direction
     * to the {@code Cell} objects located at the position defined by the row and column positions in the parameters
     * neighbouring mazes directions list.
     * @param row the row position of the {@code Cell} object thats' neighbouring cells are analysed.
     * @param col the column position of the {@code Cell} object thats' neighbouring cells are analysed.
     * @param neighbourCells the list into which the neighbouring {@code Cell} objects are added.
     * @param grid the {@code CellGrid} object in which the {@code Cell} objects are nested
     * @param steps the distance between the neighbouring {@code Cell} objects.
     */
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

    /**
     * Connects the {@code Cell} object defined in the parameters located in the {@code CellGrid} object
     * to the maze to be generated, i.e. marking it as a part of the maze and opening the {@code Cell} object
     * between the this current {@code Cell} object and a neighbouring {@code Cell} object that is already connected
     * to the maze.
     * @param cell the {@code Cell} object to be connected.
     * @param grid the {@code CellGrid} object where the defined {@code Cell} object resides.
     */
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

    /**
     * Essentially the same as the {@code connectNeighbourToMaze} method, but doing it to
     * two predefined {@code Cell} objects instead of doing it pseudo-randomly.
     */
    public static void connect(Cell cell1, Cell cell2, CellGrid grid) {
        int row = (cell1.getRow() - cell2.getRow() == 0)
                ? cell1.getRow() : Math.min(cell1.getRow(), cell2.getRow()) + 1;
        int col = (cell1.getColumn() - cell2.getColumn() == 0)
                ? cell1.getColumn() : Math.min(cell1.getColumn(), cell2.getColumn()) + 1;
        grid.getCell(row, col).isSolid(false);
    }

    /**
     * A method intended to be used by a pathsolving algorithm. It adds all neighbouring {@code Cell} objects, that
     * have either not been processed before or would have a lower cost to the beginning after the addition,
     * to a list and finally returns the list.
     * @param grid the {@code CellGrid} object where the {@code Cell} objects are nested.
     * @param parent the {@code Cell} object thats' neighbours are analysed.
     * @return a list containing the appropriate neighbouring {@code Cell} objects.
     */
    public static MyArrayList getOpenNeighboursToCoordinatesAndSetDistAndParent(CellGrid grid, Cell parent) {
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
