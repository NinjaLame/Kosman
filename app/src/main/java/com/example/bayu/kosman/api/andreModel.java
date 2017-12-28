package com.example.bayu.kosman.api;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bayu on 28/12/17.
 */

public class andreModel implements Serializable{
    List<andreItem> andreList;

    public List<andreItem> getAndreList() {
        return andreList;
    }

    public void setAndreList(List<andreItem> andreList) {
        this.andreList = andreList;
    }

    public andreModel(List<andreItem> andreList) {
        this.andreList = andreList;

    }

}
