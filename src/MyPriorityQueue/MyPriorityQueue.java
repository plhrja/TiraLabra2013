package MyPriorityQueue;

import MyArrayList.MyArrayList;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;

public class MyPriorityQueue<E extends Comparable> extends AbstractQueue<E> implements Serializable {

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
            if (this.arrayList.get(i).compareTo(e) < 1) {
                this.arrayList.add(i, e);
                return true;
            }
        }
        this.arrayList.add(e);
        return true;

    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public E peek() {
        return this.arrayList.get(0);
    }
}
