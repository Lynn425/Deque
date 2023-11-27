package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> madComparator;

    public MaxArrayDeque(Comparator<T> c) {
        madComparator = c;
    }
    public T max() {
        T ret = super.get(0);
        for (int i = 1; i < super.size(); i++) {
            if (madComparator.compare(super.get(i), ret) >= 0) {
                ret = super.get(i);
            }
        }
        return ret;
    }

    public T max(Comparator<T> c) {
        T ret = super.get(0);
        for (int i = 1; i < super.size(); i++) {
            if (c.compare(super.get(i), ret) >= 0) {
                ret = super.get(i);
            }
        }
        return ret;
    }
}
