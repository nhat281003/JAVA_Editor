package com.example.test_exam.model;

public class BlinkData {
    String createdAt;
    String name;
    String avatar;
    String height;
    String tall;
    String id;
    int age;

    public BlinkData() {
    }

    public BlinkData(String createdAt, String name, String avatar, String height, String tall, String id, int age) {
        this.createdAt = createdAt;
        this.name = name;
        this.avatar = avatar;
        this.height = height;
        this.tall = tall;
        this.id = id;
        this.age = age;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
