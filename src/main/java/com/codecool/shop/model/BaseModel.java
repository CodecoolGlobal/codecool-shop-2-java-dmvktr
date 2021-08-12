package com.codecool.shop.model;


import java.lang.reflect.Field;
import java.util.Objects;

public class BaseModel {

    protected int id;
    protected String name;
    protected String description;

    public BaseModel(String name) {
        this.name = name;
    }

    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BaseModel(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName()).append(":").append(value).append(",");
                }
            } catch (IllegalAccessException ignored) {
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return getId() == baseModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}