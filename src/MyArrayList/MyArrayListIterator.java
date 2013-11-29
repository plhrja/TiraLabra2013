package MyArrayList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A {@code ListIterator} implementation to the {@code MyArrayList} list. For documentation <br>
 * info see the {@code ListIterator} documentation!
 * @author Easysimulation
 * @param <E>
 * @see ListIterator
 */
public class MyArrayListIterator<E> implements ListIterator<E> {

    private MyArrayList arrayList;
    private int position;
    private int leftBoundary;
    private boolean manipulateable;

    public MyArrayListIterator(MyArrayList list, int index) {
        this.arrayList = list;
        this.position = index - 1;
        this.leftBoundary = index;
        this.manipulateable = false;
    }

    @Override
    public boolean hasNext() {
        return this.position < this.arrayList.size() - 1;
    }

    @Override
    public E next() {
        if (this.nextIndex() == this.arrayList.size()) {
            throw new NoSuchElementException();
        }
        position++;
        E elem = (E) this.arrayList.get(position);
        this.manipulateable = true;
        return elem;
    }

    @Override
    public boolean hasPrevious() {
        return this.position > this.leftBoundary;
    }

    @Override
    public E previous() {
        if (this.previousIndex() < this.leftBoundary) {
            throw new NoSuchElementException();
        }
        position--;
        E elem = (E) this.arrayList.get(position);
        this.manipulateable = true;
        return elem;
    }

    @Override
    public int nextIndex() {
        return this.position + 1;
    }

    @Override
    public int previousIndex() {
        return this.position - 1;
    }

    @Override
    public void remove() {
        if (!this.manipulateable) {
            throw new IllegalStateException("Illegal state, see documentation!");
        }
        this.arrayList.remove(position);
        this.manipulateable = false;
    }

    @Override
    public void set(E e) {
        if (!this.manipulateable) {
            throw new IllegalStateException("Illegal state, see documentation!");
        }
        this.arrayList.set(position, e);
    }

    @Override
    public void add(E e) {
        if (!this.manipulateable) {
            throw new IllegalStateException("Illegal state, see documentation!");
        }
        this.arrayList.add(position, e);
        this.manipulateable = false;
    }
}