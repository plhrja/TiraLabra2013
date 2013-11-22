package node.maze;

import MyPriorityQueue.MyPriorityQueue;
import heuristic.Heuristic;

public class AStar {
    
    private MyPriorityQueue queue;
    private Heuristic heuristic;
    
    public AStar(Heuristic heuristic){
        this.heuristic = heuristic;
    }
    
    
    
}
