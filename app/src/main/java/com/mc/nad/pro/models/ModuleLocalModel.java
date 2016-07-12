package com.mc.nad.pro.models;

import io.realm.RealmObject;

public class ModuleLocalModel extends RealmObject {

    private String title;
    private String name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
