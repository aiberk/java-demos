package com.abyiber.familytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.abyiber.familytree.model.Tree;
import com.abyiber.familytree.model.TreeNode;
import com.abyiber.familytree.model.Person;
import com.abyiber.familytree.model.HashMap;

public class TreeTest {
    private Tree<Person> tree;
    private HashMap<String, TreeNode<Person>> familyMap;

    @BeforeEach
    public void setUp() {
        familyMap = new HashMap<>();
        tree = new Tree<>(familyMap);
    }

    @Test
    public void testAddPersonAndFindPersonNode() {
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, "John");
        tree.addPerson("Alice", "Mary", "John");

        assertNotNull(tree.findPersonNode("John"), "John should be in the tree");
        assertNotNull(tree.findPersonNode("Mary"), "Mary should be in the tree");
        assertNotNull(tree.findPersonNode("Alice"), "Alice should be in the tree");

        TreeNode<Person> johnNode = tree.findPersonNode("John");
        TreeNode<Person> maryNode = tree.findPersonNode("Mary");
        TreeNode<Person> aliceNode = tree.findPersonNode("Alice");

        assertEquals(maryNode, aliceNode.getMother(), "Mary should be the mother of Alice");
        assertEquals(johnNode, aliceNode.getFather(), "John should be the father of Alice");
    }
}
