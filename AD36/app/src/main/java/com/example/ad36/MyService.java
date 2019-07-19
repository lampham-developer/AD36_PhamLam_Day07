package com.example.ad36;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.example.ad36.Foods.Food;

import java.util.HashMap;
import java.util.List;

public class MyService extends AsyncTask<Food, Food, Food> {

    MainActivity mainActivity;

    public MyService(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Food doInBackground(Food... foods) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Food food) {
        super.onPostExecute(food);
    }

    @Override
    protected void onProgressUpdate(Food... values) {
        super.onProgressUpdate(values);
    }
}
