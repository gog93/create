package com.example.create;

public class ModelContact {
    private String id,description,name,autoGeneratedId,updatedTime;

    // create constructor

    public ModelContact(String id,String description, String name, String autoGeneratedId,String updatedTime) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.autoGeneratedId = autoGeneratedId;
        this.updatedTime=updatedTime;

    }

    // create getter and setter method


    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String image) {
        this.description = image;
    }

    public String getAutoGeneratedId() {
        return autoGeneratedId;
    }

    public void setAutoGeneratedId(String autoGeneratedId) {
        this.autoGeneratedId = autoGeneratedId;
    }
}
