package speedTesting.mazeGeneration;

import cell.maze.CellMazeGenerator;
import cell.maze.DFSCellMazeGenerator;
import cell.maze.PrimCellMazeGenerator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MazeGenerationSpeedTest {

    private File datFile;
    private CellMazeGenerator mazeGenerator;

    public MazeGenerationSpeedTest(String filename, CellMazeGenerator mazeGenerator) {
        File tempDatFile = new File(filename);
        if (tempDatFile.exists()) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(tempDatFile, true)));
                writer.print("");
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(MazeGenerationSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                tempDatFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(MazeGenerationSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.datFile = tempDatFile;
        this.mazeGenerator = mazeGenerator;
    }
    
    public void test(int minBoundary, int maxBoundary, int increment){
        ArrayList<Integer> testSizes = new ArrayList<>();
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(this.datFile, true)));
        } catch (IOException ex) {
            Logger.getLogger(MazeGenerationSpeedTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        for (int i = minBoundary; i <= maxBoundary; i += increment) {
            testSizes.add(i);
        }
        
        for (int i = 0; i < testSizes.size(); i++) {
            int size = testSizes.get(i) + 1;
            
            long startTime = System.currentTimeMillis();
            this.mazeGenerator.generateCustomMaze(size, size);
            long finishTime = System.currentTimeMillis() - startTime;
            
            String print = size + "\t" + finishTime;
            System.out.println(print);
            writer.println(print);
        }
        writer.close();
    }
    
    public static void main(String[] args) {
        MazeGenerationSpeedTest test = new MazeGenerationSpeedTest("primGen_speedTest3.dat", new PrimCellMazeGenerator());
        test.test(100, 1500, 10);
    }
}
