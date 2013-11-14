package node.maze;

import MyArrayList.MyArrayList;
import java.util.Random;
import node.Node;
import node.grid.NodeGrid;
import node.grid.NodeGridGenerator;
import node.grid.nodeGridPrinter;

public class PrimNodeMazeGenerator implements NodeMazeGenerator {

    private static NodeGrid generatePrimMaze(NodeGrid grid) {
        Random rand = new Random();
        MyArrayList<Node> neighbourNodes = new MyArrayList<>();

        int row = rand.nextInt(grid.getRowLength());
        int col = rand.nextInt(grid.getColumnLength());
        grid.getNode(row, col).InMaze(true);
        MazeTools.addNeighboursToCoordinates(row, col, neighbourNodes, grid);

        while (!neighbourNodes.isEmpty()) {
            Node node = neighbourNodes.get(rand.nextInt(neighbourNodes.size()));
            if (!node.isInMaze()) {
                MazeTools.connectNeighbourToMaze(node, grid);
            }
            MazeTools.addNeighboursToCoordinates(node.getRow(), node.getColumn(), neighbourNodes, grid);
            neighbourNodes.remove(node);
        }

        return grid;
    }

    @Override
    public NodeGrid generateSmallMaze() {
        return generatePrimMaze(NodeGridGenerator.generateSmallGrid());
    }

    @Override
    public NodeGrid generateMediumMaze() {
        return generatePrimMaze(NodeGridGenerator.generateMediumGrid());
    }

    @Override
    public NodeGrid generateLargeMaze() {
        return generatePrimMaze(NodeGridGenerator.generateLargeGrid());
    }

    //Test-main
    public static void main(String[] args) {
        nodeGridPrinter.printGrid(new PrimNodeMazeGenerator().generateSmallMaze());
    }
}
