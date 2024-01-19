package com.abyiber.familytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.abyiber.familytree.model.Person;
import com.abyiber.familytree.model.Array;

public class ArrayTest {

    private Array<Person> array;

    @BeforeEach
    public void setUp() {
        array = new Array<Person>();
    }

    @Test
    public void testAddAndGetSizeWithPerson() {
        assertEquals(0, array.getSize());
        Person person1 = new Person("Alice", "Mother1", "Father1");
        Person person2 = new Person("Bob", "Mother2", "Father2");
        array.add(person1);
        assertEquals(1, array.getSize());
        array.add(person2);
        assertEquals(2, array.getSize());
    }

    @Test
    public void testGetWithPerson() {
        Person person1 = new Person("Alice", "Mother1", "Father1");
        Person person2 = new Person("Bob", "Mother2", "Father2");
        array.add(person1);
        array.add(person2);
        assertSame(person1, array.get(0));
        assertSame(person2, array.get(1));
    }

    @Test
    public void testIndexOfWithPerson() {
        Person person1 = new Person("Alice", "Mother1", "Father1");
        Person person2 = new Person("Bob", "Mother2", "Father2");
        array.add(person1);
        array.add(person2);
        assertEquals(0, array.indexOf(person1));
        assertEquals(1, array.indexOf(person2));
        assertEquals(-1, array.indexOf(new Person("Charlie", "Mother3", "Father3")));
    }

    @Test
    public void testContainsWithPerson() {
        Person person1 = new Person("Alice", "Mother1", "Father1");
        array.add(person1);
        assertTrue(array.contains(person1));
        assertFalse(array.contains(new Person("Charlie", "Mother3", "Father3")));
    }

    @Test
    public void testToString() {
        Person person1 = new Person("Alice", "Mother1", "Father1");
        Person person2 = new Person("Bob", "Mother2", "Father2");
        array.add(person1);
        array.add(person2);
        String expected = "[Alice, Bob]";
        assertEquals(expected, array.toString());
    }

}
