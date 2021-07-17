package com.amela.model;

public class Nation {
    private String nationName;

    public Nation(){

    }

    public Nation(String nationName){
        this.nationName = nationName;
    }

    public String getNationName(){
        return this.nationName;
    }

    public void setNationName(String nationName){
        this.nationName = nationName;
    }
}
