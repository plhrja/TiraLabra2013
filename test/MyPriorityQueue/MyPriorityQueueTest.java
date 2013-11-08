package MyPriorityQueue;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyPriorityQueueTest {

    public MyPriorityQueueTest() {
    }
    
    private MyPriorityQueue<Integer> queue;

    @Before
    public void setUp() {
        this.queue = new MyPriorityQueue<>();
        for (int i = 0; i < 5; i++) {
            this.queue.add((5 - i) * 2);
        }
    }

    
    @Test
    public void elementsInQueueAreOrdered(){
        assertArrayEquals(new Integer[]{2, 4, 6, 8, 10}, queue.toArray());
    }
    
    @Test
    public void testAddingValuesToQueue(){
        queue.add(1);
        queue.add(5);
        queue.add(69);
        assertArrayEquals(new Integer[]{1, 2, 4, 5, 6, 8, 10, 69}, queue.toArray());
    }
    
    @Test
    public void pollRemovesFirstAndReturnsFirstValue(){
        int poll = queue.poll();
        assertEquals(2, poll);
        assertArrayEquals("Queue short of poll element", new Integer[]{4, 6, 8, 10}, queue.toArray());
    }
    
    @Test
    public void removeRemovesFirstValue(){
        queue.remove();
        assertArrayEquals(new Integer[]{4, 6, 8, 10}, queue.toArray());
    }
    
    @Test
    public void removeAllRemovesAll(){
        queue.removeAll(queue);
        assertEquals(true, queue.isEmpty());
    }
    
    @Test
    public void containsFindsCorrectValueAndDoesNotFindIncorrectValue(){
        assertEquals(true, queue.contains(10));
        assertEquals(false, queue.contains(666));
    }
}