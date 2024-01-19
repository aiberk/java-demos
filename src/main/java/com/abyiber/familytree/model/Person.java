package com.abyiber.familytree.model;

public class Person {
    private String name;
    private String mother;
    private String father;
    private Array<Person> children;

    /**
     * Constructs a new Person with the specified name, mother, and father.
     * 
     * @param name   the name of the person
     * @param mother the name of the person's mother
     * @param father the name of the person's father
     */
    public Person(String name, String mother, String father) {
        this.name = name;
        this.mother = mother;
        this.father = father;
        this.children = new Array<Person>();
    }

    /**
     * Adds a child to the list of children for this person.
     * 
     * @param child the child to add
     */
    public void addChild(Person child) {
        this.children.add(child);
    }

    /**
     * Gets the list of children for this person.
     * 
     * @return the list of children
     */
    public Array<Person> getChildren() {
        return children;
    }

    /**
     * Gets the name of this person.
     * 
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the mother of this person.
     * 
     * @return the name of the mother
     */
    public String getMother() {
        return mother;
    }

    /**
     * Gets the name of the father of this person.
     * 
     * @return the name of the father
     */
    public String getFather() {
        return father;
    }

    /**
     * Sets the name of the father of this person.
     * 
     * @param father the new name of the father
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     * Sets the name of the mother of this person.
     * 
     * @param mother the new name of the mother
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     * Returns a string representation of the person (their name).
     * 
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return name;
    }
}
