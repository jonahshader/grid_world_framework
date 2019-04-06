package jonahshader.gridframework.grid.gridpart;

public class ScalableLayer<T> implements Layer<T> {
    private BiDirectionalArrayList<BiDirectionalArrayList<T>> array;
    private final String name;
    private int xMin, yMin, xMax, yMax;

    public ScalableLayer(int xMin, int xMax, int yMin, int yMax, String name) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.name = name;
        array = new BiDirectionalArrayList<>();

        array.expandForward(yMax);
        array.expandBackward(yMin);

//        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                array.set(y, new BiDirectionalArrayList<>());
                array.get(y).expandBackward(xMin);
                array.get(y).expandForward(xMax);
            }
//        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void set(int x, int y, T element) {
        array.get(y).set(x, element);
    }

    @Override
    public T get(int x, int y) {
        return array.get(y).get(x);
    }

    @Override
    public int getWidth() {
        return array.get(0).getTotalSize();
    }

    @Override
    public int getHeight() {
        return array.getTotalSize();
    }

    @Override
    public int getxMin() {
        return xMin;
    }

    @Override
    public int getyMin() {
        return yMin;
    }

    @Override
    public int getxMax() {
        return xMax;
    }

    @Override
    public int getyMax() {
        return yMax;
    }

    @Override
    public boolean isInBounds(int x, int y) {
        if (array.isInBounds(y)) {
            return array.get(y).isInBounds(x);
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return true = success
     */
    @Override
    public boolean attemptExpand(int x, int y) {
        expand(x, y);
        return true;
    }

    /**
     * reduces size of arrays to non-null bounds
     */
    private void trim() {
        for (BiDirectionalArrayList biDirectionalArrayList : array.getForward())
            biDirectionalArrayList.trim();
        for (BiDirectionalArrayList biDirectionalArrayList : array.getBackward())
            biDirectionalArrayList.trim();
    }

    /**
     * expands dimensions to contain position
     * @param x
     * @param y
     */
    private void expand(int x, int y) {
        array.expandForward(y);
        array.expandBackward(y);

        BiDirectionalArrayList<T> tempSlice = array.get(y);
        tempSlice.expandForward(x);
        tempSlice.expandBackward(x);
    }
}
