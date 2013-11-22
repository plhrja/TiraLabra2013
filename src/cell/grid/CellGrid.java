package cell.grid;

import cell.Cell;
import java.util.Iterator;

public class CellGrid implements Iterable<Cell>{

    private Cell[][] grid;
    private int rowLength;
    private int columnLength;

    public CellGrid(int rowLength, int columnLength) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.grid = new Cell[rowLength][columnLength];

        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
    }

    public CellGrid(int rowLength, int columnLength, boolean solid) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.grid = new Cell[rowLength][columnLength];

        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                this.grid[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int column) {
        return (row > -1 && row < this.getRowLength() && column > -1 && column < this.getColumnLength()) ? 
                grid[row][column] : null;
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getColumnLength() {
        return columnLength;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellGridIterator(this);
    }
}
