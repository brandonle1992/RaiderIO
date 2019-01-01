package com.example.brandon.raiderio.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

public class ClassesIcon {

    private String iconCode;

    public ClassesIcon(String code){
        this.iconCode = code;
    }

    public Bitmap getURL(){

        Bitmap bitmap = null;
        try{
            bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://wow.zamimg.com/images/icons/class-crests/"+iconCode+".png").getContent());
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

}
