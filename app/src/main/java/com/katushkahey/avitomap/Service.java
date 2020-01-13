package com.katushkahey.avitomap;

public class Service {
    private String name;
    boolean checked = false;

    public Service(String name) {
        this.name = name;
    }

    public void setChecked(Boolean value) {
        this.checked = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getChecked() {
        return checked;
    }
}