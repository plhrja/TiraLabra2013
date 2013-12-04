package gui;

import cell.grid.CellGrid;
import gui.components.JCellGrid;
import gui.components.JMazeMenu;
import gui.components.JSolverPanel;
import gui.components.MazeMenuAdapter;
import gui.components.SolverPanelAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A {@code JFrame} object that compiles all the GUI components from the
 * {@code gui.components} package. Also the main class of this project.
 * @author rsirvio
 * @see JFrame
 */
public class MPVGUI extends JFrame {

    private static final Dimension JCELL_DIMENSION = new Dimension(8, 8);
    private JCellGrid jCellMaze;
    private CellGrid maze;
    private MazeMenuAdapter menu;
    private SolverPanelAdapter solverPanel;
    private boolean mazeInitialized;

    /**
     * Constructor builds the GUI.
     */
    public MPVGUI() {
        this.setLayout(new BorderLayout());

        this.jCellMaze = null;
        this.maze = null;
        this.mazeInitialized = false;

        this.solverPanel = new JSolverPanel(this);
        this.menu = new JMazeMenu(this);

        this.add("South", this.solverPanel);
        this.setJMenuBar(this.menu);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Returns the dimension of the individual {@code JCell} objects in the
     * {@code JCellGrid} object that represents a underlying maze.
     * @return the dimension of each {@code JCell} object.
     */
    public static Dimension getJCellDimension() {
        return JCELL_DIMENSION;
    }
    
    /**
     * Replaces the current {@code JCellGrid} object that represents the underlying maze
     * by another defined in the parameter.
     * @param maze the {@code JCellGrid} that replaces the current representation of a maze.
     * @see JCellGrid
     */
    public void replaceJCellMaze(JCellGrid maze) {
        if(this.mazeInitialized){
        this.remove(jCellMaze);
        }
        this.jCellMaze = maze;
        this.maze = maze.getGrid();
        this.add("North", jCellMaze);
        this.pack();
        this.setLocationRelativeTo(null);
        this.mazeInitialized = true;
    }

    /**
     * Returns the {@code SolverPanelAdapter} object attached to this frame.
     * @return the {@code SolverPanelAdapter} object attached to this maze.
     */
    public SolverPanelAdapter getSolverPanel() {
        return solverPanel;
    }

    /**
     * Returns the current {@code JCellGrid} representation of the underlying maze.
     * @return the {@code JCellGrid} representation of the underlying maze
     * @see JCellGrid
     */
    public JCellGrid getjCellMaze() {
        return jCellMaze;
    }

    /**
     * Returns the underlying maze (a {@code CellGrid} object)
     * @return the underlying maze.
     */
    public CellGrid getMaze() {
        return maze;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MPVGUI();
            }
        });
    }
}
