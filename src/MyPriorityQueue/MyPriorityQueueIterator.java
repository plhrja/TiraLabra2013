package MyPriorityQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyPriorityQueueIterator<E> implements Iterator<E> {

    private MyPriorityQueue queue;
    
    public MyPriorityQueueIterator(MyPriorityQueue queue){
        this.queue = queue;
    }
    
    @Override
    public boolean hasNext() {
        return this.queue.size() != 0;
    }

    @Override
    public E next() {
        if(!this.hasNext()){
            throw new NoSuchElementException();
        }
        return (E) this.queue.poll();
    }

    @Override
    public void remove() {
        if(!this.hasNext()){
            throw new NoSuchElementException();
        }
        this.queue.poll();
    }

}
