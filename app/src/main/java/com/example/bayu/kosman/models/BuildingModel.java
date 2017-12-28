package com.example.bayu.kosman.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bayu on 28/12/17.
 */

public class BuildingModel implements Serializable{
    List<BuildingItem> buildingList;

    public List<BuildingItem> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<BuildingItem> buildingList) {
        this.buildingList = buildingList;
    }

    public BuildingModel(List<BuildingItem> buildingList) {
        this.buildingList = buildingList;

    }
}
