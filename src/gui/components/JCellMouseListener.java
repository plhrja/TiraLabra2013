package gui.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class JCellMouseListener extends MouseAdapter{

    private static final Border BORDER = BorderFactory.createLineBorder(Color.black); 
    private JCell cell;
    
    JCellMouseListener(JCell cell){
        this.cell = cell;
    }
    
    @Override
    public void mousePressed(MouseEvent ev){
        if(isValid()){
            cell.getComponentPopupMenu().show(ev.getComponent(), ev.getX(), ev.getY());
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent ev){
        if(isValid()){
            cell.setBorder(BORDER);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent ev){
        if(isValid()){
            cell.setBorder(null);
        }
    }

    private boolean isValid() {
        return cell.isOpen() && !cell.isStart() && !cell.isFinish();
    }
    
}
