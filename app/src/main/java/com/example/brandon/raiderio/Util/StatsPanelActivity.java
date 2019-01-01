package com.example.brandon.raiderio.Util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brandon.raiderio.CharacterResult;
import com.example.brandon.raiderio.CustomAdapter;
import com.example.brandon.raiderio.R;

import java.util.Map;

public class StatsPanelActivity extends BaseAdapter {

    private Map<String,String> mapStats;
    Context context;
    LayoutInflater inflater = null;

    public StatsPanelActivity(CharacterResult cr, Map<String,String> stats){
        this.mapStats = stats;
        this.context = cr;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return mapStats.size();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }


    class StatsHolder{
        TextView textView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        StatsHolder holder = new StatsHolder();
        View rowView;

        rowView = inflater.inflate(R.layout.stats_panel,null);

        holder.textView = (TextView)rowView.findViewById(R.id.statsText);

        holder.textView.setText(mapStats.keySet().toArray()[position] + ": " +
        mapStats.get(mapStats.keySet().toArray()[position]));
        holder.textView.setTextColor(Color.WHITE);

        return rowView;
    }
}
