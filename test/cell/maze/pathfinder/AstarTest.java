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
import org.junit.Test;

/**
 *
 * @author Roope
 */
public class AstarTest {

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
        System.out.println("Manhattan:");
        assertEquals(true, heuristicWorkingProperly(new ManhattanHeuristic()));
        System.out.println("------------------------------------------------");
        System.out.println();
    }
    
    @Test
    public void euclideanHeuristicWorkingProperly(){
        System.out.println("Euclidean:");
        assertEquals(true, heuristicWorkingProperly(new EuclideanHeuristic()));
        System.out.println("------------------------------------------------");
        System.out.println();
    }
    
    //NOTE: this test is to show that Semi-Euclidean method for distance-estimation
    //is, although useful, NOT a real heuristic in the sence of the definition of a heuristic.
    @Test
    public void semiEuclideanHeuristicIsNotARealHeuristic(){
        System.out.println("Semi-Euclidean:");
        assertEquals(false, heuristicWorkingProperly(new SemiEuclideanHeuristic()));
        System.out.println("------------------------------------------------");
        System.out.println("");
    }
    @Test
    public void dijkstraHeuristicWorkingProperly(){
        System.out.println("Dijikstra");
        assertEquals(true, heuristicWorkingProperly(new DijkstraHeuristic()));
    }
}