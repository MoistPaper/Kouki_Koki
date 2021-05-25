/*
This is the cover page for the app, and the first activity the user will see. It is the welcome page and includes a randomly generated quote
 */
package com.example.kouki_koki;

/*
This is the cover page for the app, and the first activity the user will see. It is the welcome page and includes a randomly generated quote
 */
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //instantiate image asset
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instantiate title and inspiration text while also set font for it
        TextView inspiration = (TextView) findViewById(R.id.titlepage);
        Typeface quicksand=Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Regular.ttf");
        inspiration.setTypeface(quicksand);
        //Pull quotes from API
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://zenquotes.io/api/random/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        String quote = jsonObject.getString("q");
                        String person = jsonObject.getString("a");
                        inspiration.setText(quote+" -"+person);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("a","Error");
            }
        });
        queue.add(jsonArrayRequest);
    }
    //OnClick attribute for button in order to go to next page
    public void start(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
