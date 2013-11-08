package gui.components;

import cell.converter.NodeMazeConverter;
import gui.MPVGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import node.grid.NodeGrid;
import node.maze.DFSNodeMazeGenerator;
import node.maze.NodeMazeGenerator;
import node.maze.PrimNodeMazeGenerator;

public class JMazeMenu extends MazeMenuAdapter {

    private NodeMazeGenerator primGenerator;
    private NodeMazeGenerator DFSGenerator;
    private MPVGUI gui;

    public JMazeMenu(MPVGUI gui) {
        super();
        this.gui = gui;
        this.primGenerator = new PrimNodeMazeGenerator();
        this.DFSGenerator = new DFSNodeMazeGenerator();
    }

    @Override
    public ActionListener generatePrimJCellGrid(String option) {
        final String size = option;
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NodeGrid maze = null;
                switch (size) {
                    case "Small" : maze = primGenerator.generateSmallMaze(); break;
                    case "Medium": maze = primGenerator.generateMediumMaze(); break;
                    case "Large": maze = primGenerator.generateLargeMaze(); break;
                }
                gui.replaceJCellMaze(new JCellGrid(
                                NodeMazeConverter.convertNodeMazeToCellMaze(maze),
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
                NodeGrid maze = null;
                switch (size) {
                    case "Small" : maze = DFSGenerator.generateSmallMaze(); break;
                    case "Medium": maze = DFSGenerator.generateMediumMaze(); break;
                    case "Large": maze = DFSGenerator.generateLargeMaze(); break;
                }
                gui.replaceJCellMaze(new JCellGrid(
                                NodeMazeConverter.convertNodeMazeToCellMaze(maze),
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
