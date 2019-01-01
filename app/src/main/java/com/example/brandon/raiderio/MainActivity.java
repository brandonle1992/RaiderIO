package com.example.brandon.raiderio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.brandon.raiderio.Util.ClassesIcon;
import com.example.brandon.raiderio.Util.RaceIcon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        Button realmBTN = (Button)findViewById(R.id.realmBTN);
        final Spinner realmSpinner = (Spinner) findViewById(R.id.realm_Spinner);
        final TextView nameText = (TextView) findViewById(R.id.nameText);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        requestQueue.add(new JsonObjectRequest(Request.Method.GET,
                "https://us.api.battle.net/wow/realm/status?locale=en_US&apikey=",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray realmArray = response.getJSONArray("realms");
                            String[] realmList = new String[realmArray.length()];

                            for(int i = 0; i < realmArray.length(); i++){
                                realmList[i] = realmArray.getJSONObject(i).getString("name");
                            }

                            ArrayAdapter<String> realmArrayAdapater = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, realmList);

                            realmSpinner.setAdapter(realmArrayAdapater);
                        }catch(JSONException jsonE){
                            jsonE.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }));


            realmBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                    requestQueue.add(new JsonObjectRequest(Request.Method.GET,
                            "https://us.api.battle.net/wow/character/" +
                                    URLEncoder.encode("Zul'jin", "utf-8") + "/" +
                                    nameText.getText() + "?fields=stats&locale=en_US&apikey=yj5s3db9mzc4zjutc2thek4vvuqwj546",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    Intent intent = new Intent(MainActivity.this, CharacterResult.class);
                                    intent.putExtra("Character", response.toString());
                                    MainActivity.this.startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }));
                    }catch(UnsupportedEncodingException uee){
                        uee.printStackTrace();
                    }
                }
            });

    }
}
