package com.abyiber.familytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.abyiber.familytree.model.Person;
import com.abyiber.familytree.model.HashMap;

@SuppressWarnings("unchecked")
public class HashMapTest {

    @SuppressWarnings("rawtypes")
    private HashMap hashMap;

    @BeforeEach
    public void setUp() {
        hashMap = new HashMap<>();
    }

    @Test
    public void testPutAndGet() {
        Person person1 = new Person("Alice", "MotherAlice", "FatherAlice");
        Person person2 = new Person("Bob", "MotherBob", "FatherBob");

        hashMap.put(person1.getName(), person1);
        hashMap.put(person2.getName(), person2);

        assertSame(person1, hashMap.get(person1.getName()));
        assertSame(person2, hashMap.get(person2.getName()));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 20; i++) {
            hashMap.put("Person" + i, new Person("Person" + i, "Mother" + i, "Father" + i));
        }

        for (int i = 0; i < 20; i++) {
            assertNotNull(hashMap.get("Person" + i));
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashMap.isEmpty());
        hashMap.put("Alice", new Person("Alice", "MotherAlice", "FatherAlice"));
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        assertFalse(hashMap.containsKey("Alice"));
        hashMap.put("Alice", new Person("Alice", "MotherAlice", "FatherAlice"));
        assertTrue(hashMap.containsKey("Alice"));
    }

    @Test
    public void testSize() {
        assertEquals(0, hashMap.size());
        hashMap.put("Alice", new Person("Alice", "MotherAlice", "FatherAlice"));
        assertEquals(1, hashMap.size());
        hashMap.put("Bob", new Person("Bob", "MotherBob", "FatherBob"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testToString() {
        hashMap.put("Alice", new Person("Alice", "MotherAlice", "FatherAlice"));
        hashMap.put("Bob", new Person("Bob", "MotherBob", "FatherBob"));
        assertEquals("{Alice: Alice, Bob: Bob}", hashMap.toString());
    }
}
