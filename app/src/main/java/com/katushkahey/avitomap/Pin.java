package com.katushkahey.avitomap;

public class Pin {
    private int id;
    private String service;
    private Coordinates coordinates;
    private boolean checked = false;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public boolean getChecked(){
        return checked;
    }

    public void setChecked(boolean value){
        checked = value;
    }
}
