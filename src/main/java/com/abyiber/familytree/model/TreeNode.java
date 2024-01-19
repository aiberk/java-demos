package com.abyiber.familytree.model;

/**
 * Represents a tree node with data, mother, father, and a list of children
 * nodes.
 * 
 * @param <T> the type of data stored in the node
 */
public class TreeNode<T> {
    private T data;
    private TreeNode<T> mother;
    private TreeNode<T> father;
    private Array<TreeNode<T>> children;

    /**
     * Constructs a new TreeNode with the specified data.
     * 
     * @param data the data to be stored in the node
     */
    public TreeNode(T data) {
        this.data = data;
        this.children = new Array<TreeNode<T>>();
    }

    /**
     * Adds a child node to the list of children for this node.
     * 
     * @param child the child node to add
     */
    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    /**
     * Gets the list of children nodes for this node.
     * 
     * @return the list of children nodes
     */
    public Array<TreeNode<T>> getChildren() {
        return children;
    }

    /**
     * Gets the data stored in this node.
     * 
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the mother node of this node.
     * 
     * @return the mother node
     */
    public TreeNode<T> getMother() {
        return mother;
    }

    /**
     * Sets the mother node of this node.
     * 
     * @param mother the new mother node to set
     */
    public void setMother(TreeNode<T> mother) {
        this.mother = mother;
    }

    /**
     * Gets the father node of this node.
     * 
     * @return the father node
     */
    public TreeNode<T> getFather() {
        return father;
    }

    /**
     * Sets the father node of this node.
     * 
     * @param father the new father node to set
     */
    public void setFather(TreeNode<T> father) {
        this.father = father;
    }
}