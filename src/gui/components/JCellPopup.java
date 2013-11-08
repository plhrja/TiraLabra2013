package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JCellPopup extends PopupAdapter{
    
    public JCellPopup(JCell cell, JCellGrid grid){
        super(cell, grid);
    }
    
    @Override
    public ActionListener setStartFunction(){
        final JCellGrid grid = this.getGrid();
        final JCell cell = this.getCell();
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (grid.getStart() != null) {
                    grid.getStart().open();
                }
                cell.setToStart();
                grid.setStart(cell);
            
            }
        };
    }
    
    @Override
    public ActionListener setFinishFunction(){
        final JCellGrid grid = this.getGrid();
        final JCell cell = this.getCell();
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (grid.getFinish() != null) {
                    grid.getFinish().open();
                }
                cell.setToFinish();
                grid.setFinish(cell);
            }
        };
    }
}
