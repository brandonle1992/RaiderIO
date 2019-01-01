package com.example.brandon.raiderio;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private String[] characterList;
    private String[] guildList;
    private Context context;
    private Bitmap[] imgRaceList;
    private Bitmap[] imgClassList;

    private static LayoutInflater inflater = null;

    public CustomAdapter(ResultActvity activity, String[] characterArray, String[] guildArray,
                         Bitmap[] imgRaceArray, Bitmap[] imgClassArray){
        context = activity;
        characterList = characterArray;
        guildList = guildArray;
        imgRaceList = imgRaceArray;
        imgClassList = imgClassArray;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return characterList.length;
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public class Holder{
        ImageView imgRace;
        ImageView imgClass;
        TextView textName;
        TextView textGuild;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.search_layout,null);
        holder.imgRace = (ImageView)rowView.findViewById(R.id.raceIcon);
        holder.imgClass = (ImageView)rowView.findViewById(R.id.classIcon);
        holder.textName = (TextView)rowView.findViewById(R.id.nameText);
        holder.textGuild = (TextView)rowView.findViewById(R.id.guildText);

        holder.imgRace.setImageBitmap(imgRaceList[position]);
        holder.imgClass.setImageBitmap(imgClassList[position]);
        holder.textName.setText(characterList[position]);
        holder.textGuild.setText(guildList[position]);

        return rowView;
    }
}
