package com.example.brandon.raiderio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.brandon.raiderio.Util.ClassesIcon;
import com.example.brandon.raiderio.Util.RaceIcon;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent intent = getIntent();
        String name, guild, classType, raceType, genderType;
        Bitmap[] classIcons;
        Bitmap[] raceIcons;
        String[] nameArray;
        String[] guildArray;

        ListView characterLV = (ListView)findViewById(R.id.characterLV);

        try {
            JSONObject response = new JSONObject(intent.getStringExtra("Character"));

            int max = 1;
            classIcons = new Bitmap[max];
            raceIcons = new Bitmap[max];
            nameArray = new String[max];
            guildArray = new String[max];

            for(int i = 0 ; i < max; i++) {
                name = response.getString("name");
                classType = response.getString("class");
                raceType = response.getString("race");
                genderType = response.getString("gender");
                guild = "Vigil";


                classIcons[i] = new ClassesIcon(classType).getURL();
                raceIcons[i] = new RaceIcon(raceType, genderType).getRaceIcon();
                System.out.println("Bitmap: " + raceIcons[i]);

                nameArray[i] = name;
                guildArray[i] = guild;
            }

            characterLV.setAdapter(new CustomAdapter(this,nameArray,guildArray,raceIcons,classIcons));

        }catch(JSONException jsonE) {
            jsonE.printStackTrace();
        }
    }
}
