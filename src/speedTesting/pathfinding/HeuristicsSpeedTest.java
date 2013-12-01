package speedTesting.pathfinding;

import cell.grid.CellGrid;
import cell.maze.CellMazeGenerator;
import cell.maze.DFSCellMazeGenerator;
import cell.maze.Pathfinder.Astar;
import cell.maze.Pathfinder.Pathfinder;
import cell.maze.PrimCellMazeGenerator;
import heuristics.DijikstraHeuristic;
import heuristics.EuclideanHeuristic;
import heuristics.Heuristic;
import heuristics.ManhattanHeuristic;
import heuristics.SemiEuclideanHeuristic;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeuristicsSpeedTest implements Observer {

    private Heuristic[] heuristics;
    private CellMazeGenerator generator;
    private int counter;

    public HeuristicsSpeedTest(CellMazeGenerator generator) {
        this.heuristics = new Heuristic[]{new ManhattanHeuristic(),
                                         new EuclideanHeuristic(),
                                         new SemiEuclideanHeuristic(),
                                         new DijikstraHeuristic()};
        this.generator = generator;
        this.counter = 0;
    }

    public void test(String filename, int repetitions) {
        File datFile = initFile(filename);
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(datFile, true)));
        } catch (IOException ex) {
            Logger.getLogger(HeuristicsSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        int sumOfSteps = 0;
        for (int i = 0; i <= repetitions; i++) {
            CellGrid maze = this.generator.generateCustomMaze(131, 131);
            for (int j = 0; j < this.heuristics.length; j++) {
                Pathfinder astar = new Astar(this.heuristics[j]);
                astar.addObserver(this);
                
                //top-left to low-right
                astar.findPath(maze.getCell(1, 1),
                               maze.getCell(maze.getRowLength()-2, maze.getColumnLength() - 2),
                               maze,
                               0);
                sumOfSteps += this.counter;
                this.counter = 0;
                
                //low-left to top-right
                astar.findPath(maze.getCell(maze.getRowLength() - 2, 1),
                               maze.getCell(1, maze.getColumnLength() - 2),
                               maze,
                               0);
                sumOfSteps += this.counter;
                this.counter = 0;
                
                //low-left to top-left
                astar.findPath(maze.getCell(maze.getRowLength() - 2, 1),
                               maze.getCell(1, 1),
                               maze,
                               0);
                sumOfSteps += this.counter;
                this.counter = 0;
                
                //low-right to top-right
                astar.findPath(maze.getCell(maze.getRowLength() - 2, maze.getColumnLength() - 2),
                               maze.getCell(1, maze.getColumnLength() - 2),
                               maze,
                               0);
                sumOfSteps += this.counter;
                this.counter = 0;
                
                int avg = sumOfSteps/4;
                System.out.println(i + ", " + avg);
                writer.print(avg + "\t");
                sumOfSteps = 0;
            }
            writer.println();
        }
        writer.close();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.counter++;
    }

    private File initFile(String filename) {
        File datFile = new File(filename);
        if (datFile.exists()) {
            try {
                PrintWriter writer = new PrintWriter(datFile);
                writer.print("");
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(HeuristicsSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            try {
                datFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HeuristicsSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return datFile;
    }
    
    public static void main(String[] args) {
        HeuristicsSpeedTest primHeuristicsSpeedTest = new HeuristicsSpeedTest(new DFSCellMazeGenerator());
        primHeuristicsSpeedTest.test("heuristics_speedTest_DFS.dat", 100);
    }
}
