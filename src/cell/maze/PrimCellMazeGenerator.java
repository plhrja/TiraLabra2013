package cell.maze;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import cell.grid.CellGridGenerator;
import cell.grid.CellGridPrinter;
import cell.neighbourTools.NeighbourTools;
import java.util.Random;

public class PrimCellMazeGenerator implements CellMazeGenerator{

    private static CellGrid generatePrimMaze(CellGrid grid){
        Random rand = new Random();
        MyArrayList<Cell> neighbourCells = new MyArrayList<>();
        
        int randomRow = rand.nextInt(grid.getRowLength() - 1);
        int randomCol = rand.nextInt(grid.getColumnLength() - 1);
        int row = randomRow + ((randomRow + 1) % 2);
        int col = randomCol + ((randomCol + 1) % 2);
        
        grid.getCell(row, col).InMaze(true);
        grid.getCell(row, col).isSolid(false);
        NeighbourTools.addInMazeNeighboursToCoordinates(row, col, neighbourCells, grid, 2);
        
        while(!neighbourCells.isEmpty()){
            Cell cell = neighbourCells.get(rand.nextInt(neighbourCells.size()));
            if(!cell.isInMaze()){
                NeighbourTools.connectNeighbourToMaze(cell, grid);
            }
            NeighbourTools.addInMazeNeighboursToCoordinates(cell.getRow(), cell.getColumn(), neighbourCells, grid, 2);
            neighbourCells.remove(cell);
        }
        
        return grid;
    }
    
    @Override
    public CellGrid generateSmallMaze() {
        return generatePrimMaze(CellGridGenerator.createSmallGrid());
    }

    @Override
    public CellGrid generateMediumMaze() {
       return generatePrimMaze(CellGridGenerator.createMediumGrid());
    }

    @Override
    public CellGrid generateLargeMaze() {
        return generatePrimMaze(CellGridGenerator.createLargeGrid());
    }
    
    //test-main
    public static void main(String[] args) {
        CellGridPrinter.printGrid(new PrimCellMazeGenerator().generateSmallMaze());
    }
 

}
