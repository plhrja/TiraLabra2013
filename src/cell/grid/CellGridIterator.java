package cell.grid;

import cell.Cell;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CellGridIterator implements Iterator<Cell> {

    private CellGrid grid;
    private int counter;

    public CellGridIterator(CellGrid grid) {
        this.grid = grid;
        counter = 0;
    }

    @Override
    public boolean hasNext() {
        return counter < grid.getColumnLength() * grid.getRowLength();
    }

    @Override
    public Cell next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int row = counter / grid.getColumnLength();
        int column = counter % grid.getColumnLength();
        this.counter++;
        return grid.getCell(row, column);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported, see documentation!");
    }
}
