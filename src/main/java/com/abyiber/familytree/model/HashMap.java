package com.abyiber.familytree.model;

/**
 * A custom implementation of a HashMap data structure.
 * 
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values associated with keys in the map
 */
public class HashMap<K, V> {
    private HashMapNode<K, V>[] buckets;
    private int capacity;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /**
     * Constructs an empty HashMap with an initial capacity of 16.
     */
    @SuppressWarnings("unchecked")
    public HashMap() {
        capacity = INITIAL_CAPACITY;
        buckets = (HashMapNode<K, V>[]) new HashMapNode[capacity];
        size = 0;
    }

    /**
     * Returns the number of key-value pairs in the HashMap.
     * 
     * @return the number of elements in the map
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the HashMap is empty.
     * 
     * @return true if the map is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the bucket index for a given key and probe count.
     * 
     * @param key        the key to hash
     * @param probeCount the number of probes (collision resolution)
     * @return the index of the bucket for the key
     */
    private int getBucketIndex(K key, int probeCount) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode + probeCount) % capacity;
    }

    /**
     * Associates the specified value with the specified key in the HashMap.
     * 
     * @param key   the key to associate with the value
     * @param value the value to be associated with the key
     */
    public void put(K key, V value) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null && !buckets[bucketIndex].getKey().equals(key)) {
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }

        if (buckets[bucketIndex] != null && buckets[bucketIndex].getKey().equals(key)) {
            buckets[bucketIndex].setValue(value);
        } else {
            buckets[bucketIndex] = new HashMapNode<>(key, value);
            size++;
        }
    }

    /**
     * Retrieves the value associated with the specified key in the HashMap.
     * 
     * @param key the key to look up
     * @return the value associated with the key, or null if not found
     */
    public V get(K key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey().equals(key)) {
                return buckets[bucketIndex].getValue();
            }
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }
        return null;
    }

    /**
     * Checks if the HashMap contains a specified key.
     * 
     * @param key the key to check for
     * @return true if the key is found in the map, false otherwise
     */
    public boolean containsKey(K key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey().equals(key)) {
                return true;
            }
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }
        return false;
    }

    /**
     * Resizes the hash table when the load factor threshold is exceeded.
     * This method doubles the capacity of the hash table and rehashes all elements.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        HashMapNode<K, V>[] oldBuckets = buckets;
        buckets = (HashMapNode<K, V>[]) new HashMapNode[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (HashMapNode<K, V> node : oldBuckets) {
            if (node != null) {
                put(node.getKey(), node.getValue());
            }
        }
    }

    /**
     * Prints the key-value pairs in the HashMap.
     */
    public void print() {
        for (HashMapNode<K, V> node : buckets) {
            if (node != null) {
                System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
            }
        }
    }

    /**
     * Returns a string representation of the HashMap.
     * 
     * @return a string representation of the key-value pairs in the map
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean isFirst = true;
        for (HashMapNode<K, V> node : buckets) {
            if (node != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(", ");
                }
                builder.append(node.getKey()).append(": ").append(node.getValue());
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
