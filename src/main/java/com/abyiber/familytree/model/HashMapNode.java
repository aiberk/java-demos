package com.abyiber.familytree.model;

/**
 * A node representing a key-value pair in a hash map.
 * 
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class HashMapNode<K, V> {
    private K key;
    private V value;
    private boolean isActive;
    private HashMapNode<K, V> next;

    /**
     * Constructs a new HashMapNode with the specified key and value.
     * 
     * @param key   the key for the node
     * @param value the value associated with the key
     */
    public HashMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.isActive = true;
        this.next = null;
    }

    /**
     * Gets the key of the node.
     * 
     * @return the key of the node
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key of the node.
     * 
     * @param key the new key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Gets the value associated with the key.
     * 
     * @return the value associated with the key
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value associated with the key.
     * 
     * @param value the new value to set
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Checks if the node is active.
     * 
     * @return true if the node is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the activity status of the node.
     * 
     * @param isActive the new activity status
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Gets the next node in the linked list (for handling collisions).
     * 
     * @return the next node
     */
    public HashMapNode<K, V> getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked list (for handling collisions).
     * 
     * @param next the next node to set
     */
    public void setNext(HashMapNode<K, V> next) {
        this.next = next;
    }

    /**
     * Returns a string representation of the HashMapNode.
     * 
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "HashMapNode{" +
                "key=" + key +
                ", value=" + value +
                ", isActive=" + isActive +
                '}';
    }
}