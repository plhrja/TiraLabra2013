package gui.components;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class JCell extends JLabel {

    private static final Color OPEN = Color.white;
    private static final Color CLOSED = new Color(70, 70, 70);
    private static final Color START = new Color(115, 249, 115);
    private static final Color FINISH = new Color(249, 115, 115);
    private static final Color PROCESSED = new Color(180, 180, 249);
    private static final Color PATH = new Color(80, 80, 249);
    private static final Border CLOSED_BORDER = BorderFactory.createLineBorder(Color.black);
    private boolean open;
    private boolean start;
    private boolean finish;
    private boolean processed;
    private boolean partOfPath;
    private int row;
    private int column;

    public JCell(int row, int column) {
        super();
        this.setBackground(CLOSED);
        this.setBorder(CLOSED_BORDER);
        this.open = false;
        this.start = false;
        this.finish = false;
        this.processed = false;
        this.partOfPath = false;
        this.row = row;
        this.column = column;
        this.setOpaque(true);
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isFinish() {
        return finish;
    }

    public boolean isProcessed() {
        return processed;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }
    
    public void open() {
        this.setBackground(OPEN);
        this.setBorder(null);
        this.open = true;
        this.start = false;
        this.finish = false;
        this.processed = false;
        this.partOfPath = false;
    }

    public void close() {
        this.setBackground(CLOSED);
        this.setBorder(CLOSED_BORDER);
        this.open = false;
        this.start = false;
        this.finish = false;
        this.processed = false;
    }

    public void setToStart() {
        if (open) {
            this.setBackground(START);
            this.start = true;
            this.finish = false;
        }
    }

    public void setToFinish() {
        if (open) {
            this.setBackground(FINISH);
            this.finish = true;
            this.start = false;
        }
    }
    
    public void processed(){
        this.setBackground(PROCESSED);
        this.processed = true;
    }
    
    public void setToPath(){
        this.setBackground(PATH);
        this.partOfPath = true;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
