package com.abyiber.familytree.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic array data structure that dynamically resizes when needed.
 * 
 * @param <T> the type of elements stored in the array
 */
public class Array<T> implements Iterable<T> {
    private Object[] items;
    private int size;
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty Array with an initial capacity of 10.
     */
    public Array() {
        items = new Object[INITIAL_SIZE];
        size = 0;
    }

    /**
     * Adds an element to the end of the Array.
     * 
     * @param item the element to add
     */
    public void add(T item) {
        if (size == items.length) {
            resize();
        }
        items[size] = item;
        size++;
    }

    /**
     * Resizes the internal array to accommodate more elements.
     */
    private void resize() {
        Object[] newItems = new Object[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the
     * Array.
     * 
     * @param item the element to search for
     * @return the index of the element, or -1 if not found
     */
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the Array contains the specified element.
     * 
     * @param item the element to check for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    /**
     * Returns the number of elements in the Array.
     * 
     * @return the size of the Array
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the element at the specified index in the Array.
     * 
     * @param index the index of the element to retrieve
     * @return the element at the given index
     * @throws IllegalArgumentException if the index is invalid
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        return (T) items[index];
    }

    /**
     * Iterator implementation to allow for-each loops over the custom Array class.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) items[currentIndex++];
        }
    }

    /**
     * Returns a string representation of the Array.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}