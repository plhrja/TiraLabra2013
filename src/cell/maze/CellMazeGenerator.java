package cell.maze;

import cell.grid.CellGrid;

/**
 * A maze generator interface that produces small, medium and large {@code CellGrid} objects.
 * The different dimensions are not explicitly specified in the documentation!
 * @author Easysimulation
 */
public interface CellMazeGenerator {
    
    /**
     * Returns a small {@code CellGrid} object representation of a maze. The exact dimension is not specified.
     * @return small {@code CellGrid} maze.
     */
    public CellGrid generateSmallMaze();
    /**
     * Returns a medium {@code CellGrid} object representation of a maze. The exact dimension is not specified.
     * @return medium {@code CellGrid} maze.
     */
    public CellGrid generateMediumMaze();
    /**
     *Returns a large {@code CellGrid} object representation of a maze. The exact dimension is not specified.
     * @return large {@code CellGrid} maze.
     */
    public CellGrid generateLargeMaze();

}
