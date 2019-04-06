package jonahshader.gridframework;

import jonahshader.gridframework.grid.Grid;

import java.util.ArrayList;

public class World {
    private ArrayList<Grid> grids;

    private int defaultWidth, defaultHeight;

    public World(int defaultWidth, int defaultHeight) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        grids = new ArrayList<>();
    }

    public void addNewGrid(String name) {
        grids.add(new Grid(defaultWidth, defaultHeight, name));
    }



}
