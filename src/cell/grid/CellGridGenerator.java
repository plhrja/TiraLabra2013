package cell.grid;

/**
 * A static tool for creating {@code CellGrid} structures of predefined size. <br>
 * The different dimensions are not explicitly specified in the documentation!
 * @author Easysimulation
 * @see CellGrid
 */
public class CellGridGenerator {
    
    private static final int SMALL_ROW_SIZE = 41;
    private static final int SMALL_COLUMN_SIZE = 65;
    private static final int MEDIUM_ROW_SIZE = 61;
    private static final int MEDIUM_COLUMN_SIZE = 85;
    private static final int LARGE_ROW_SIZE = 79;
    private static final int LARGE_COLUMN_SIZE = 105;
    
    /**
     * Returns a small {@code CellGrid} object. The exact dimension is not specified.
     * @return small {@code CellGrid} object.
     */
    public static CellGrid generateSmallGrid(){
        return new CellGrid(SMALL_ROW_SIZE, SMALL_COLUMN_SIZE);
    }
    
    /**
     * Returns a medium {@code CellGrid} object. The exact dimension is not specified.
     * @return medium {@code CellGrid} object.
     */
    public static CellGrid generateMediumGrid(){
        return new CellGrid(MEDIUM_ROW_SIZE, MEDIUM_COLUMN_SIZE);
    }
    
    /**
     * Returns a large {@code CellGrid} object. The exact dimension is not specified.
     * @return large {@code CellGrid} object.
     */
    public static CellGrid generateLargeGrid(){
        return new CellGrid(LARGE_ROW_SIZE, LARGE_COLUMN_SIZE);
    }

}
