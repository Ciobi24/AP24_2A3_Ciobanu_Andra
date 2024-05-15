package org.example;

public class Author extends Entity{
    private String name;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    // Getters and setters


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}