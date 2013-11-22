package cell.grid;

public class CellGridGenerator {
    
    private static final int SMALL_ROW_SIZE = 25;
    private static final int SMALL_COLUMN_SIZE = 25;
    private static final int MEDIUM_ROW_SIZE = 49;
    private static final int MEDIUM_COLUMN_SIZE = 49;
    private static final int LARGE_ROW_SIZE = 71;
    private static final int LARGE_COLUMN_SIZE = 71;
    
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
