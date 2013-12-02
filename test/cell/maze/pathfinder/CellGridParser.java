package cell.maze.pathfinder;

import cell.grid.CellGrid;
import cell.grid.CellGridPrinter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CellGridParser {

    private static final String PATH_TO_TEST_MAZES = "test/testMazes/";
    
    public static CellGrid parseTextToMaze(String filename){
        File file = new File(PATH_TO_TEST_MAZES + filename);
        if(!file.exists()){
            System.err.println("No file named " + filename + " exists!");
            return null;
        }
        int rowLength = countRows(file);
        int columnLength = countColumns(file);
        if(rowLength == 0 || columnLength == 0){
            return null;
        }
        CellGrid grid = new CellGrid(rowLength, columnLength, false);
        
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CellGridParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        int counter = 0;
        while(reader.hasNextLine()){
            String nextLine = reader.nextLine();
            for (int i = 0; i < nextLine.length(); i++) {
                if(nextLine.charAt(i) == '#'){
                    grid.getCell(counter, i).isSolid(true);
                }
            }
            counter++;
        }
        
        reader.close();
        return grid;
    }

    private static int countColumns(File file) {
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CellGridParser.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
        if(reader.hasNextLine()){
            return reader.nextLine().length();
        }
        
        reader.close();
        return 0;
    }

    private static int countRows(File file) {
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CellGridParser.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
        int counter = 0;
        while(reader.hasNextLine()){
            reader.nextLine();
            counter++;
        }
        
        reader.close();
        return counter;
    }
    
    public static void main(String[] args) {
        CellGridPrinter.printGrid(CellGridParser.parseTextToMaze("maze3.txt"));
    }
    
}
