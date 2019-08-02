package com.example.ad36_phamlam_demojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ad36_phamlam_demojson.Entity.MainWeather;
import com.example.ad36_phamlam_demojson.Entity.SysWeather;
import com.example.ad36_phamlam_demojson.Entity.Weather;
import com.example.ad36_phamlam_demojson.Entity.Wind;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String jsonArray = "{\"coord\": { \"lon\": 139,\"lat\": 35}, \"weather\": [ { \"id\": 800, \"main\": \"Clear\", \"description\": \"clear sky\", \"icon\": \"01n\" } ], \"base\": \"stations\", \"main\": { \"temp\": 289.92, \"pressure\": 1009, \"humidity\": 92, \"temp_min\": 288.71, \"temp_max\": 290.93 }, \"wind\": { \"speed\": 0.47, \"deg\": 107.538 }, \"clouds\": { \"all\": 2 }, \"dt\": 1560350192, \"sys\": { \"type\": 3, \"id\": 2019346, \"message\": 0.0065, \"country\": \"JP\", \"sunrise\": 1560281377, \"sunset\": 1560333478 }, \"timezone\": 32400, \"id\": 1851632, \"name\": \"Shuzenji\", \"cod\": 200 }";

    TextView tvName,tvMain, tvtemp, tvsun_rise_set, tv_min_temp, tv_max_temp, tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvName = findViewById(R.id.tvName);
        tvMain = findViewById(R.id.tvMain);
        tvtemp = findViewById(R.id.tvtemp);
        tvsun_rise_set = findViewById(R.id.tvsun_rise_set);
        tv_min_temp = findViewById(R.id.tv_min_temp);
        tv_max_temp = findViewById(R.id.tv_max_temp);
        tvDate = findViewById(R.id.tvDate);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        Date date = new Date(1560350192);
        tvName.setText(simpleDateFormat.format(date));

        try {
            Weather weather = getWeather(new JSONObject(String.valueOf(jsonArray)).getString("weather"));
            MainWeather mainWeather = getMainWeather(new JSONObject(String.valueOf(jsonArray)).getString("main"));
            SysWeather sysWeather = getSys(new JSONObject(String.valueOf(jsonArray)).getString("sys"));


            tvName.setText(new JSONObject(jsonArray).getString("name"));
            tvMain.setText(weather.getMain());
            tvtemp.setText(String.valueOf(tempExchange(mainWeather.getTemp_min())));
            tv_max_temp.setText(String.valueOf(tempExchange(mainWeather.getTemp_max())));
            tv_min_temp.setText(String.valueOf(tempExchange(mainWeather.getTemp_min())));
            tvsun_rise_set.setText(dateExchange(sysWeather.getSunrise(),"HH:mm") +"/"+ dateExchange(sysWeather.getSunset(), "HH:mm"));
            tvDate.setText(dateExchange(new JSONObject(String.valueOf(jsonArray)).getString("dt"),"dd/MM/yy"));

        }catch (Exception e){
        }


    }


    private Weather getWeather(String json){
        Weather weather = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String id = jsonObject.getString("id");
            String main = jsonObject.getString("main");
            String description = jsonObject.getString("description");
            String icon = jsonObject.getString("icon");
            weather = new Weather(id,main,description,icon);
        }catch (Exception e){
            e.printStackTrace();
        }
        return weather;
    }

    private MainWeather getMainWeather(String json){
        MainWeather mainWeather = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String temp = jsonObject.getString("temp");
            String pressure = jsonObject.getString("pressure");
            String humidity = jsonObject.getString("humidity");
            String temp_min = jsonObject.getString("temp_min");
            String temp_max = jsonObject.getString("temp_max");
            mainWeather = new MainWeather(temp,pressure,humidity,temp_min,temp_max);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mainWeather;
    }


    private Wind getWind(String json){
        Wind wind = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String speed = jsonObject.getString("speed");
            String deg = jsonObject.getString("deg");
            wind = new Wind(speed,deg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wind;
    }

    private SysWeather getSys(String json){
        SysWeather sysWeather = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String type = jsonObject.getString("type");
            String id = jsonObject.getString("id");
            String message = jsonObject.getString("message");
            String country = jsonObject.getString("country");
            String sunrise = jsonObject.getString("sunrise");
            String sunset = jsonObject.getString("sunset");
            sysWeather = new SysWeather(type,id,message,country,sunrise,sunset);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysWeather;
    }

    private int tempExchange(String s){
        return (int) Math.round((Double.parseDouble(s)- 273.15));
    }

    private String dateExchange(String source, String parten){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parten);
        Date date = new Date(Long.parseLong(source));
        return simpleDateFormat.format(date);
    }

}
