package com.example.brandon.raiderio;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brandon.raiderio.Util.StatsPanelActivity;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CharacterResult extends AppCompatActivity {

    private String mainCharImgURL = "https://render-us.worldofwarcraft.com/character/";
    private String[] primaryWords = {"str", "agi", "int", "sta","health","power"};
    private String[] secondaryWords = {"critRating","hasteRating","masteryRating","versatility"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.armory_layout);

        Intent intent = getIntent();
        Map<String,String> primaryStats = new HashMap<String,String>();
        Map<String,String> secondaryStats = new HashMap<String,String>();;


        TextView nameText = findViewById(R.id.characterHeaderText);
        ImageView characterMainImage = findViewById(R.id.mainCharacterImg);

        try{
            JSONObject response = new JSONObject(intent.getStringExtra("Character"));


            nameText.setText(response.getString("name"));
            nameText.setTextColor(Color.WHITE);
            characterMainImage.setImageBitmap(BitmapFactory
                    .decodeStream((InputStream) new URL(mainCharImgURL + response.getString("thumbnail").replaceAll("\\bavatar\\b", "main"))
                            .getContent()));

            JSONObject jar = response.getJSONObject("stats");

            for(int i = 0; i < primaryWords.length; i++){

                System.out.println(jar.getString(primaryWords[i]));

                primaryStats.put(primaryWords[i], jar.getString(primaryWords[i]));
            }
            for(int i = 0; i < secondaryWords.length; i++){

                secondaryStats.put(secondaryWords[i], jar.getString(secondaryWords[i]));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        ListView primaryLV = findViewById(R.id.primaryStatsList);
        ListView secondaryLV = findViewById(R.id.secondaryStatsList);

        primaryLV.setAdapter(new StatsPanelActivity(this,primaryStats));

        secondaryLV.setAdapter(new StatsPanelActivity(this,secondaryStats));
    }
}
