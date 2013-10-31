package MyArrayList;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements Serializable, Collection<E>, List<E>, Iterable<E>{
    
    private Object[] array;
    private int capacity;
    private int size;
    
    public MyArrayList(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.array = new Object[capacity];
    }
    
    public MyArrayList(){
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
        for (Object obj : this.array) {
            if(o.equals(obj)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if(a.length < this.size){
            a = (T[]) new Object[this.size()];
        }
        for (int i = 0; i < this.size; i++) {
            a[i] = (T) this.array[i];
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if(this.size == this.array.length){
            this.array = copyAndEnlarge(this.array);
        }
        this.array[this.size] = e;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!this.contains(o)){
            return false;
        }
        shiftLeft(this.array, this.indexOf(o));
        this.size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if(!this.contains(obj)){
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
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean altered = false;
        for (Object obj : c) {
            if(this.remove((E) obj)) {
                altered = true;
            }
        }
        return altered;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean altered = false;
        for (Object obj : this.array) {
            if(!c.contains(obj)){
                this.remove((E) obj);
                altered = true;
            }
        }
        return altered;
    }

    @Override
    public void clear() {
        this.array = new Object[this.capacity];
        this.size = 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        return (E) this.array[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        if(!indexInsideBounds(index)){
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        E atIndex = this.get(index);
        remove(atIndex);
        return atIndex;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if(this.array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        MyArrayList<String> ar = new MyArrayList<>(10);
        ar.add("asdasd");
        ar.add("kikkeli");
        ar.add("ahahahahaha");
        System.out.println(ar.get(1));
        ar.remove("asdasd");
        System.out.println(ar.get(1));
    }

    private Object[] copyAndEnlarge(Object[] array) {
        
        Object[] newArray = new Object[array.length + this.capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        
        return newArray;
    }

    private void shiftLeft(Object[] array, int indexOf) {
        for (int i = indexOf; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    private boolean indexInsideBounds(int index) {
        return index > -1 && index < this.size;
    }

}
