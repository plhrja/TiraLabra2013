package cell.maze.pathfinder;

import cell.grid.CellGrid;
import heuristics.Heuristic;
import org.junit.Before;

/**
 *
 * @author Roope
 */
public class AstarTest {
    
    public AstarTest() {
    }
    
    private Astar astar;
    
    private void initAstar(Heuristic heuristic){
        astar = new Astar(heuristic);
    }
    
    private CellGrid maze1(){
        CellGrid maze = new CellGrid(7, 11, false);
        for (int i = 1; i < 6; i++) {
            maze.getCell(1, i).isSolid(false);
        }
        for (int i = 0; i < 9; i++) {
            
        }
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(3, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        maze.getCell(2, 5).isSolid(true);
        return maze;
    }
    
}