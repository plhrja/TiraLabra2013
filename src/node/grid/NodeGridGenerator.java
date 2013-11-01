package node.grid;

public class NodeGridGenerator {

    private static final int SMALL_ROW_SIZE = 27;
    private static final int SMALL_COLUMN_SIZE = 40;
    private static final int MEDIUM_ROW_SIZE = 30;
    private static final int MEDIUM_COLUMN_SIZE = 45;
    private static final int LARGE_ROW_SIZE = 40;
    private static final int LARGE_COLUMN_SIZE = 55;
    
    public static NodeGrid generateSmallGrid(){
        return new NodeGrid(SMALL_ROW_SIZE, SMALL_COLUMN_SIZE);
    }
    
    public static NodeGrid generateMediumGrid(){
        return new NodeGrid(MEDIUM_ROW_SIZE, MEDIUM_COLUMN_SIZE);
    }
    
    public static NodeGrid generateLargeGrid(){
        return new NodeGrid(LARGE_ROW_SIZE, LARGE_COLUMN_SIZE);
    }

}
