package cell.maze.pathfinder;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import cell.grid.CellGridPrinter;
import cell.maze.Pathfinder.Astar;
import heuristics.DijkstraHeuristic;
import heuristics.EuclideanHeuristic;
import heuristics.Heuristic;
import heuristics.ManhattanHeuristic;
import heuristics.SemiEuclideanHeuristic;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Roope
 */
public class AstarTest {

    public AstarTest() {
    }
    private static final CellGrid MAZE_1 = CellGridParser.parseTextToMaze("maze1.txt");
    private static final CellGrid MAZE_2 = CellGridParser.parseTextToMaze("maze2.txt");
    private static final CellGrid MAZE_3 = CellGridParser.parseTextToMaze("maze3.txt");
    private Astar astar;

    private void initAstar(Heuristic heuristic) {
        astar = new Astar(heuristic);
    }

    private boolean heuristicWorkingProperly(Heuristic heuristic) {
        initAstar(heuristic);
        boolean workingProperly = true;
        MyArrayList<Cell> path;

        path = astar.findPath(MAZE_1.getCell(1, 1),
                              MAZE_1.getCell(MAZE_1.getRowLength() - 2, 1),
                              MAZE_1,
                              0);
        CellGridPrinter.printGridWithPath(MAZE_1, path);
        System.out.println();
        workingProperly = (workingProperly && path.size() == 21) ? true : false;

        path = astar.findPath(MAZE_2.getCell(1, 1),
                              MAZE_2.getCell(MAZE_2.getRowLength() - 2, 1),
                              MAZE_2,
                              0);
        CellGridPrinter.printGridWithPath(MAZE_2, path);
        System.out.println();
        workingProperly = (workingProperly && path.size() == 24) ? true : false;

        path = astar.findPath(MAZE_3.getCell(MAZE_3.getRowLength() - 2, 1),
                              MAZE_3.getCell(1, MAZE_3.getColumnLength() - 2),
                              MAZE_3,
                              0);
        CellGridPrinter.printGridWithPath(MAZE_3, path);
        System.out.println();
        workingProperly = (workingProperly && path.size() == 39) ? true : false;

        return workingProperly;
    }
    
    @Test
    public void manhattanHeuristicWorkingProperly(){
        assertEquals(true, heuristicWorkingProperly(new ManhattanHeuristic()));
    }
    
    @Test
    public void euclideanHeuristicWorkingProperly(){
        assertEquals(true, heuristicWorkingProperly(new EuclideanHeuristic()));
    }
    
    //NOTE: this test is to show that Semi-Euclidean method for distance-estimation
    //is, although useful, NOT a real heuristic in the sence of the definition of a heuristic.
    @Test
    public void semiEuclideanHeuristicIsNotARealHeuristic(){
        assertEquals(false, heuristicWorkingProperly(new SemiEuclideanHeuristic()));
    }
    @Test
    public void dijkstraHeuristicWorkingProperly(){
        assertEquals(true, heuristicWorkingProperly(new DijkstraHeuristic()));
    }
}