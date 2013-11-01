package MyArrayList;

import java.util.ListIterator;
import java.util.NoSuchElementException;


public class MyArrayListIterator<E> implements ListIterator<E> {
    
    private MyArrayList arrayList;
    private int position;
    private int leftBoundary;
    private boolean manipulateable;
    
    public MyArrayListIterator(MyArrayList list, int index){
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
        E elem = null;
        try{
            position++;
            this.manipulateable = true;
            elem = (E) this.arrayList.get(position);
        } catch(NoSuchElementException e){
            position--;
            this.manipulateable = false;
            System.err.println("No more elements to iterate!");
        }
        return elem;
    }

    @Override
    public boolean hasPrevious() {
        return this.position > this.leftBoundary;
    }

    @Override
    public E previous() {
        E elem = null;
        try{
            position--;
            elem = (E) this.arrayList.get(position);
            this.manipulateable = true;
        } catch(NoSuchElementException e){
            position++;
            this.manipulateable = false;
            System.err.println("No more elements to iterate!");
        }
        return elem;
    }

    @Override
    public int nextIndex() {
        return this.position++;
    }

    @Override
    public int previousIndex() {
        return this.position--;
    }

    @Override
    public void remove() {
        if(this.manipulateable){
            this.arrayList.remove(position);
            this.manipulateable = false;
        } else {
            System.err.println("Illegal State!");
        }
    }

    @Override
    public void set(E e) {
        if(this.manipulateable){
            this.arrayList.set(position, e);
        } else {
            System.err.println("Illegal State!");
        }
    }

    @Override
    public void add(E e) {
        if(this.manipulateable){
            this.arrayList.add(position, e);
            this.manipulateable = false;
        } else {
            System.err.println("Illegal State");
        }
    }

}

