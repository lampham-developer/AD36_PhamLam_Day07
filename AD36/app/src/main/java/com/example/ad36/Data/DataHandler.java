package com.example.ad36.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ad36.Foods.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Food_Manager";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FOOD_NAME = "Foods";
    private static final String KEY_FOOD_ID = "id";
    private static final String KEY_FOOD_NAME = "name";
    private static final String KEY_FOOD_DESCRIP = "descrip";
    private static final String KEY_FOOD_TYPE = "types";
    private static final String KEY_FOOD_ICON_ID = "Icon_ID";
    private static final String KEY_FOOD_PRICE = "price";

    private static final String TABLE_ORDER_NAME = "Order_Food";
    private static final String KEY_ORDER_ID = "id";
    private static final String KEY_ORDER_QUANTITY = "quantity";

    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_food = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)"
                , TABLE_FOOD_NAME, KEY_FOOD_ID, KEY_FOOD_NAME, KEY_FOOD_DESCRIP, KEY_FOOD_TYPE, KEY_FOOD_ICON_ID, KEY_FOOD_PRICE);
        String create_table_order = String.format("CREATE TABLE %s(%s INTEGER, %s INTEGER)", KEY_ORDER_ID, KEY_ORDER_QUANTITY);
        sqLiteDatabase.execSQL(create_table_order);
        sqLiteDatabase.execSQL(create_table_food);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_table_food = String.format("DROP TABLE IF EXISTS %s", TABLE_FOOD_NAME);
        String drop_table_order = String.format("DROP TABLE IF EXISTS %s", TABLE_ORDER_NAME);
        sqLiteDatabase.execSQL(drop_table_food);
        sqLiteDatabase.execSQL(drop_table_order);
        onCreate(sqLiteDatabase);
    }

    public  void createTable(){
        String create_table_food = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)"
                , TABLE_FOOD_NAME, KEY_FOOD_ID, KEY_FOOD_NAME, KEY_FOOD_DESCRIP, KEY_FOOD_TYPE, KEY_FOOD_ICON_ID, KEY_FOOD_PRICE);
        String create_table_order = String.format("CREATE TABLE %s(%s INTEGER, %s INTEGER)",TABLE_ORDER_NAME, KEY_ORDER_ID, KEY_ORDER_QUANTITY);
        String drop_table_food = String.format("DROP TABLE IF EXISTS %s", TABLE_FOOD_NAME);
        String drop_table_order = String.format("DROP TABLE IF EXISTS %s", TABLE_ORDER_NAME);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(drop_table_food);
        sqLiteDatabase.execSQL(drop_table_order);
        sqLiteDatabase.execSQL(create_table_food);
        sqLiteDatabase.execSQL(create_table_order);
    }


    public void addNewFood(Food f) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_FOOD_NAME, f.getName());
        contentValues.put(KEY_FOOD_DESCRIP, f.getDescrip());
        contentValues.put(KEY_FOOD_TYPE, f.getType());
        contentValues.put(KEY_FOOD_ICON_ID, f.getIconId());
        contentValues.put(KEY_FOOD_PRICE, f.getPrice());

        sqLiteDatabase.insert(TABLE_FOOD_NAME, null, contentValues);
        sqLiteDatabase.close();
    }


    public void clearFoodDataBase() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_FOOD_NAME;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public void clearOrderDataBase() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ORDER_NAME;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public HashMap<Food, Integer> getOrderList() {
        HashMap<Food, Integer> listOrder = new HashMap<>();

        String query = "SELECT * FROM " + TABLE_ORDER_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor != null) {
            while (cursor.isAfterLast() == false) {
                Food f = getFood(cursor.getInt(0));
                listOrder.put(f, cursor.getInt(1));
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        return listOrder;

    }

    public List<Food> getFoodList(String type) {
        List<Food> listFood = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_FOOD_NAME + " WHERE " + KEY_FOOD_TYPE + " = ?";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{type});
        cursor.moveToFirst();
        if (cursor != null) {
            while (cursor.isAfterLast() == false) {
                Food f = new Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
                listFood.add(f);
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        return listFood;
    }

    public Food getFood(int id) {
        Food f = null;
        String query = "SELECT * FROM " + TABLE_FOOD_NAME + " WHERE " + KEY_FOOD_ID + " = ?";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor != null) {
            cursor.moveToFirst();
            f = new Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
        }
        return f;
    }


    public void updateOrder(int id){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER_NAME + " WHERE " + KEY_ORDER_ID + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});

        sqLiteDatabase = this.getWritableDatabase();
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            int quantity = cursor.getInt(1) + 1;
            sqLiteDatabase.execSQL("UPDATE Order_Food SET quantity = ? WHERE id = ?",new String[]{ String.valueOf(quantity), String.valueOf(id)});
        }else {
            sqLiteDatabase.execSQL("INSERT INTO Order_Food ( id, quantity) VALUES (?,?)",new String[]{ String.valueOf(id), String.valueOf(1)});
        }
        sqLiteDatabase.close();
    }

    public int getCountOrder(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = 0;
        if(cursor.getCount() > 0)  count = cursor.getCount();
        return count;
    }
}
