package com.kiwi.runranker;

import java.util.Collections;
import java.util.List;

public class RaceInfo {
    private String Name;//The race's name
    private List<Runner> Runners;//The people who participated in the race

    @Override
    public String toString(){
        return Name + " " + Runners.size();
    }

    String getRaceName(){
        if(Name != null) {
            return Name;
        }
        return "";//In case the name is null
    }

    String getNumberofRacers(){
        if(Runners != null){
            return Runners.size() == 1 ? Runners.size() + " Racer" : Runners.size() + " Racers";
        }
        return "0";
    }

    List<Runner> getRunners(){
        Collections.sort(Runners);//Sort the list by Finish Time
        return Runners;
    }

}
