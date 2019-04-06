package test;

import jonahshader.gridframework.Tile;
import jonahshader.gridframework.grid.Grid;
import jonahshader.gridframework.grid.gridpart.Layer;

public class MainTest {
    private Grid grid;

    public void execute() {
        grid = new Grid(24, 16, "Main grid");
        System.out.println("Test");

        print();
    }

    public void print() {
        StringBuilder output = new StringBuilder();
        Layer<Tile> layer = grid.getlayer();
        for (int y = layer.getyMin(); y < layer.getyMax(); y++) {
            for (int x = layer.getxMin(); x < layer.getxMax(); x++) {
                output.append((layer.get(x, y) == null) ? "_" : "O");
                System.out.println("1");
                //TODO: this is not printing. size is 0 for some reason
            }
            output.append("\n");
        }
        System.out.print(output);
    }

    public static void main(String[] args) {
        new MainTest().execute();
    }
}
