package com.abyiber.familytree.service;

import java.util.ArrayList;
import java.util.List;

import com.abyiber.familytree.model.Tree;
import com.abyiber.familytree.model.TreeNode;
import com.abyiber.familytree.model.Person;
import com.abyiber.familytree.model.HashMap;
import org.springframework.stereotype.Service;

@Service
public class TreeService {
    private Tree<Person> familyTree;

    public TreeService() {
        this.familyTree = new Tree<>(new HashMap<>());
    }

    public void addPerson(String name, String motherName, String fatherName) {
        familyTree.addPerson(name, motherName, fatherName);
    }

    public List<String> getDescendants(String name) {
        TreeNode<Person> node = familyTree.findPersonNode(name);
        if (node == null) {
            return List.of("Person not found in the tree.");
        }
        List<String> descendants = new ArrayList<>();
        descendants.add("Descendants of " + name + ":");
        printDescendantsRecursive(node, 0, descendants);
        return descendants;
    }

    public List<String> getAncestors(String name) {
        TreeNode<Person> node = familyTree.findPersonNode(name);
        if (node == null) {
            return List.of("Person not found in the tree.");
        }
        List<String> ancestors = new ArrayList<>();
        ancestors.add("Ancestors of " + name + ":");

        // Use the recursive method to populate the ancestors list
        printAncestorsRecursive(node, 0, ancestors);
        return ancestors;
    }

    // Methods for recursive printing (similar to those in Tree class)
    private void printDescendantsRecursive(TreeNode<Person> node, int level, List<String> list) {
        if (node == null) {
            return;
        }

        for (TreeNode<Person> child : node.getChildren()) {
            StringBuilder line = new StringBuilder();
            // indent(line, level);
            line.append(child.getData().getName());
            list.add(line.toString());
            printDescendantsRecursive(child, level + 1, list);
        }
    }

    // private void indent(StringBuilder sb, int level) {
    // for (int i = 0; i < level; i++) {
    // sb.append(" "); // 3 spaces per level of indentation
    // }
    // }

    private void printAncestorsRecursive(TreeNode<Person> node, int level,
            List<String> list) {
        if (node == null) {
            return;
        }

        StringBuilder line = new StringBuilder();
        // indent(line, level);
        line.append(node.getData().getName());
        list.add(line.toString());

        // Recurse for mother
        if (node.getMother() != null) {
            printAncestorsRecursive(node.getMother(), level + 1, list);
        }

        // Recurse for father
        if (node.getFather() != null) {
            printAncestorsRecursive(node.getFather(), level + 1, list);
        }
    }

    public List<String> getFamilyTree(String name) {
        List<String> familyTree = new ArrayList<>();
        familyTree.addAll(getAncestors(name));
        familyTree.add(""); // Add an empty line for separation
        familyTree.addAll(getDescendants(name));
        return familyTree;
    }

}
