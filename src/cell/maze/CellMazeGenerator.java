package cell.maze;

import cell.grid.CellGrid;

public interface CellMazeGenerator {
    
    public CellGrid generateSmallMaze();
    public CellGrid generateMediumMaze();
    public CellGrid generateLargeMaze();

}
