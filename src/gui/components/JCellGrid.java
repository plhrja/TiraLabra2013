package gui.components;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;


/**
 * The {@code JCellGrid} object is intended to be used as a visual representation of a
 * {@code CellGrid} object. It is basically a matrix of {@code JCell} objects, to which
 * properties are assigned according to the underlying {@code CellGrid} object.
 * @author Easysimulation
 * @see JCell
 * @see CellGrid
 */
public class JCellGrid extends JPanel{
    
    private JCell[][] cellArray;
    private CellGrid grid;
    private Dimension cellDimension;
    private JCell start;
    private JCell finish;
    
    /**
     * Constructor constructs a new {@code JCellGrid} object and assigns properties of the
     * {@code JCell} objects according to the underlying {@code CellGrid} object.
     * @param grid the underlying {@code CellGrid} object.
     * @param cellDimension the size of the individual {@code JCell} objects.
     */
    public JCellGrid(CellGrid grid, Dimension cellDimension){
        super(new GridLayout(grid.getRowLength(), grid.getColumnLength()));
        this.grid = grid;
        this.cellArray = new JCell[grid.getRowLength()][grid.getColumnLength()];
        this.cellDimension = cellDimension;
        this.start = null;
        this.finish = null;
        
        initCellArray(grid);
        
        constructPanel();
        
        this.setOpaque(true);
        this.setVisible(true);
    }
    
    private void initCellArray(CellGrid grid) {
        for (Cell cell : grid) {
            JCell jCell = new JCell(cell.getRow(), cell.getColumn());
            JCellMouseListener mouseAdapter = new JCellMouseListener(jCell);
            PopupAdapter popup = new JCellPopup(jCell, this);
            
            jCell.addMouseListener(mouseAdapter);
            jCell.setComponentPopupMenu(popup);
            jCell.setPreferredSize(this.cellDimension);
            
            if(!cell.isSolid()){
                jCell.open();
            }
            
            this.cellArray[cell.getRow()][cell.getColumn()] = jCell;
        }
    }

    private void constructPanel() {
        for (int i = 0; i < this.cellArray.length; i++) {
            for (int j = 0; j < this.cellArray[i].length; j++) {
                this.add(this.cellArray[i][j]);
            }
        }
    }

    /**
     * Returns the {@code JCell} object from the matrix that is set as a starting cell for a path.
     * @return the {@code JCell} object set as a starting cell.
     */
    public JCell getStart() {
        return start;
    }

    /**
     * Sets the {@code JCell} object specified in the parameter field as a starting cell of a path.
     * @param start the {@code JCell} object in the matrix that is a starting point of a path.
     */
    public void setStart(JCell start) {
        this.start = start;
    }

    /**
     * Returns the {@code JCell} object from the matrix that is set as a finish cell for a path.
     * @return the {@code JCell} object set as a finish cell.
     */
    public JCell getFinish() {
        return finish;
    }

    /**
     * Sets the {@code JCell} object specified in the parameter field as a finish cell of a path.
     * @param start the {@code JCell} object in the matrix that is a finishing point of a path.
     */
    public void setFinish(JCell finish) {
        this.finish = finish;
    }
    
    /**
     * Returns the {@code JCell} object at the in the parameter field specified position in the matrix.
     * @param row the row position of the {@code JCell} object.
     * @param column the column position of the {@code JCell} object.
     * @return the {@code JCell} at the specified position in the matrix.
     */
    public JCell getJCell(int row, int column){
        return cellArray[row][column];
    }

    /**
     * Returns the underlying {@code CellGrid} structure of the matrix.
     * @return the underlying {@code CellGrid} structure.
     */
    public CellGrid getGrid() {
        return grid;
    }
    
    /**
     * Sets all {@code JCell} objects of the matrix marked as either processed or part of a path back to open.
     */
    public void clean(){
        for(JCell[] jCells : cellArray) {
            for (JCell jCell : jCells) {
                if(jCell.isProcessed() || jCell.isPartOfPath()){
                    jCell.open();
                }
            }
        }
    }
    
    /**
     * Marks all the {@code JCell} objects of the matrix, that resemble the position of each
     * {@code Cell} object given in the list as a parameter, to be part of a path.
     * @param path the list that contains the {@code Cell} objects that represent the path.
     */
    public void paintPath(MyArrayList<Cell> path){
        for (Cell cell : path) {
            this.cellArray[cell.getRow()][cell.getColumn()].setToPath();
        }
    }
    
}
