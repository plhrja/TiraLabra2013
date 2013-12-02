package cell.grid;

import cell.Cell;
import java.util.Iterator;

/**
 * An implementation of grid/matrix of {@code Cell} objects.
 *
 * @author Easysimulation
 * @see Cell
 */
public class CellGrid implements Iterable<Cell> {

    private Cell[][] grid;
    private int rowLength;
    private int columnLength;

    /**
     * Constructor creates a grid of {@code Cell} objects with given row length
     * and <br>
     * column length. All cells are initially set as solid.
     *
     * @param rowLength row length of the grid.
     * @param columnLength column length of the grid.
     */
    public CellGrid(int rowLength, int columnLength) {
        this(rowLength, columnLength, true);
    }

    /**
     * Constructor creates a grid of {@code Cell} objects with given row length
     * and <br>
     * column length. Type of the {@code Cell} objects is defined by the
     * {@code solid} parameter.
     *
     * @param rowLength row length of the grid.
     * @param columnLength column length of the grid.
     * @param solid type of the {@code Cell} objects.
     */
    public CellGrid(int rowLength, int columnLength, boolean solid) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.grid = new Cell[rowLength][columnLength];

        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                this.grid[i][j] = new Cell(i, j, solid);
            }
        }
    }

    /**
     * Returns the {@code Cell} object at given position.
     *
     * @param row row position of the {@code Cell} object.
     * @param column column position of the {@code Cell} object.
     * @return the {@code Cell} object at the given position determined by
     * {@code row} and {@code column}
     */
    public Cell getCell(int row, int column) {
        return (row > -1 && row < this.getRowLength() && column > -1 && column < this.getColumnLength())
                ? grid[row][column] : null;
    }

    /**
     * Returns the row length of the grid.
     *
     * @return row length of the grid.
     */
    public int getRowLength() {
        return rowLength;
    }

    /**
     * Returns the column length of the grid.
     *
     * @return column length of the grid.
     */
    public int getColumnLength() {
        return columnLength;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellGridIterator(this);
    }
}
