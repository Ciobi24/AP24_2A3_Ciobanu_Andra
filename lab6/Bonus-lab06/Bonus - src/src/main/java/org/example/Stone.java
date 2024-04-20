package org.example;

import java.io.Serializable;

public class Stone implements Serializable {
    private final int index;
    private final int x;
    private final int y;
    private int color;

    public Stone(int index, int x, int y, int color) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Stone{" +
                "index=" + index +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }

}