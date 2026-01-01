package com.java_swing_project.main.java.domain;

public class Room {
    private long id;
    private String name;
    private String Status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", Status='" + Status + '\'' +
                '}';
    }
}
