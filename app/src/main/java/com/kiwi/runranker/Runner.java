package com.kiwi.runranker;

enum ageCategory{//Using this ENUM to keep things consistent throughout the app
    TEEN,//0-15
    YOUNG,//16-29
    ADULT//30+
}

class Runner implements Comparable<Runner>{
    private String Name;
    private int Age;
    private int Time;
    private ageCategory Category;//This is the category that the race belongs to, 1 = 0-15
    private int Ranking;//The Ranking in their age group

    void assignCategory(){
        //Assign their Categories, better to do it here than iterate though the list later
        if(Age <= 15){
            Category = ageCategory.TEEN;
        }
        else if(Age <= 29){
            Category = ageCategory.YOUNG;
        }
        else{
            Category = ageCategory.ADULT;
        }
    }

    int getTime(){return Time;}
    public String getName(){return Name;}
    int getAge(){return Age;}
    ageCategory getCategory(){return Category;}
    int getRanking(){return Ranking;}

    void setRanking(int rank){Ranking = rank;}

    @Override
    public String toString(){
        return Name + " " + Time + " " + Age;
    }

    @Override
    public int compareTo(Runner r){//This helps sort a List of Runners by Finish Time
        return (Time - r.getTime());
    }
}

