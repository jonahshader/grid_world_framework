package jonahshader.gridframework.grid.gridpart;

public class UnscalableLayer<T> implements Layer<T> {
    private ArrayList2D<T> array;
    private final String name;

    public UnscalableLayer(int width, int height, String name) {
        this.name = name;
        array = new ArrayList2D<>(width, height);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void set(int x, int y, T element) {
        array.set(x, y, element);
    }

    @Override
    public T get(int x, int y) {
        return array.get(x, y);
    }

    @Override
    public int getWidth() {
        return array.getWidth();
    }

    @Override
    public int getHeight() {
        return array.getHeight();
    }

    @Override
    public int getxMin() {
        return 0;
    }

    @Override
    public int getxMax() {
        return getWidth();
    }

    @Override
    public int getyMin() {
        return 0;
    }

    @Override
    public int getyMax() {
        return getHeight();
    }

    @Override
    public boolean isInBounds(int x, int y) {
        return array.isInBounds(x, y);
    }

    @Override
    public boolean attemptExpand(int x, int y) {
        return false;
    }
}
