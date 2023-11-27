import java.util.ArrayList;
import java.util.List;


public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resizeUp(int cap) {
        T[] a = (T[]) new Object[cap * 2];
        for (int i = 0; i < size && nextFirst + i + 1 < items.length; i++) {
            a[i] = items[nextFirst + i + 1];
        }
        if (size + nextFirst >= items.length) {
            for (int j = items.length - nextFirst - 1; j < size; j++) {
                a[j] = items[nextLast + j - size];
            }
        }
        items = a;
        nextLast = size;
        nextFirst = a.length - 1;
    }

    private void resizeDown(int cap) {
        T[] a = (T[]) new Object[cap * 2];
        for (int i = 0; i < size && nextFirst + i + 1 < items.length; i++) {
            a[i] = items[nextFirst + i + 1];
        }
        if (size + nextFirst >= items.length) {
            for (int j = items.length - nextFirst - 1; j < size; j++) {
                a[j] = items[nextLast + j - size];
            }
        }
        items = a;
        nextLast = size;
        nextFirst = a.length - 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp(size);
        }
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp(size);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = nextFirst + 1; i < items.length && items[i] != null; i++) {
            returnList.add(items[i]);
        }
        for (int j = 0; j <= nextFirst && items[j] != null; j++) {
            returnList.add(items[j]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            if (size == items.length / 4 && items.length >= 2 * 2 * 2 * 2) {
                resizeDown(size);
            }
            T returnItem;
            if (nextFirst == items.length - 1) {
                returnItem = items[0];
                items[0] = null;
                nextFirst = 0;
            } else {
                returnItem = items[nextFirst + 1];
                items[nextFirst + 1] = null;
                nextFirst += 1;
            }
            size--;
            return returnItem;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            if (size == items.length / 4 && items.length >= 2 * 2 * 2 * 2) {
                resizeDown(size);
            }
            T returnItem;
            if (nextLast == 0) {
                returnItem = items[items.length - 1];
                items[items.length - 1] = null;
                nextLast = items.length - 1;
            } else {
                returnItem = items[nextLast - 1];
                items[nextLast - 1] = null;
                nextLast -= 1;
            }
            size--;
            return returnItem;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else if (index + nextFirst + 1 < items.length) {
            return items[nextFirst + index + 1];
        } else {
            return items[nextFirst + index + 1 - items.length];
        }
    }

}
