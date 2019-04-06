package jonahshader.gridframework;

import jonahshader.gridframework.grid.gridpart.Layer;

import java.awt.*;

public class Tile {
    private Layer<Tile> containingLayer;    // the spacial layer that contains this
    private int x, y;

    public Tile(int x, int y, Layer<Tile> containingLayer) {
        this.x = x;
        this.y = y;
        this.containingLayer = containingLayer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * this methid checks if the desired location is within bounds,
     * then it checks if there is a tile there.
     * if it is null, move there.
     * expands the layer if possible and necessary.
     *
     * @param newX new x location
     * @param newY new y location
     * @return true = successful
     */
    public boolean move(int newX, int newY) {
        if (containingLayer.isInBounds(newX, newY)) {           // if the desired location is within bounds,
            if (containingLayer.get(newX, newY) == null) {      // if the desired location is null,
                containingLayer.set(newX, newY, this);   // set the desired location to this
                containingLayer.set(x, y, null);         // set the current location to null
                x = newX;                                       // update location to desired location
                y = newY;
                return true;                                    // success
            }
        } else if (containingLayer.attemptExpand(newX, newY)) { // if layer expansion was successful,
            containingLayer.set(newX, newY, this);       // set the desired location to this
            containingLayer.set(x, y, null);             // set the current location to null
            x = newX;                                           // update location to desired location
            y = newY;
            return true;                                        // success
        }
        return false;                                           // fail
    }

    public Color getColor() {
        return null;
    }

    public void run() {
        move(x + (Math.random() > 0.5 ? 1 : -1), y + (Math.random() > 0.5 ? 1 : 0));
    }
}
