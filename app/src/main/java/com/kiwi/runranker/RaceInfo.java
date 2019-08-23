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
        //Variables to keep track of the ranking
        int numTeens = 0;
        int numYoung = 0;
        int numAdults = 0;
        Collections.sort(Runners);//Sort the list by Finish Time
        //Iterate though list to get teh rankings for the racers
        for(int i = 0; i < Runners.size(); i++){
            Runners.get(i).assignCategory();//This function gets assigns the age category for a racer
            switch (Runners.get(i).getCategory()){
                case TEEN:
                    numTeens++;
                    Runners.get(i).setRanking(numTeens);
                    break;
                case YOUNG:
                    numYoung++;
                    Runners.get(i).setRanking(numYoung);
                    break;
                case ADULT:
                    numAdults++;
                    Runners.get(i).setRanking(numAdults);
                    break;
                default:
                    Runners.get(i).setRanking(0);
            }
        }
        return Runners;
    }

}
