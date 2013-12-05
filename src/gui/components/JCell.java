package gui.components;

import cell.Cell;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * {@code JCell} is a graphical representation of a {@code Cell} object. It's essentially a colored
 * {@code JLabel} component with coloring according to its type
 * @author Easysimulation
 * @see Cell
 * @see JLabel
 * @see JCellGrid
 */
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

    /**
     * Constructor that constructs a new {@code JCell} component that is intended to be used
     * as a visual representation of a {@code Cell} object. The initial state of the
     * {@code JCell} object is closed (which should resemble the type of the matching {@code Cell} object).
     * @param row the row position of th
     * @param column
     */
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

    /**
     * Returns the "primitive" type of the {@code JCell} object (if open {@code true}, else {@code false}).
     * @return the type of the {@code JCell} object.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Returns {@code true} if this current {@code JCell} object is assigned as a start-cell. This is used 
     * when solving paths in a {@code JCellGrid}.
     * @return {@code true} if this {@code JCell} object is a start-cell, {@code false} otherwise.
     */
    public boolean isStart() {
        return start;
    }

    /**
     * Returns {@code true} if this current {@code JCell} object is assigned as a finish-cell. This is used 
     * when solving paths in a {@code JCellGrid}.
     * @return {@code true} if this {@code JCell} object is a finish-cell, {@code false} otherwise.
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * Returns {@code true} if this current {@code JCell} object is marked as processed. This is used 
     * when solving paths in a {@code JCellGrid}, or more explicitly, this is to note that the solving
     * algorithm has processed this current {@code JCell} object.
     * @return {@code true} if this {@code JCell} object is a processed, {@code false} otherwise.
     * @return {@code true} if this {@code JCell} object is a processed, {@code false} otherwise
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Returns {@code true} if this {@code JCell} object is marked as part of the path (generated
     * by a pathsolving algorithm).
     * @return {@code true} if this {@code JCell} object is a part of a path, {@code false} otherwise.
     */
    public boolean isPartOfPath() {
        return partOfPath;
    }
    
    /**
     * This method sets the current {@code JCell} object to an open {@code JCell} object,
     * i.e. setting the background color to white and makes it editable.
     * @see JCellGrid
     * @see JCellMouseListener
     */
    public void open() {
        this.setBackground(OPEN);
        this.setBorder(null);
        this.open = true;
        this.start = false;
        this.finish = false;
        this.processed = false;
        this.partOfPath = false;
    }

    /**
     * This method sets the current {@code JCell} object to a closed {@code JCell} object,
     * i.e. setting the background color to grey and makes it non-editable.
     * @see JCellGrid
     * @see JCellMouseListener
     */
    public void close() {
        this.setBackground(CLOSED);
        this.setBorder(CLOSED_BORDER);
        this.open = false;
        this.start = false;
        this.finish = false;
        this.processed = false;
    }

    /**
     * This method marks the current {@code JCell} object as a startpoint {@code JCell} object,
     * i.e. setting the background color to green and marking it as a startingpoint.
     * @see JCellGrid
     * @see JCellMouseListener
     * @see JMazeWorker
     */
    public void setToStart() {
        if (open) {
            this.setBackground(START);
            this.start = true;
            this.finish = false;
        }
    }

    /**
     * This method marks the current {@code JCell} object as a finishingpoint {@code JCell} object,
     * i.e. setting the background color to red and marking it as a finishingpoint.
     * @see JCellGrid
     * @see JCellMouseListener
     * @see JMazeWorker
     */
    public void setToFinish() {
        if (open) {
            this.setBackground(FINISH);
            this.finish = true;
            this.start = false;
        }
    }
    
    /**
     * This method marks the current {@code JCell} object as a processed {@code JCell} object,
     * i.e. setting the background color to [180, 180, 249] (in RGB) and marking it as processed
     * (by the pathsolving algorithm).
     * @see JCellGrid
     * @see JCellMouseListener
     * @see JMazeWorker
     */
    public void processed(){
        this.setBackground(PROCESSED);
        this.processed = true;
    }
    
    /**
     * This method marks the current {@code JCell} object as a part of a path {@code JCell} object,
     * i.e. setting the background color to [80, 80, 249] (in RGB) and marking it as a part of a path generated
     * by a pathsolving algorithm.
     * @see JCellGrid
     * @see JCellMouseListener
     * @see JMazeWorker
     */
    public void setToPath(){
        this.setBackground(PATH);
        this.partOfPath = true;
    }

    /**
     * Returns the row position of this {@code JCell} object.
     * @return the row position of this {@code JCell} object.
     */
    public int getRow() {
        return row;
    }

    /**
     * Return the column position of this {@code JCell} object.
     * @return the row position of this {@code JCell} object.
     */
    public int getColumn() {
        return column;
    }
}
