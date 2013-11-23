package cell.maze;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import cell.grid.CellGridGenerator;
import cell.grid.CellGridPrinter;
import cell.neighbourTools.NeighbourTools;
import java.util.Random;

public class DFSCellMazeGenerator implements CellMazeGenerator{
    
    private static CellGrid generateDFSMaze(CellGrid grid) {
        int randomRow = new Random().nextInt(grid.getRowLength() - 1);
        int randomCol = new Random().nextInt(grid.getColumnLength() - 1);
        int row = randomRow + ((randomRow + 1) % 2);
        int col = randomCol + ((randomCol + 1) % 2);

        DFSThroughGrid(grid, grid.getCell(row, col), null);

        return grid;
    }

    private static void DFSThroughGrid(CellGrid grid, Cell nextCell, Cell prevCell) {
        if (prevCell == null) {
            grid.getCell(nextCell.getRow(), nextCell.getColumn()).InMaze(true);
            grid.getCell(nextCell.getRow(), nextCell.getColumn()).isSolid(false);
        } else if (nextCell.isInMaze()) {
            return;
        } else {
            grid.getCell(nextCell.getRow(), nextCell.getColumn()).InMaze(true);
            grid.getCell(nextCell.getRow(), nextCell.getColumn()).isSolid(false);
            NeighbourTools.connect(nextCell, prevCell, grid);
        }
        
        MyArrayList<Cell> neighbourCells = new MyArrayList<>();
        NeighbourTools.addInMazeNeighboursToCoordinates(nextCell.getRow(), nextCell.getColumn(), neighbourCells, grid, 2);
        
        while (!neighbourCells.isEmpty()) {
            Cell cell = neighbourCells.get(new Random().nextInt(neighbourCells.size()));
            DFSThroughGrid(grid,
                    cell,
                    nextCell);
            neighbourCells.remove(cell);
        }
    }

    @Override
    public CellGrid generateSmallMaze() {
        return generateDFSMaze(CellGridGenerator.createSmallGrid());
    }

    @Override
    public CellGrid generateMediumMaze() {
        return generateDFSMaze(CellGridGenerator.createMediumGrid());
    }

    @Override
    public CellGrid generateLargeMaze() {
        return generateDFSMaze(CellGridGenerator.createLargeGrid());
    }
    
    //test-main
    public static void main(String[] args) {
        CellGridPrinter.printGrid(new DFSCellMazeGenerator().generateSmallMaze());
    }
    
}
