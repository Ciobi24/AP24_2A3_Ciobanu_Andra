package org.example;

import java.sql.Timestamp;
import java.util.List;

public class ReadingList extends Entity {
    private String name;
    private Timestamp creationTimestamp;
    private List<String> bookSet;

    public ReadingList(String name, Timestamp creationTimestamp, List<String> bookSet) {
        this.name = name;
        this.creationTimestamp = creationTimestamp;
        this.bookSet = bookSet;
    }

    public ReadingList() {}

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

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public List<String> getBookSet() {
        return bookSet;
    }

    public void setBookSet(List<String> bookSet) {
        this.bookSet = bookSet;
    }

    @Override
    public String toString() {
        return "ReadingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", bookSet=" + bookSet +
                '}';
    }
}
