package node.maze;

import MyArrayList.MyArrayList;
import java.util.Random;
import node.Node;
import node.grid.NodeGrid;

public class MazeTools {

    public static void addNeighboursToCoordinates(int row, int col, MyArrayList<Node> neighbourNodes, NodeGrid grid) {
        if (row + 1 < grid.getRowLength() && !grid.getNode(row + 1, col).isInMaze() &&
                !neighbourNodes.contains(grid.getNode(row + 1, col))) {
            neighbourNodes.add(grid.getNode(row + 1, col));
            grid.getNode(row + 1, col).addHasInMazeNeighboursIn('N');
        }
        if (col + 1 < grid.getColumnLength() && !grid.getNode(row, col + 1).isInMaze() &&
                !neighbourNodes.contains(grid.getNode(row, col + 1))) {
            neighbourNodes.add(grid.getNode(row, col + 1));
            grid.getNode(row, col + 1).addHasInMazeNeighboursIn('W');
        }
        if (row - 1 > -1 && !grid.getNode(row - 1, col).isInMaze() &&
                !neighbourNodes.contains(grid.getNode(row - 1, col))) {
            neighbourNodes.add(grid.getNode(row - 1, col));
            grid.getNode(row - 1, col).addHasInMazeNeighboursIn('S');
        }
        if (col - 1 > -1 && !grid.getNode(row, col - 1).isInMaze() &&
                !neighbourNodes.contains(grid.getNode(row, col - 1))) {
            neighbourNodes.add(grid.getNode(row, col - 1));
            grid.getNode(row, col - 1).addHasInMazeNeighboursIn('E');
        }
    }
    
    public static void connectNeighbourToMaze(Node node, NodeGrid grid) {
        char direction = node.getHasInMazeNeighboursIn().get(new Random().nextInt(node.getHasInMazeNeighboursIn().size()));
        int row = node.getRow();
        int column = node.getColumn();
        switch (direction) {
            case 'N':
                grid.connect(row, column, row - 1, column);
                grid.getNode(row, column).InMaze(true);
                break;
            case 'W':
                grid.connect(row, column, row, column - 1);
                grid.getNode(row, column).InMaze(true);
                break;
            case 'S':
                grid.connect(row, column, row + 1, column);
                grid.getNode(row, column).InMaze(true);
                break;
            case 'E':
                grid.connect(row, column, row, column + 1);
                grid.getNode(row, column).InMaze(true);
                break;
        }
    }

    
}
