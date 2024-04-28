package org.example;

public abstract class Entity {
    protected int id;

    public Entity() {}

    public Entity(int id) {
        this.id = id;
    }

    public abstract int getId();
    public abstract void setId(int id);

}