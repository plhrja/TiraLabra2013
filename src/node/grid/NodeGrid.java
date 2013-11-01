package node.grid;

import node.Node;

public class NodeGrid {

    private Node[][] grid;
    private int rowLength;
    private int columnLength;

    public NodeGrid(int rowLength, int columnLength) {
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.grid = new Node[rowLength][columnLength];

        for (int i = 0; i < this.rowLength; i++) {
            for (int j = 0; j < this.columnLength; j++) {
                grid[i][j] = new Node(i, j);
            }
        }
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public Node getNode(int row, int column) {
        return grid[row][column];
    }

    public void connect(int row1, int column1, int row2, int column2) {
        this.getNode(row1, column1).twoWayConnectionTo(this.getNode(row2, column2));
    }

    public void connect(Node node1, Node node2) {
        connect(node1.getRow(), node1.getColumn(), node2.getRow(), node2.getColumn());
    }

    public boolean isConnected(int row1, int column1, int row2, int column2) {
        return this.getNode(row1, column1).isConnectedTo(this.getNode(row2, column2))
                && this.getNode(row2, column2).isConnectedTo(this.getNode(row1, column1));
    }
    
    public boolean isConnected(Node node1, Node node2){
        return this.isConnected(node1.getRow(), node1.getColumn(), node2.getRow(), node2.getColumn());
    }
}
