package MyArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyArrayListTest {

    public MyArrayListTest() {
    }
    
    private MyArrayList<String> stringList;
    private MyArrayList<Integer> integerList;
    private static final int CAPACITY = 5;
    
    @Before
    public void setup(){
        stringList = new MyArrayList<>(CAPACITY);
        integerList = new MyArrayList<>(CAPACITY);
    }

    @Test
    public void testAddingAndGettingValues(){
        stringList.add("first");
        integerList.add(1);
        
        String s = stringList.get(0);
        int i = integerList.get(0);
        
        assertEquals("First element of String type list is \"first\"", "first", s);
        assertEquals("First element of Integer type list is 1", 1, i);
        
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void gettingOverBoundaryThrowsException(){
        stringList.get(CAPACITY);
    }
    
    @Test
    public void TestIsEmptyMethod(){
        assertEquals(true, stringList.isEmpty());
    }
    
    @Test
    public void testSizeAndAddingAndGettingOverBoundary(){
        assertEquals("Initial String list size 0", 0, stringList.size());
        assertEquals("Initial Integer list size 0", 0, integerList.size());
        
        for (int i = 0; i < 100; i++) {
            stringList.add("foo" + i);
            integerList.add(i);
        }
        
        String s = stringList.get(99);
        int i = integerList.get(99);
        
        assertEquals("Adding to String type list succeeded", 100, stringList.size());
        assertEquals("Adding to Integer type list succeeded", 100, integerList.size());
        assertEquals("Added String correctly over capacity", ("foo" + 99), s);
        assertEquals("Added Integer correctly over capacity", 99, i);
    }
    
    @Test
    public void testConatinsMethodWithBothTrueAndFalseValues(){
        initStringListWithValues();
        initIntegerListWithValues();
        
        assertEquals("Found String when searching for correct value", true, stringList.contains("second"));
        assertEquals("Found Integer when searching for correct value", true, integerList.contains(2));
        
        assertEquals("Did not find String when searching for incorrect value", false, stringList.contains("should not exist..."));
        assertEquals("Did not find Integer when searching for incorrect value", false, integerList.contains(666));
    }
    
    @Test
    public void testToArrayMethodWithoutDestination(){
        Object[] intArray = integerList.toArray();
        Object[] stringArray = stringList.toArray();
        assertArrayEquals("Empty String list -> empty array", new Object[]{}, stringArray);
        assertArrayEquals("Empty Integer list -> empty array", new Object[]{}, intArray);
        
        initStringListWithValues();
        initIntegerListWithValues();
        
        intArray = integerList.toArray();
        stringArray = stringList.toArray();
        
        assertArrayEquals(new Object[]{1,2,3}, intArray);
        assertArrayEquals(new Object[]{"first", "second", "third"}, stringArray);
    }

    @Test
    public void testToArrayMethodWithDestination(){
        Integer[] dummyIntArray = new Integer[0];
        String[] dummyStringArray = new String[0];
        assertArrayEquals("Empty String list -> empty array", dummyStringArray, stringList.toArray(dummyStringArray));
        assertArrayEquals("Empty Integer list -> empty array", dummyIntArray, integerList.toArray(dummyIntArray));
        
        initStringListWithValues();
        initIntegerListWithValues();
        
        String[] stringArray = stringList.toArray(dummyStringArray);
        Integer[] intArray = integerList.toArray(dummyIntArray);
        
        assertArrayEquals(new Integer[]{1,2,3}, intArray);
        assertArrayEquals(new String[]{"first", "second", "third"}, stringArray);
        
    }
    
    @Test
    public void testRemoveMethodWithObjectParam(){
        initStringListWithValues();
        initIntegerListWithValues();
        
        stringList.remove("second");
        integerList.remove((Integer) 2);
        
        assertArrayEquals(new String[]{"first", "third"}, stringList.toArray());
        assertArrayEquals(new Integer[]{1, 3}, integerList.toArray());
        
        assertEquals("Sizes also match", 2, stringList.size());
    }
    
    @Test
    public void testRemoveMethodWithIndexParam(){
        initStringListWithValues();
        initIntegerListWithValues();
        
        stringList.remove(1);
        integerList.remove(1);
        
        assertArrayEquals(new String[]{"first", "third"}, stringList.toArray());
        assertArrayEquals(new Integer[]{1, 3}, integerList.toArray());
    }
    
    @Test
    public void testRemoveAllWithMatchingCollection(){
        initStringListWithValues();
        initIntegerListWithValues();
        
        MyArrayList<String> stringsToBeRemoved = new MyArrayList<>(5);
        MyArrayList<Integer> integersToBeRemoved = new MyArrayList<>(5);
        for (Integer integer : integerList) {
            integersToBeRemoved.add(integer);
        }
        for (String string :stringList) {
            stringsToBeRemoved.add(string);
        }
        boolean stringsRemoved = stringList.removeAll(stringsToBeRemoved);
        boolean integersRemoved = integerList.removeAll(integersToBeRemoved);
        
        assertEquals("All removed -> size = 0", true, stringList.isEmpty());
        assertEquals("All removed -> size = 0", true, integerList.isEmpty());
        
        assertArrayEquals(new String[]{}, stringList.toArray());
        assertArrayEquals(new Integer[]{}, integerList.toArray());
        
        assertEquals(true, stringsRemoved);
        assertEquals(true, integersRemoved);
    }
    
    @Test
    public void testRemoveAllWithNarrowCollection(){
        initIntegerListWithValues();
        
        MyArrayList<Integer> integersToBeRemoved = new MyArrayList<>(5);
        for (int i = 0; i < 2; i++) {
            integersToBeRemoved.add(i + 1);
        }
        
        boolean integersRemoved = integerList.removeAll(integersToBeRemoved);
        
        assertArrayEquals(new Integer[]{3}, integerList.toArray());
        assertEquals(true, integersRemoved);
    }
    
    @Test
    public void testRemoveAllWithEmptyCollection(){
        initIntegerListWithValues();
        
        boolean integersRemoved = integerList.removeAll(new MyArrayList<>(5));
        
        assertArrayEquals(new Integer[]{1,2,3}, integerList.toArray());
        assertEquals(false, integersRemoved);
    }
    
    @Test
    public void testAddALLWhenInsideBoundaryAfterAddition(){
        MyArrayList<Integer> integersToBeAdded = new MyArrayList<>(CAPACITY);
        MyArrayList<String> stringsToBeAdded = new MyArrayList<>(CAPACITY);
        
        integersToBeAdded.add(666);
        integersToBeAdded.add(777);
        stringsToBeAdded.add("fizz");
        stringsToBeAdded.add("buzz");
        
        initIntegerListWithValues();
        initStringListWithValues();
        
        integerList.addAll(integersToBeAdded);
        stringList.addAll(stringsToBeAdded);
        
        assertArrayEquals(new Integer[]{1,2,3,666,777}, integerList.toArray());
        assertArrayEquals(new String[]{"first", "second", "third", "fizz", "buzz"},
                stringList.toArray());
    }
    
    private void initStringListWithValues() {
        stringList = new MyArrayList<>(CAPACITY);
        stringList.add("first");
        stringList.add("second");
        stringList.add("third");
    }

    private void initIntegerListWithValues() {
        integerList = new MyArrayList<>(CAPACITY);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
    }
    
    
}