package cell.grid;

public class CellGridGenerator {
    
    private static final int SMALL_ROW_SIZE = 41;
    private static final int SMALL_COLUMN_SIZE = 65;
    private static final int MEDIUM_ROW_SIZE = 61;
    private static final int MEDIUM_COLUMN_SIZE = 85;
    private static final int LARGE_ROW_SIZE = 79;
    private static final int LARGE_COLUMN_SIZE = 105;
    
    public static CellGrid createSmallGrid(){
        return new CellGrid(SMALL_ROW_SIZE, SMALL_COLUMN_SIZE);
    }
    
    public static CellGrid createMediumGrid(){
        return new CellGrid(MEDIUM_ROW_SIZE, MEDIUM_COLUMN_SIZE);
    }
    
    public static CellGrid createLargeGrid(){
        return new CellGrid(LARGE_ROW_SIZE, LARGE_COLUMN_SIZE);
    }

}
