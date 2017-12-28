package com.example.bayu.kosman.api;

import java.io.Serializable;

/**
 * Created by bayu on 28/12/17.
 */

public class andreItem implements Serializable{
    String id,nama;

    public andreItem(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
