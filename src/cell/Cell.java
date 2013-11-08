package cell;

import MyPriorityQueue.MyPriorityQueue;
import java.util.Arrays;

public class Cell implements Comparable<Cell>{

    private boolean solid;
    private boolean visited;
    private Cell parentCell;
    private int row;
    private int column;
    private int costToBeginning;
    private int costToFinish;
    
    public Cell(){
        this.row = -1;
        this.column = -1;
        this.solid = true;
        this.costToBeginning = Integer.MAX_VALUE;
        this.costToFinish = Integer.MAX_VALUE;
        this.parentCell = null;
        this.visited = false;
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.solid = true;
        this.costToBeginning = Integer.MAX_VALUE;
        this.costToFinish = Integer.MAX_VALUE;
        this.parentCell = null;
        this.visited = false;
    }

    public Cell(int row, int column, boolean solid) {
        this.row = row;
        this.column = column;
        this.solid = solid;
        this.costToBeginning = Integer.MAX_VALUE;
        this.costToFinish = Integer.MAX_VALUE;
        this.parentCell = null;
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

    public int getCostToBeginning() {
        return costToBeginning;
    }

    public void setCostToBeginning(int distToBeginning) {
        this.costToBeginning = distToBeginning;
    }

    public int getCostToFinish() {
        return costToFinish;
    }

    public void setCostToFinish(int distToFinish) {
        this.costToFinish = distToFinish;
    }

    public int getTotalCost() {
        return (this.getCostToBeginning() == Integer.MAX_VALUE || this.getCostToFinish() == Integer.MAX_VALUE) ?
                Integer.MAX_VALUE : this.getCostToBeginning() + this.getCostToFinish();
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
