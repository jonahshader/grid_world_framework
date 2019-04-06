package test;

import jonahshader.gridframework.Tile;
import jonahshader.gridframework.grid.Grid;
import jonahshader.gridframework.grid.gridpart.Layer;

import java.io.IOException;

public class MainTest {
    private Grid grid;

    public void execute() {
        grid = new Grid(24, 16,  "Main grid");
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * 24);
            int y = (int) (Math.random() * 16);

            Tile newTile = new Tile(x, y, grid.getLayer());
            if (!grid.add(newTile)) {
                System.out.println("Tile " + i + " overlapped and could not be created.");
            }
        }

        while (true) {
            run();
        }
    }

    public void print() {
        StringBuilder output = new StringBuilder();
        Layer<Tile> layer = grid.getLayer();
        for (int y = layer.getyMin(); y < layer.getyMax(); y++) {
            for (int x = layer.getxMin(); x < layer.getxMax(); x++) {
                output.append((layer.get(x, y) == null) ? " " : "O");
            }
            output.append("\n");
        }
        System.out.println(output);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (Tile tile : grid.getTiles()) {
            tile.run();
        }
        grid.update();
        grid.remove(grid.getTiles().get(0));
        for (int x = 0; x < grid.getLayer().getWidth(); x++) {
            grid.add(new Tile(x, 0, grid.getLayer()));
        }
        grid.update();
        print();
    }

    public static void main(String[] args) {
        new MainTest().execute();
    }
}
