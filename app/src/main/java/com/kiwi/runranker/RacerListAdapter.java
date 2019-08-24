package com.kiwi.runranker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class RacerListAdapter extends RecyclerView.Adapter<RacerListAdapter.ViewHolder> {

    private List<Runner> runners;


    RacerListAdapter(List<Runner> runnerList){
        this.runners = runnerList;
    }

    @Override
    public int getItemCount(){
        return runners.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultlist_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos){
        //Assign data to UI
        Runner runner = runners.get(pos);
        holder.tvRacerName.setText(runner.getName());
        holder.tvRacerAge.setText(String.format(Locale.ROOT, "Age: %d", runner.getAge()));
        holder.tvRacerTime.setText(String.valueOf(runner.getTime()));
        holder.tvRacerRank.setText(String.valueOf(runner.getRanking()));
        //Change the color of the UI based on the age category
        switch (runner.getCategory()) {
            case TEEN:
                holder.tvRacerRank.setBackgroundColor(holder.itemView.getResources().getColor(R.color.category_teen));
                break;
            case YOUNG:
                holder.tvRacerRank.setBackgroundColor(holder.itemView.getResources().getColor(R.color.category_young));
                break;
            case ADULT:
                holder.tvRacerRank.setBackgroundColor(holder.itemView.getResources().getColor(R.color.category_adult));
                break;
            default:
                holder.tvRacerRank.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorPrimaryDark));
        }
        switch (runner.getRanking()){//Displays the appropriate medal graphic
            case 1:
                holder.ivRacerMedal.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_medal_yo_first));
                break;
            case 2:
                holder.ivRacerMedal.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_medal_yo_second));
                break;
            case 3:
                holder.ivRacerMedal.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_medal_yo_third));
                break;
            default://If they didn't earn a medal, hide it
                holder.ivRacerMedal.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.ic_medal_none));
                break;
        }
        //Assign view to row
        holder.tvRacerTime.setBackgroundColor(getTimeGradient(pos, getItemCount()));
    }

    private int getTimeGradient(int position, int total){//This makes the time box darker the later a racer finished
        double step = 255.0 / total;

        return Color.argb(255, (int) (255 - (step * position)), 0, 0 );
    }

    static class ViewHolder extends RecyclerView.ViewHolder{//A view holder to keep things smooth
        private TextView tvRacerName;
        private TextView tvRacerAge;
        private TextView tvRacerTime;
        private TextView tvRacerRank;
        private ImageView ivRacerMedal;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(null);//The list view doesn't go anywhere, so it shouldn't be clickable
            //Set Up the UI in the list row
            tvRacerName = view.findViewById(R.id.racer_name);
            tvRacerAge = view.findViewById(R.id.racer_age);
            tvRacerTime = view.findViewById(R.id.racer_time);
            tvRacerRank = view.findViewById(R.id.racer_rank);
            ivRacerMedal = view.findViewById(R.id.racer_medal);
        }
    }
}
