package jonahshader.gridframework.grid.gridpart;

import java.util.ArrayList;

public class ArrayList2D<T> {
    private ArrayList<T> arrayList;
    private int width, height;

    public ArrayList2D(int width, int height) {
        this.width = width;
        this.height = height;
        arrayList = new ArrayList<>();

        // populate array with null
        for (int i = 0; i < width * height; i++) {
            arrayList.add(null);
        }
    }

    public T get(int x, int y) {
        return arrayList.get(dimToIndex(x, y));
    }

    public void set(int x, int y, T element) {
        arrayList.set(dimToIndex(x, y), element);
    }

    public boolean isInBounds(int x, int y) {
        boolean inBounds = arrayList.size() > dimToIndex(x, y);
        if (!(x >= 0 && x < width && y >= 0 && y < height)) {
            inBounds = false;
        }
        return inBounds;
    }

    private int dimToIndex(int x, int y) {
        return (y * width) + x;
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
