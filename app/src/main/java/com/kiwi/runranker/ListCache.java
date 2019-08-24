package com.kiwi.runranker;

public class ListCache {
    private static ListCache INSTANCE;
    private RaceInfo list;
    public static ListCache getInstance(){
        if(INSTANCE == null){
           INSTANCE = new ListCache();
        }
        return INSTANCE;
    }

    public void setList(RaceInfo list) {
        this.list = list;
    }

    public RaceInfo getList(){
        return this.list;
    }
}//List Cache
