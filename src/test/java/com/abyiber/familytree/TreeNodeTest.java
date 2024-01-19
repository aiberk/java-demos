package com.abyiber.familytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.abyiber.familytree.model.TreeNode;
import com.abyiber.familytree.model.Person;

public class TreeNodeTest {
    private TreeNode<Person> rootNode;
    private Person rootPerson;
    private Person childPerson;

    @BeforeEach
    public void setUp() {
        rootPerson = new Person("RootPerson", null, null);
        childPerson = new Person("ChildPerson", null, null);
        rootNode = new TreeNode<>(rootPerson);
    }

    @Test
    public void testAddChild() {
        TreeNode<Person> childNode = new TreeNode<>(childPerson);
        rootNode.addChild(childNode);

        assertEquals(1, rootNode.getChildren().getSize(), "Child count should be 1");
        assertEquals("ChildPerson", rootNode.getChildren().get(0).getData().getName(), "Child should be ChildPerson");
    }

    @Test
    public void testGetMotherAndFather() {
        TreeNode<Person> motherNode = new TreeNode<>(new Person("Mother", null, null));
        TreeNode<Person> fatherNode = new TreeNode<>(new Person("Father", null, null));

        rootNode.setMother(motherNode);
        rootNode.setFather(fatherNode);

        assertEquals("Mother", rootNode.getMother().getData().getName(), "Mother should be set correctly");
        assertEquals("Father", rootNode.getFather().getData().getName(), "Father should be set correctly");
    }

    @Test
    public void testGetData() {
        assertEquals("RootPerson", rootNode.getData().getName(), "Data should be RootPerson");
    }
}
