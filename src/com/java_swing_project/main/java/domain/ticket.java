package com.java_swing_project.main.java.domain;

public class ticket {
    private long id;
    private long serviceId;
    private long roomId;
    private long petId;
    private String createTime;
    private String endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ticket{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", roomId=" + roomId +
                ", petId=" + petId +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
