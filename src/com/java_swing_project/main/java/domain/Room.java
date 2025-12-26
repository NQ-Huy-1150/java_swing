package com.java_swing_project.main.java.domain;

public class Room {
    private long id;
    private String Status;
    private long pet_id;

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

    public long getPet_id() {
        return pet_id;
    }

    public void setPet_id(long pet_id) {
        this.pet_id = pet_id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", Status='" + Status + '\'' +
                ", pet_id=" + pet_id +
                '}';
    }
}
