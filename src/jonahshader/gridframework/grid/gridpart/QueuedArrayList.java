package jonahshader.gridframework.grid.gridpart;

import java.util.ArrayList;

public class QueuedArrayList<T> {
    private ArrayList<T> arrayList, addQueue, removeQueue;

    public QueuedArrayList() {
        arrayList = new ArrayList<>();
        addQueue = new ArrayList<>();
        removeQueue = new ArrayList<>();
    }

    public synchronized void update() {
        if (addQueue.size() > 0) {
            arrayList.addAll(addQueue);
            addQueue.clear();
        }
        if (removeQueue.size() > 0) {
            arrayList.removeAll(removeQueue);
            removeQueue.clear();
        }
    }

    public synchronized void queueAdd(T element) {
        addQueue.add(element);
    }

    public synchronized void queueRemove(T element) {
        removeQueue.add(element);
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

}
