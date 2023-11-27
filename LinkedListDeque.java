import java.util.ArrayList;
import java.util.List;


public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node pre;
        private T item;
        private Node next;
        private Node(Node p, T i, Node n) {
            pre = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            sentinel = new Node(null, x, null);
            sentinel.pre = sentinel;
            sentinel.next = sentinel;
        } else {
            Node p = new Node(sentinel.pre, x, sentinel);
            sentinel.pre = p;
            sentinel = p;
            p.pre.next = p;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            sentinel = new Node(null, x, null);
            sentinel.pre = sentinel;
            sentinel.next = sentinel;
        } else {
            Node p = new Node(sentinel.pre, x, sentinel);
            sentinel.pre.next = p;
            sentinel.pre = p;
        }
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (sentinel.item != null && size > 0) {
            Node p = sentinel;
            while (p.next != sentinel) {
                returnList.add(p.item);
                p = p.next;
            }
            returnList.add(p.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return (sentinel.item == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0 || sentinel.item == null) {
            return null;
        } else {
            T ret = sentinel.item;
            Node p = sentinel;
            Node n = sentinel.next;
            sentinel = n;
            p.next.pre = p.pre;
            p.pre.next = p.next;
            size--;
            if (size == 0) {
                sentinel.item = null;
            }
            return ret;
        }
    }

    @Override
    public T removeLast() {
        Node p = sentinel;
        T ret = p.pre.item;
        if (size == 0 || p.item == null) {
            return null;
        } else {
            p.pre.pre.next = p;
            p.pre = p.pre.pre;
            size--;
            if (size == 0) {
                sentinel.item = null;
            }
            return ret;
        }
    }

    @Override
    public T get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        } else {
            Node p = sentinel;
            for (int i = 0; i <= index - 1; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        return rec(index, 0, sentinel);
    }
    private T rec(int index, int now, Node n) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        } else if (now == index) {
            return n.item;
        } else {
            return rec(index, now + 1, n.next);
        }
    }
}
