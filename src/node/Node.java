package node;

import MyArrayList.MyArrayList;

public class Node {
    
    private MyArrayList<Node> connectedNodes;
    private boolean inMaze;
    private int row;
    private int column;
    private MyArrayList<Character> inMazeNeighbours;

    public Node() {
        this.row = -1;
        this.column = -1;
        inMazeNeighbours = new MyArrayList<>();
        this.connectedNodes = new MyArrayList<>();
        this.inMaze = false;
    }

    public Node(int row, int column) {
        this.row = row;
        this.column = column;
        inMazeNeighbours = new MyArrayList<>();
        this.connectedNodes = new MyArrayList<>();
        this.inMaze = false;
    }
    
    
    
    public boolean isConnectedTo(Node node){
        return connectedNodes.contains(node);
    }
    
    public void oneWayConnectionTo(Node node){
        if(!isConnectedTo(node)) {
            connectedNodes.add(node);
        }
    }
    
    public void twoWayConnectionTo(Node node){
        if(node != null){
            this.oneWayConnectionTo(node);
            node.oneWayConnectionTo(this);
        }
    }

    public MyArrayList<Character> getHasInMazeNeighboursIn() {
        return inMazeNeighbours;
    }

    public void addHasInMazeNeighboursIn(char c) {
        this.inMazeNeighbours.add(c);
    }
    
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isInMaze() {
        return inMaze;
    }

    public void InMaze(boolean inMaze) {
        this.inMaze = inMaze;
        if(inMaze) {
            inMazeNeighbours.removeAll(inMazeNeighbours);
        }
    }
    
    @Override
    public String toString(){
        return (inMazeNeighbours.isEmpty()) ? "•" : "♪";
    }
    
}
