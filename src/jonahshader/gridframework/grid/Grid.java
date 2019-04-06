package jonahshader.gridframework.grid;

import jonahshader.gridframework.Tile;
import jonahshader.gridframework.grid.gridpart.Layer;
import jonahshader.gridframework.grid.gridpart.QueuedArrayList;
import jonahshader.gridframework.grid.gridpart.ScalableLayer;
import jonahshader.gridframework.grid.gridpart.UnscalableLayer;

import java.util.ArrayList;

public class Grid {
    private Layer<Tile> layer;                 // elements organized spatially (for collision detection)
    private QueuedArrayList<Tile> tiles;       // elements organized sequentially (for iterating)
//    private boolean scalable;

    public Grid(int xMin, int xMax, int yMin, int yMax, String name) {
        layer = new ScalableLayer<>(xMin, xMax, yMin, yMax, name);
        tiles = new QueuedArrayList<>();
    }

    public Grid(int width, int height, String name) {
        layer = new UnscalableLayer<>(width, height, name);
        tiles = new QueuedArrayList<>();
    }

    public boolean add(Tile tile) {
        int x = tile.getX();
        int y = tile.getY();

        if (layer.isInBounds(x, y)) {
            if (layer.get(x, y) == null) {
                layer.set(x, y, tile);
                tiles.queueAdd(tile);
                return true;
            }
        } else if (layer.attemptExpand(x, y)) {
            layer.set(x, y, tile);
            tiles.queueAdd(tile);
            return true;
        }
        return false;
    }

    /**
     * @param tile
     * @return true = success
     */
    public boolean remove(Tile tile) {
        if (layer.get(tile.getX(), tile.getY()) == tile) {      // if the tile is where it says it is,
            layer.set(tile.getX(), tile.getY(), null);   // set that position to null
            tiles.queueRemove(tile);                            // queue it for removal from the sequential array
            return true;                                        // success
        } else if (tiles.getArrayList().contains(tile)) {       // the tile isn't where it says it is, but if it is in the sequential array,
            tiles.queueRemove(tile);                            // queue it for removal from the sequential array
            //TODO: maybe I should report a lost tile here? Although a lost tile would indicate a problem with the framework
            return true;                                        // success
        }
        return false;                                           // fail
    }

    // TODO: make these methods:

    public ArrayList<Tile> getTiles() {
        return tiles.getArrayList();
    }

    /**
     * update does not call any methods on the tiles.
     * update just updates the QueuedArrayList to add/remove tiles
     */
    public void update() {
        tiles.update();
    }

    public Layer<Tile> getlayer() {
        return layer;
    }
}
