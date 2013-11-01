package node.grid;

public class nodeGridPrinter {

    public static void printGrid(NodeGrid grid) {
        for (int i = 0; i < grid.getRowLength() * 2; i++) {
            for (int j = 0; j < grid.getColumnLength(); j++) {
                int iScaled = i / 2;
                if (i % 2 == 0) {
                    System.out.print(grid.getNode(iScaled, j));
                    System.out.print((j < grid.getColumnLength() - 1
                            && grid.isConnected(iScaled, j, iScaled, j + 1) ? " - " : "   "));
                } else {
                    System.out.print((iScaled < grid.getRowLength() - 1
                            && grid.isConnected(iScaled, j, iScaled + 1, j) ? "|   " : "    "));
                }
            }
            System.out.println();
        }
    }

    //print-testing
    public static void main(String[] args) {
        NodeGrid grid = NodeGridGenerator.generateSmallGrid();
        for (int i = 0; i < grid.getRowLength() - 1; i++) {
            grid.connect(i, i, i, i + 1);
            grid.connect(i, i + 1, i + 1, i + 1);
            grid.connect(i, 14, i+1, 14);
            grid.connect(14, i, 14, i+1);
        }
        printGrid(grid);
    }
}
