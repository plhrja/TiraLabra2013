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

public class MPVGUI extends JFrame {

    private static final Dimension JCELL_DIMENSION = new Dimension(8, 8);
    private JCellGrid jCellMaze;
    private CellGrid maze;
    private MazeMenuAdapter menu;
    private SolverPanelAdapter solverPanel;
    private boolean mazeInitialized;

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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static Dimension getJCellDimension() {
        return JCELL_DIMENSION;
    }
    
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

    public JCellGrid getjCellMaze() {
        return jCellMaze;
    }

    public CellGrid getMaze() {
        return maze;
    }
    
    
    //testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MPVGUI();
            }
        });
    }
}
