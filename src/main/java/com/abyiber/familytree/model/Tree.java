package com.abyiber.familytree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a family tree where each person is a node in the tree.
 * The tree stores relationships between people, including parents and children.
 *
 * @param <T> The type of data stored in each tree node.
 */
public class Tree<T> {

    private Array<TreeNode<Person>> potentialRoots;
    private HashMap<String, TreeNode<Person>> nameMap;

    /**
     * Constructs a new Tree with the specified name map.
     *
     * @param nameMap A HashMap linking names to corresponding TreeNode objects.
     */
    public Tree(HashMap<String, TreeNode<Person>> nameMap) {
        this.nameMap = nameMap;
        this.potentialRoots = new Array<>();
    }

    /**
     * Prints the names of all potential root nodes in the tree.
     * A potential root is a person without known parents in the tree.
     */
    public void printPotentialRoots() {
        for (TreeNode<Person> root : potentialRoots) {
            System.out.println(root.getData().getName());
        }
    }

    /**
     * Adds a person to the family tree with specified mother and father.
     * If the mother or father is unknown, the person is considered as a potential
     * root.
     *
     * @param name       The name of the person to add.
     * @param motherName The name of the person's mother.
     * @param fatherName The name of the person's father.
     */
    public void addPerson(String name, String motherName, String fatherName) {
        TreeNode<Person> personNode = getOrCreateNode(name);

        if (motherName != null && !motherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> motherNode = getOrCreateNode(motherName);
            personNode.setMother(motherNode);
            motherNode.addChild(personNode);
        } else if (motherName == null || motherName.equalsIgnoreCase("unknown")) {
            potentialRoots.add(personNode);
        }

        if (fatherName != null && !fatherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> fatherNode = getOrCreateNode(fatherName);
            personNode.setFather(fatherNode);
            fatherNode.addChild(personNode);
        } else if (fatherName == null || fatherName.equalsIgnoreCase("unknown")) {
            potentialRoots.add(personNode);
        }
    }

    private TreeNode<Person> getOrCreateNode(String name) {
        if (!nameMap.containsKey(name)) {
            TreeNode<Person> newNode = new TreeNode<>(new Person(name, null, null));
            nameMap.put(name, newNode);
            return newNode;
        }
        return (TreeNode<Person>) nameMap.get(name);
    }

    /**
     * Finds and returns the node associated with the given person's name.
     *
     * @param name The name of the person to find in the tree.
     * @return The TreeNode associated with the given name, or null if not found.
     */
    public TreeNode<Person> findPersonNode(String name) {
        if (name == null) {
            return null;
        }
        return (TreeNode<Person>) nameMap.get(name);
    }

    /**
     * Returns the list of potential root nodes in the tree.
     * Potential roots are those nodes without known parents.
     *
     * @return An Array of TreeNode<Person> objects representing potential root
     *         nodes.
     */
    public Array<TreeNode<Person>> getPotentialRoots() {
        return potentialRoots;
    }

