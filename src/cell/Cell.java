package cell;

import MyArrayList.MyArrayList;
import MyPriorityQueue.MyPriorityQueue;
import java.util.Arrays;

public class Cell implements Comparable<Cell>{

    private boolean solid;
    private boolean visited;
    private Cell parentCell;
    private int row;
    private int column;
    private double costToBeginning;
    private double costToFinish;
    private MyArrayList<Character> inMazeNeighbours;
    private boolean inMaze;
    
    public Cell(){
        this(-1,-1);
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.solid = true;
        this.costToBeginning = Integer.MAX_VALUE;
        this.costToFinish = Integer.MAX_VALUE;
        this.parentCell = null;
        this.visited = false;
        this.inMazeNeighbours = new MyArrayList<>();
        this.inMaze = false;
    }

    public boolean isSolid() {
        return solid;
    }

    public void isSolid(boolean isSolid) {
        this.solid = isSolid;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getParentCell() {
        return parentCell;
    }

    public void setParentCell(Cell parentCell) {
        this.parentCell = parentCell;
    }

    public double getCostToBeginning() {
        return costToBeginning;
    }

    public void setCostToBeginning(double distToBeginning) {
        this.costToBeginning = distToBeginning;
    }

    public double getCostToFinish() {
        return costToFinish;
    }

    public void setCostToFinish(double distToFinish) {
        this.costToFinish = distToFinish;
    }
    
    public double getTotalCost() {
        return (this.getCostToBeginning() == Integer.MAX_VALUE || this.getCostToFinish() == Integer.MAX_VALUE) ?
                Integer.MAX_VALUE : this.getCostToBeginning() + this.getCostToFinish();
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
    
    public MyArrayList<Character> getHasInMazeNeighboursIn() {
        return inMazeNeighbours;
    }

    public void addHasInMazeNeighboursIn(char c) {
        this.inMazeNeighbours.add(c);
    }

    @Override
    public String toString() {
        return this.isSolid() ? "♫" : " ";
    }

    @Override
    public int compareTo(Cell cell) {
        return (this.getTotalCost() > cell.getTotalCost()) ? 1
                : (this.getTotalCost() == cell.getTotalCost()) ? 0 : -1;
    }

    //test-main
    public static void main(String[] args) {
        MyPriorityQueue<Cell> queue = new MyPriorityQueue<>();
        int cellArraySize = 4;
        Cell cell[] = new Cell[cellArraySize];
        
        for (int i = 0; i < cellArraySize; i++) {
            Cell tempCell = new Cell(0,i);
            tempCell.setCostToBeginning(1);
            cell[i] = tempCell;
        }
        
        cell[0].setCostToFinish(3);
        cell[1].setCostToFinish(4);
        cell[2].setCostToFinish(2);
        cell[3].setCostToFinish(1);
        
        queue.addAll(Arrays.asList(cell));
        
        for (int i = 0; i < 4; i++) {
            System.out.println(queue.poll().getColumn());
        }
    }
}
