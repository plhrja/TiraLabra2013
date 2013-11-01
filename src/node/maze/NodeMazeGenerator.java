package node.maze;

import node.grid.NodeGrid;

public interface NodeMazeGenerator {

    public NodeGrid generateSmallMaze();

    public NodeGrid generateMediumMaze();

    public NodeGrid generateLargeMaze();
}