    /**
     * Prints the descendants of the person with the given name.
     * The method prints the person's name followed by their descendants, each
     * indented.
     *
     * @param name The name of the person whose descendants are to be printed.
     */
    public void printDescendants(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            System.out.println("Person not found in the tree.");
            return;
        }
        System.out.println("Descendants:");
        printIndentation(1);
        System.out.println(name);
        printDescendantsRecursive(node, 2);
    }

    /**
     * Recursively prints the descendants of a given node, with indentation based on
     * their level in the tree.
     *
     * @param node  The TreeNode from which to start printing descendants.
     * @param level The current level of indentation.
     */
    private void printDescendantsRecursive(TreeNode<Person> node, int level) {
        if (node == null) {
            return;
        }
        for (TreeNode<Person> child : node.getChildren()) {
            printIndentation(level);
            System.out.println(child.getData().getName());
            printDescendantsRecursive(child, level + 1);
        }
    }

    /**
     * Prints spaces for indentation in the console output.
     * The number of spaces is based on the specified depth.
     *
     * @param depth The depth of indentation, where each depth unit represents a
     *              fixed number of spaces.
     */
    private void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("   ");
        }
    }

    // /**
    // * Prints the ancestors of the person with the given name.
    // * The method prints the person's name followed by their ancestors, each
    // * indented.
    // *
    // * @param name The name of the person whose ancestors are to be printed.
    // */
    // public void printAncestors(String name) {
    // TreeNode<Person> node = findPersonNode(name);
    // if (node == null) {
    // System.out.println("Person not found in the tree.");
    // return;
    // }
    // System.out.println("Ancestors:");
    // printIndentation(1);
    // System.out.println(name);
    // printAncestorsRecursive(node, 2);
    // }

    // /**
    // * Recursively prints the ancestors of a given node, with indentation based on
    // * their level in the tree.
    // *
    // * @param node The TreeNode from which to start printing ancestors.
    // * @param depth The current depth of indentation.
    // */
    // private void printAncestorsRecursive(TreeNode<Person> node, int depth) {
    // if (node == null || (node.getMother() == null && node.getFather() == null))
    // return;
    // int nextDepth = depth + 1;

    // if (node.getMother() != null) {
    // printIndentation(nextDepth);
    // System.out.println(node.getMother().getData().getName());
    // printAncestorsRecursive(node.getMother(), nextDepth);
    // }
    // if (node.getFather() != null) {
    // printIndentation(nextDepth);
    // System.out.println(node.getFather().getData().getName());
    // printAncestorsRecursive(node.getFather(), nextDepth);
    // }
    // }

    public List<String> getAncestors(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            return List.of("Person not found in the tree.");
        }
        List<String> ancestors = new ArrayList<>();
        ancestors.add("Ancestors of " + name + ":");
        printAncestorsRecursive(node, 0, ancestors);
        return ancestors;
    }

    private void printAncestorsRecursive(TreeNode<Person> node, int depth, List<String> list) {
        if (node == null || (node.getMother() == null && node.getFather() == null)) {
            return;
        }
        int nextDepth = depth + 1;

        if (node.getFather() != null) {
            printAncestorsRecursive(node.getFather(), nextDepth, list);
            StringBuilder line = new StringBuilder();
            indent(line, depth);
            line.append(node.getFather().getData().getName());
            list.add(line.toString());
        }

        if (node.getMother() != null) {
            printAncestorsRecursive(node.getMother(), nextDepth, list);
            StringBuilder line = new StringBuilder();
            indent(line, depth);
            line.append(node.getMother().getData().getName());
            list.add(line.toString());
        }
    }

    private void indent(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("    "); // 4 spaces per level of indentation
        }
    }

    /**
     * Updates the parents of the person with the given name.
     * The method updates the mother and father nodes of the specified person in the
     * tree.
     *
     * @param name       The name of the person whose parents are to be updated.
     * @param motherName The name of the mother to be set. Pass null or "unknown" if
     *                   the mother is unknown.
     * @param fatherName The name of the father to be set. Pass null or "unknown" if
     *                   the father is unknown.
     */
    public void updatePersonParents(String name, String motherName, String fatherName) {
        if (!personExists(name)) {
            return;
        }

        TreeNode<Person> personNode = nameMap.get(name);

        if (motherName != null && !motherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> motherNode = getOrCreateNode(motherName);
            personNode.setMother(motherNode);
            motherNode.addChild(personNode);
        }

        if (fatherName != null && !fatherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> fatherNode = getOrCreateNode(fatherName);
            personNode.setFather(fatherNode);
            fatherNode.addChild(personNode);
        }
    }

    /**
     * Checks if a person with the given name exists in the tree.
     *
     * @param name The name of the person to check.
     * @return true if the person exists in the tree, false otherwise.
     */
    public boolean personExists(String name) {
        return nameMap.containsKey(name);
    }

    /**
     * Prints detailed information about a person, including their name, mother's
     * name, father's name, and children's names.
     *
     * @param name The name of the person whose details are to be printed.
     */
    public void printPersonDetails(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            System.out.println("Person not found in the tree.");
            return;
        }

        Person person = node.getData();
        System.out.println("Person: " + person.getName());

        String motherName = (node.getMother() != null) ? node.getMother().getData().getName() : "Unknown";
        System.out.println("Mother: " + motherName);

        String fatherName = (node.getFather() != null) ? node.getFather().getData().getName() : "Unknown";
        System.out.println("Father: " + fatherName);

        System.out.print("Children: ");
        if (node.getChildren().getSize() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < node.getChildren().getSize(); i++) {
                TreeNode<Person> childNode = node.getChildren().get(i);
                System.out.print(childNode.getData().getName());
                if (i < node.getChildren().getSize() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns a string representation of the tree.
     * The string includes all potential root nodes and their descendants, formatted
     * as a tree structure.
     *
     * @return A string representation of the tree.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TreeNode<Person> root : potentialRoots) {
            printTreeRecursive(root, 0, sb);
        }
        return sb.toString();
    }

    /**
     * Recursively builds a string representation of the tree starting from a given
     * node.
     * This method is used internally by the toString() method.
     *
     * @param node  The starting TreeNode for building the string representation.
     * @param level The current level of the node in the tree structure.
     * @param sb    The StringBuilder object used to build the string
     *              representation.
     */
    private void printTreeRecursive(TreeNode<Person> node, int level, StringBuilder sb) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < level; i++) {
            sb.append("    ");
        }

        sb.append(node.getData().getName()).append("\n");
        sb.append("\n");

        for (TreeNode<Person> child : node.getChildren()) {
            printTreeRecursive(child, level + 1, sb);
        }
    }

}
