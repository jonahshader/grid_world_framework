package jonahshader.gridframework.grid.gridpart;

import java.util.ArrayList;

/**
 * BiDirectionalArrayList is an arraylist that can expand in both directions.
 * It can have positive and negative indexes.
 * @param <T>
 */
public class BiDirectionalArrayList<T> {
    private ArrayList<T> forward, backward;

    public BiDirectionalArrayList() {
        forward = new ArrayList<>();
        backward = new ArrayList<>();
    }

    public void expandForward(int n) {
        while (forward.size() < n)
            forward.add(null);
    }

    public void expandBackward(int n) {
        while (backward.size() < -n - 1)
            backward.add(null);
    }

    public void trim() {
        trimForward();
        trimBackward();
    }

    public int getTotalSize() {
        return forward.size() - backward.size();
    }

    public ArrayList<T> getForward() {
        return forward;
    }

    public ArrayList<T> getBackward() {
        return backward;
    }

    public boolean isInBounds(int n) {
        if (n >= 0) {
            return (n < forward.size());
        } else {
            return (-n - 1 < backward.size());
        }
    }

    public T get(int n) {
        return n >= 0 ? forward.get(n) : backward.get(-n - 1);
    }

    public void set(int n, T toSet) {
        if (n >= 0)
            forward.set(n, toSet);
        else
            backward.set(-n - 1, toSet);
    }

    private void trimForward() {
        int furthestElement = 0;
        for (int i = 0; i < forward.size(); i++)
            if (forward.get(i) != null)
                furthestElement = i;

        forward = (ArrayList<T>) forward.subList(0, furthestElement);
        forward.trimToSize();
    }

    private void trimBackward() {
        int furthestElement = 0;
        for (int i = 0; i < backward.size(); i++)
            if (backward.get(i) != null)
                furthestElement = i;

        backward = (ArrayList<T>) backward.subList(0, furthestElement);
        backward.trimToSize();
    }
}