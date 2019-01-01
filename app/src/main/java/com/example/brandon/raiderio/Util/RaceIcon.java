package com.example.brandon.raiderio.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

public class RaceIcon {

    private String raceCode;
    private String genderCode;

    public RaceIcon(String raceCode, String genderCode){
        this.raceCode = raceCode;
        this.genderCode = genderCode;
    }

    public Bitmap getRaceIcon(){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(getURL()).getContent());
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public String getURL(){
                String url = "https://wow.zamimg.com/images/wow/icons/medium/race_" + getRace() +"_" + getGender() + ".jpg";
        return url;
    }

    private String getGender() {
        String gender = "";
        switch(genderCode) {
            case "0":
                gender = "male";
                break;
            case "1":
                gender = "female";
                break;
        }
        return gender;

    }

        private String getRace() {
            String race;
            switch(raceCode)
            {
                case "1":
                    race = "human";
                    break;
                case "2":
                    race = "orc";
                    break;
                case "3":
                    race = "dwarf";
                    break;
                case "4":
                    race = "undead";
                    break;
                case "5":
                    race = "scourge";
                    break;
                case"6":
                    race = "tauren";
                    break;
                case"7":
                    race = "gnome";
                    break;
                case"8":
                    race = "troll";
                    break;
                case"10":
                    race = "bloodelf";
                    break;
                case"11":
                    race = "draenei";
                    break;
                default:
                    race = "default";
            }
            return race;
    }
}
