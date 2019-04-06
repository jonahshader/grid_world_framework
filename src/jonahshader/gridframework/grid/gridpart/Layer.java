package jonahshader.gridframework.grid.gridpart;

public interface Layer<T> {
    String getName();
    void set(int x, int y, T element);
    T get(int x, int y);
    int getWidth();
    int getHeight();
    int getxMin();
    int getxMax();
    int getyMin();
    int getyMax();
    boolean isInBounds(int x, int y);
    boolean attemptExpand(int x, int y);
}
