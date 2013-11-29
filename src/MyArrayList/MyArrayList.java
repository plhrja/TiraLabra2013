package MyArrayList;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A list implementation replicating the {@code ArrayList} implementation. For documentation <br>
 * info see the original implementation!
 * @author plhrja
 * @param <E>
 * @see ArrayList
 */
public class MyArrayList<E> implements Serializable, List<E>, Iterable<E> {

    private E[] array;
    private int capacity;
    private int size;

    public MyArrayList(int capacity) {
        this.capacity = Math.abs(capacity);
        this.size = 0;
        this.array = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(50);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        o = (E) o;
        for (E e : this.array) {
            if (o.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<>(this, 0);
    }

    @Override
    public Object[] toArray() {
        Object[] arrayToReturn = new Object[this.size];
        System.arraycopy(this.array, 0, arrayToReturn, 0, this.size);
        return arrayToReturn;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] newArray = (this.size == 0)
                ? (T[]) Array.newInstance(a.getClass().getComponentType(), 0) : (a.length < this.size)
                ? (T[]) Array.newInstance(a.getClass().getComponentType(), this.size) : a;

        if (newArray.length == 0) {
            return newArray;
        }
        for (int i = 0; i < this.size; i++) {
            newArray[i] = (T) this.array[i];
        }
        return newArray;
    }

    @Override
    public boolean add(E e) {
        if (this.size == this.array.length) {
            this.array = copyAndEnlarge(this.array);
        }
        this.array[this.size] = e;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!this.contains((E) o)) {
            return false;
        }
        shiftLeft(this.array, this.indexOf((E) o));
        this.size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!this.contains((E) obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            this.add(e);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean altered = false;
        for (Object obj : c) {
            if (this.remove((E)obj)) {
                altered = true;
            }
        }
        return altered;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean altered = false;
        for (Object obj : this.array) {
            if (!c.contains(obj)) {
                this.remove((E) obj);
                altered = true;
            }
        }
        return altered;
    }

    @Override
    public void clear() {
        this.array = (E[]) new Object[this.capacity];
        this.size = 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (this.array.length <= this.size + c.size()) {
            this.array = copyAndEnlarge(this.array, c.size() + this.capacity);
        }
        shiftRight(this.array, index, c.size());
        System.arraycopy(c.toArray(), 0, this.array, index, c.size());
        return !c.isEmpty();
    }

    @Override
    public E get(int index) {
        if (!indexInsideBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        return (E) this.array[index];
    }

    @Override
    public E set(int index, E element) {
        if (!indexInsideBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        E e = (E) this.array[index];
        this.array[index] = element;
        return e;
    }

    @Override
    public void add(int index, E element) {
        if (!indexInsideBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        if(this.size == this.array.length){
            copyAndEnlarge(this.array);
        }
        this.array = copyAndEnlarge(this.array);
        shiftRight(this.array, index, 1);
        this.array[index] = element;
        this.size++;
    }

    @Override
    public E remove(int index) {
        if (!indexInsideBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        E atIndex = this.get(index);
        remove(atIndex);
        return atIndex;
    }

    @Override
    public int indexOf(Object o) {
        o = (E) o;
        for (int i = 0; i < this.size; i++) {
            if (this.get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        o = (E) o;
        for (int i = this.size; i > -1; i--) {
            if (this.get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyArrayListIterator<>(this, 0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyArrayListIterator<>(this, index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        int length = toIndex - fromIndex;
        if (length < 1) {
            return null;
        }
        MyArrayList subList = new MyArrayList(length + capacity);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(this.get(i));
        }
        return subList;
    }

    private E[] copyAndEnlarge(E[] array) {
        return copyAndEnlarge(array, this.capacity);
    }

    private E[] copyAndEnlarge(E[] array, int i) {
        E[] newArray = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length + i);
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
 
    private void shiftLeft(E[] array, int indexOf) {
        for (int i = indexOf; i < this.size - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    private void shiftRight(E[] array, int index, int size) {
        for (int i = this.size-1; i > index - 1; i--) {
            array[i + size] = array[i];

        }
    }

    private boolean indexInsideBounds(int index) {
        return index > -1 && index < this.size;
    }
}
