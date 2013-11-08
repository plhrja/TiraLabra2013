package MyPriorityQueue;

import MyArrayList.MyArrayList;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;

public class MyPriorityQueue<E extends Comparable> extends AbstractQueue<E> implements Serializable, Collection<E> {

    private MyArrayList<E> arrayList;

    public MyPriorityQueue(int capacity) {
        this.arrayList = new MyArrayList<>(capacity);
    }

    public MyPriorityQueue() {
        this(11);
    }

    @Override
    public boolean add(E e) {
        for (int i = 0; i < this.arrayList.size(); i++) {
            int compareTo = this.arrayList.get(i).compareTo(e);
            if (compareTo == 1) {
                this.arrayList.add(i, e);
                return true;
            }
        }
        return this.arrayList.add(e);
    }

    @Override
    public boolean contains(Object o) {
        return this.arrayList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyPriorityQueueIterator<>(this.copy());
    }

    @Override
    public int size() {
        return this.arrayList.size();
    }

    @Override
    public boolean offer(E e) {
        return this.add(e);
    }

    @Override
    public E poll() {
        E first = this.arrayList.get(0);
        this.arrayList.remove(first);
        return first;
    }

    @Override
    public boolean remove(Object o) {
        return this.arrayList.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.arrayList.removeAll(c);
    }

    @Override
    public E peek() {
        return this.arrayList.get(0);
    }

    @Override
    public Object[] toArray() {
        return this.arrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.arrayList.toArray(a);
    }

    private MyPriorityQueue copy() {
        MyPriorityQueue<Comparable> copy = new MyPriorityQueue<>();
        for (int i = 0; i < this.size(); i++) {
            copy.add(this.arrayList.get(i));
        }
        return copy;
    }
}
