package cell.maze.Pathfinder;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.util.Observable;

public abstract class Pathfinder extends Observable{

    public abstract MyArrayList findPath(Cell start, Cell finish, CellGrid grid, int delay);

}
