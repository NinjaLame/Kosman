package com.example.bayu.kosman.models;

import java.io.Serializable;

/**
 * Created by bayu on 28/12/17.
 */

public class BuildingItem implements Serializable {
    String id, name;

    @Override
    public String toString() {
        return  name ;
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

    public BuildingItem(String id, String name) {

        this.id = id;
        this.name = name;
    }
}
