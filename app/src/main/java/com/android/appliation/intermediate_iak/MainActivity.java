package com.android.appliation.intermediate_iak;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    String urlAPI= "http://api.openweathermap.org/data/2.5/forecast/?lat=-6.1877386&lon=106.7400824&units=metric&APPID=9ed2257682b1d9a2eb66c15047e1bfdd";

    @BindView(R.id.weather_day_title)
    TextView weatherDayTitle;

    @BindView(R.id.tvCity)
    TextView cityTV;

    @BindView(R.id.weather_desc)
    TextView weatherDesc;

    @BindView(R.id.weather_temperatur)
    TextView weatherTemperature;

    @BindView(R.id.img_weather)
    ImageView imgWeather;

    @BindView(R.id.weather_list)
    RecyclerView weatherList;

    private List<Weather> weatherListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        //this one for Recycler View
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        weatherList.setLayoutManager(llm);

        //to Weather
        weatherListData = new ArrayList<Weather>();
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "11 November 2017","Cerah","17"));
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "12 November 2017","Mendung","18"));
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "13 November 2017","Hujan","19"));
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "14 November 2017","Cerah","20"));
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "15 November 2017","Mendung","21"));
//        weatherListData.add(new Weather(R.mipmap.ic_launcher, "16 November 2017","Hujan","22"));

        final WeatherAdapter weatherAdapter = new WeatherAdapter(weatherListData);

        //setRecyclerView
        weatherList.setAdapter(weatherAdapter);

        // Request API
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                try {
                    JSONArray list= response.getJSONArray("list");
                    JSONObject city= response.getJSONObject("city");
                    String lokasi= city.getString("name");

                    cityTV.setText(lokasi);

                    for(int x=0;x<10;x++){

                        JSONObject object = list.getJSONObject(x);
                        JSONObject main = object.getJSONObject("main");
                        String date = object.getString("dt_txt");
                        String temp= String.valueOf(main.getDouble("temp"));

                        JSONArray weather= object.getJSONArray("weather");
                        JSONObject weatherObject= weather.getJSONObject(0);
                        String mainWeather= weatherObject.getString("main");

                        if (x==0){
                            //this one for header
                            weatherDayTitle.setText(date);
                            weatherDesc.setText(mainWeather);
                            weatherTemperature.setText(temp);
                            imgWeather.setImageResource(R.mipmap.ic_launcher_round);
                            //end header
                        }
                        SimpleDateFormat formatDefault = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        SimpleDateFormat formatTimeCustom = new SimpleDateFormat("MM-dd hh.mm");

                        try {
                            Date timesFormat = formatDefault.parse(date);
                            date = formatTimeCustom.format(timesFormat);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (mainWeather.equals("Clear")){
                            weatherListData.add(new Weather(R.drawable.sunny,date,mainWeather,temp));
                        }else{
                            weatherListData.add(new Weather(R.drawable.rain_cloud_png_with_perfect_rain_cloud_png_for_decorating,date,mainWeather,temp));
                        }


                        weatherAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR RESPONSE",error.getMessage());
            }
        });

        Volley.newRequestQueue(getBaseContext()).add(jsonObjectRequest);
    }
}
