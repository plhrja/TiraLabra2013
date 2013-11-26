package gui.components;

import MyArrayList.MyArrayList;
import cell.Cell;
import cell.grid.CellGrid;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.JPanel;


public class JCellGrid extends JPanel{
    
    private JCell[][] cellArray;
    private CellGrid grid;
    private Dimension cellDimension;
    private JCell start;
    private JCell finish;
    
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

    public JCell getStart() {
        return start;
    }

    public void setStart(JCell start) {
        this.start = start;
    }

    public JCell getFinish() {
        return finish;
    }

    public void setFinish(JCell finish) {
        this.finish = finish;
    }
    
    public JCell getJCell(int row, int column){
        return cellArray[row][column];
    }

    public CellGrid getGrid() {
        return grid;
    }
    
    public void clean(){
        for(JCell[] jCells : cellArray) {
            for (JCell jCell : jCells) {
                if(jCell.isProcessed() || jCell.isPartOfPath()){
                    jCell.open();
                }
            }
        }
    }
    
    public void paintPath(MyArrayList<Cell> path){
        for (Cell cell : path) {
            this.cellArray[cell.getRow()][cell.getColumn()].setToPath();
        }
    }
    
}
