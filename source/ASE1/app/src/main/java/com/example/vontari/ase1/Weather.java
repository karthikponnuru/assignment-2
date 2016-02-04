package com.example.vontari.ase1;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weather extends AppCompatActivity implements View.OnClickListener{
    Button CheckBut;
    String LocationText;
    TextView Temperature;
    TextView Min;
    TextView Max;
    TextView Wind;
    TextView Humidity;
    TextView Sky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        CheckBut=(Button)findViewById(R.id.CheckBut);
        CheckBut.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




        @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CheckBut:
                OkHttpClient client = new OkHttpClient();
                TextView cityname = (TextView) findViewById(R.id.etLocation);
                LocationText = cityname.getText().toString();
                final String getURL = "http://api.openweathermap.org/data/2.5/weather?q="+LocationText+"&units=metric&appid=aecc1ada15291787e9f4ec95ab382165";//The API service URL;//The API service URL
                Request request= new Request.Builder().url(getURL).build();
                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("hii",getURL);
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final JSONObject jsonResult;
                        final String result = response.body().string();

                        try {
                            jsonResult = new JSONObject(result);
                            String city = jsonResult.getString("name");
                            System.out.println(city);
                            JSONObject mainobj=jsonResult.getJSONObject("main");
                            final String temperature=mainobj.getString("temp");
                            final String min_Temperature=mainobj.getString("temp_min");
                            final String max_Temperature=mainobj.getString("temp_max");
                            final String humidity=mainobj.getString("humidity");
                            System.out.println(temperature);
                            System.out.println(min_Temperature);
                            System.out.println(max_Temperature);
                            JSONObject windobj=jsonResult.getJSONObject("wind");
                            final String wind=windobj.getString("speed");
                            System.out.println(wind);
                            System.out.println(humidity);

                            JSONArray weatherarray=jsonResult.getJSONArray("weather");
                            JSONObject jb = (JSONObject)weatherarray.getJSONObject(0);
                            final String main=jb.getString("main");
                            System.out.println(main);
                            final TextView Temperature=(TextView)findViewById(R.id.Temperature);
                            final TextView Min=(TextView)findViewById(R.id.Min);
                            final TextView Max=(TextView)findViewById(R.id.Max);
                            final TextView Wind=(TextView)findViewById(R.id.Wind);
                            final TextView Humidity=(TextView)findViewById(R.id.Humidity);
                            final TextView Sky=(TextView)findViewById(R.id.Sky);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Temperature.setText("Temperature : "+temperature);
                                    Min.setText("Min Temperature : "+min_Temperature);
                                    Max.setText("Max Temperature : "+max_Temperature);
                                    Wind.setText("Wind : "+wind);
                                    Humidity.setText("Humidity : "+humidity);
                                    Sky.setText("Sky : "+main);

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });




                break;



        }

    }
}

