package gui.components;

import cell.grid.CellGrid;
import cell.maze.CellMazeGenerator;
import cell.maze.DFSCellMazeGenerator;
import cell.maze.PrimCellMazeGenerator;
import gui.MPVGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * An extension of the {@code MazeMenuAdapter} class where the functionalities of the
 * predefined menu items are defined.
 * @author Easysimulation
 * @see JMazeMenu
 * @see MPVGUI
 */
public class JMazeMenu extends MazeMenuAdapter {

    private CellMazeGenerator primGenerator;
    private CellMazeGenerator DFSGenerator;
    private MPVGUI gui;

    /**
     * Constructor constructs a new {@code JMazeMenu} object to the GUI specified in the 
     * parameter field.
     * @param gui the GUI to whoch the {@code JMazeMenu} object is created.
     */
    public JMazeMenu(MPVGUI gui) {
        super();
        this.gui = gui;
        this.primGenerator = new PrimCellMazeGenerator();
        this.DFSGenerator = new DFSCellMazeGenerator();
    }

    @Override
    public ActionListener generatePrimJCellGrid(String option) {
        final String size = option;
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellGrid maze = null;
                switch (size) {
                    case "Small" : maze = primGenerator.generateSmallMaze(); break;
                    case "Medium": maze = primGenerator.generateMediumMaze(); break;
                    case "Large": maze = primGenerator.generateLargeMaze(); break;
                }
                gui.replaceJCellMaze(new JCellGrid(
                                maze,
                                MPVGUI.getJCellDimension()));
            }
        };
    }
    
    @Override
    public ActionListener generateDFSJCellGrid(String option) {
        final String size = option;
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellGrid maze = null;
                switch (size) {
                    case "Small" : maze = DFSGenerator.generateSmallMaze(); break;
                    case "Medium": maze = DFSGenerator.generateMediumMaze(); break;
                    case "Large": maze = DFSGenerator.generateLargeMaze(); break;
                }
                gui.replaceJCellMaze(new JCellGrid(
                                maze,
                                MPVGUI.getJCellDimension()));
            }
        };
    }
    
    @Override
    public ActionListener exitFunction(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
            }
        };
    }
}
