package node.maze;

import MyArrayList.MyArrayList;
import java.util.Random;
import node.Node;
import node.grid.NodeGrid;
import node.grid.NodeGridGenerator;
import node.grid.nodeGridPrinter;

public class DFSNodeMazeGenerator implements NodeMazeGenerator {

    private static NodeGrid generateDFSMaze(NodeGrid grid) {
        int row = new Random().nextInt(grid.getRowLength());
        int column = new Random().nextInt(grid.getColumnLength());

        DFSThroughGrid(grid, grid.getNode(row, column), null);

        return grid;
    }

    private static void DFSThroughGrid(NodeGrid grid, Node nextNode, Node prevNode) {
        if (prevNode == null) {
            nextNode.InMaze(true);
        } else if (nextNode.isInMaze()) {
            return;
        } else {
            nextNode.InMaze(true);
            grid.connect(nextNode, prevNode);
        }

        MyArrayList<Node> neighbourNodes = new MyArrayList<>();
        MazeTools.addNeighboursToCoordinates(nextNode.getRow(), nextNode.getColumn(), neighbourNodes, grid);
        System.out.println();
        nodeGridPrinter.printGrid(grid);
        
        while(!neighbourNodes.isEmpty()){
            Node node = neighbourNodes.get(new Random().nextInt(neighbourNodes.size()));
            DFSThroughGrid(grid, 
                    neighbourNodes.get(new Random().nextInt(neighbourNodes.size())),
                    nextNode);
            neighbourNodes.remove(node);
        }

    }

    @Override
    public NodeGrid generateSmallMaze() {
        return generateDFSMaze(NodeGridGenerator.generateSmallGrid());
    }

    @Override
    public NodeGrid generateMediumMaze() {
        return generateDFSMaze(NodeGridGenerator.generateMediumGrid());
    }

    @Override
    public NodeGrid generateLargeMaze() {
        return generateDFSMaze(NodeGridGenerator.generateLargeGrid());
    }
    
    //Test-main
    public static void main(String[] args) {
        nodeGridPrinter.printGrid(new DFSNodeMazeGenerator().generateSmallMaze());
    }
}
