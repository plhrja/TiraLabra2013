package cell;

import MyArrayList.MyArrayList;

/**
 * Implementation of a cell data-structure.
 * @author Easysimulation
 */
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
    
    /**
     * Constructs a new cell object with {@code row} = -1 = {@code column}
     */
    public Cell(){
        this(-1,-1);
    }

    /**
     * Constructs a new cell object with {@code row} and {@code column} defined by the parameters,
     * type defined as solid.
     * @param row the row position of the cell.
     * @param column the column position of the cell
     */
    public Cell(int row, int column) {
        this(row,column,true);
    }
    /**
     * Constructor constructs a new cell object with given parameters <br>
     * {@code row} and {@code column}, i.e. assigning the position of the cell. <br>
     * The {@code solid} parameter defines the cell as solid if the parameter is {@code true}, else as open.
     * @param row the row position of the cell.
     * @param column the column position of the cell
     * @param solid defines the type of the cell.
     */
    public Cell(int row, int column, boolean solid){
        this.row = row;
        this.column = column;
        this.solid = solid;
        this.costToBeginning = Integer.MAX_VALUE;
        this.costToFinish = Integer.MAX_VALUE;
        this.parentCell = null;
        this.visited = false;
        this.inMazeNeighbours = new MyArrayList<>();
        this.inMaze = false;
    }

    /**
     * Returns cell-type (solid or open)
     * @return{@code true} if solid, {@code false} if open.
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Sets the type of the cell according to the {@code boolean} parameter;<br>
     * solid if parameter equals {@code true}, else open.
     * @param isSolid {@code boolean} parameter determining cell-type.
     */
    public void isSolid(boolean isSolid) {
        this.solid = isSolid;
    }

    /**
     * Returns {@code true} if {@code visited} value is true, otherwise {@code false}
     * @return {@code true} if {@code visited} value is true, otherwise {@code false}
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets {@code visited} value according to the {@code boolean} parameter.
     * @param visited {@code boolean} parameter that sets the {@code visited} value.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns the {@code row} value of the cell.
     * @return {@code row} value of the cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * Return the {@code column} value of the cell
     * @return {@code column} value of the cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the assigned parent cell of this cell. If unassigned, returns {@code null}.
     * @return parent cell if assigned, {@code null} otherwise
     */
    public Cell getParentCell() {
        return parentCell;
    }

    /**
     * Assigns the cell given in the parameter as a parent cell to this cell.
     * @param parentCell parent cell to be assigned to this cell.
     */
    public void setParentCell(Cell parentCell) {
        this.parentCell = parentCell;
    }

    /**
     * Returns the {@code costToBeginnig} value. Default value if {@code Integer.max}. <br>
     * This attribute is intended to be used in A*-pathfinding calculations overwritten <br>
     * by a heuristic.
     * @return {@code costToBeginning} value
     */
    public double getCostToBeginning() {
        return costToBeginning;
    }

    /**
     * Sets {@code costToBeginning} value according to the given parameter (by a heuristic).
     * @param distToBeginning {@code costToBeginning} value.
     */
    public void setCostToBeginning(double distToBeginning) {
        this.costToBeginning = distToBeginning;
    }

    /**
     * Returns the {@code costToFinish} value. Default value if {@code Integer.max}. <br>
     * This attribute is intended to be used in A*-pathfinding calculations overwritten <br>
     * by a heuristic.
     * @return {@code costToFinish} value
     */
    public double getCostToFinish() {
        return costToFinish;
    }

    /**
     * Sets {@code costToFinish} value according to the given parameter (by a heuristic).
     * @param distToFinish {@code costToFinish} value.
     */
    public void setCostToFinish(double distToFinish) {
        this.costToFinish = distToFinish;
    }
    
    /**
     * Returns the sum of the {@code costToBeginning} and {@code costToFinish} value, <br>
     * given that both have been reassigned to something less than {@code Integer.max}. <br>
     * Otherwise will {@code Integer.max} be returned.
     * @return
     */
    public double getTotalCost() {
        return (this.getCostToBeginning() == Integer.MAX_VALUE || this.getCostToFinish() == Integer.MAX_VALUE) ?
                Integer.MAX_VALUE : this.getCostToBeginning() + this.getCostToFinish();
    }
    
    /**
     * Returns the {@code boolean} value of {@code inMaze} attribute. Intended to be used <br>
     * when generating mazes.
     * @return the {@code inMaze} value.
     */
    public boolean isInMaze() {
        return inMaze;
    }

    /**
     * Sets the {@code inMaze} value according to the given parameter. Intended to be used <br>
     * when generating mazes. Also removes possible neighboring-cell direction values from <br>
     * {@code inMazeNeighbours}
     * @param inMaze the new {@code inMaze} value.
     */
    public void InMaze(boolean inMaze) {
        this.inMaze = inMaze;
        if(inMaze) {
            inMazeNeighbours.removeAll(inMazeNeighbours);
        }
    }
    
    /**
     * Returns a {@code Character} list that is intended to imply the directions of a neighboring cell.
     * @return {@code Character} list.
     */
    public MyArrayList<Character> getHasInMazeNeighboursIn() {
        return inMazeNeighbours;
    }

    /**
     * Adds a neighboring {@code char} value to the neighbor list.
     * @param c {@code char} value to be added to the neighbor list.
     */
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
}
