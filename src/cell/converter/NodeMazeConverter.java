package cell.converter;

import cell.grid.CellGrid;
import cell.grid.CellGridPrinter;
import node.grid.NodeGrid;
import node.maze.DFSNodeMazeGenerator;
import node.maze.PrimNodeMazeGenerator;

public class NodeMazeConverter {

    public static CellGrid convertNodeMazeToCellMaze(NodeGrid nodeGrid) {
        CellGrid cellGrid = new CellGrid((nodeGrid.getRowLength() * 2) + 1, (nodeGrid.getColumnLength() * 2) + 1);

        for (int i = 0; i < nodeGrid.getRowLength(); i++) {
            for (int j = 0; j < nodeGrid.getColumnLength(); j++) {
                int iScaled = (i * 2) + 1;
                int jScaled = (j * 2) + 1;
                cellGrid.getCell(iScaled, jScaled).isSolid(false);
                if(j + 1 < nodeGrid.getColumnLength() && nodeGrid.isConnected(i, j, i, j + 1)){
                    cellGrid.getCell(iScaled, jScaled + 1).isSolid(false);
                } 
                if(i + 1 < nodeGrid.getRowLength() && nodeGrid.isConnected(i, j, i + 1, j)){
                    cellGrid.getCell(iScaled + 1, jScaled).isSolid(false);
                } 
            }
        }

        return cellGrid;
    }
    
    //test-main
    public static void main(String[] args) {
        CellGridPrinter.printGrid(convertNodeMazeToCellMaze(new PrimNodeMazeGenerator().generateMediumMaze()));
        System.out.println();
        System.out.println();
        CellGridPrinter.printGrid(convertNodeMazeToCellMaze(new DFSNodeMazeGenerator().generateMediumMaze()));
    }
}
