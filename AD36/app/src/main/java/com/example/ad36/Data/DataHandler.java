package com.example.ad36.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ad36.Entity.User;
import com.example.ad36.Foods.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {
    DataOnCreate dataOnCreate;
    NameContainer nameContainer = new NameContainer();

    public static final String DATABASE_NAME = "Food_Manager";
    public static final int DATABASE_VERSION = 4;
    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_food = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)"
                , nameContainer.TABLE_FOOD_NAME, nameContainer.KEY_FOOD_ID, nameContainer.KEY_FOOD_NAME, nameContainer.KEY_FOOD_DESCRIP, nameContainer.KEY_FOOD_TYPE, nameContainer.KEY_FOOD_ICON_ID, nameContainer.KEY_FOOD_PRICE);
        String create_table_order = String.format("CREATE TABLE %s(%s INTEGER, %s INTEGER)",nameContainer.TABLE_ORDER_NAME, nameContainer.KEY_ORDER_ID, nameContainer.KEY_ORDER_QUANTITY);
        String create_table_user = String.format("CREATE TABLE %s(%s TEXT, %s TEXT)",nameContainer.TABLE_USER_NAME, nameContainer.KEY_USER_ID, nameContainer.KEY_USER_PASSWORD);

        sqLiteDatabase.execSQL(create_table_order);
        sqLiteDatabase.execSQL(create_table_food);
        sqLiteDatabase.execSQL(create_table_user);

        onCreateData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_table_food = String.format("DROP TABLE IF EXISTS %s", nameContainer.TABLE_FOOD_NAME);
        String drop_table_order = String.format("DROP TABLE IF EXISTS %s", nameContainer.TABLE_ORDER_NAME);
        String drop_table_user = String.format("DROP TABLE IF EXISTS %s", nameContainer.TABLE_USER_NAME);

        sqLiteDatabase.execSQL(drop_table_food);
        sqLiteDatabase.execSQL(drop_table_order);
        sqLiteDatabase.execSQL(drop_table_user);
        onCreate(sqLiteDatabase);
    }

    //
    //DATA CONTROLLER
    //

    private void onCreateData(SQLiteDatabase sqLiteDatabase){
        dataOnCreate = new DataOnCreate();
        for ( Food food: dataOnCreate.baseFood()){
            addNewFood(food, sqLiteDatabase);
        }
    }


    //
    //FOOD CONTROLLER
    //

    public void addNewFood(Food f, SQLiteDatabase sqLiteDatabase) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(nameContainer.KEY_FOOD_NAME, f.getName());
        contentValues.put(nameContainer.KEY_FOOD_DESCRIP, f.getDescrip());
        contentValues.put(nameContainer.KEY_FOOD_TYPE, f.getType());
        contentValues.put(nameContainer.KEY_FOOD_ICON_ID, f.getIconId());
        contentValues.put(nameContainer.KEY_FOOD_PRICE, f.getPrice());

        sqLiteDatabase.insert(nameContainer.TABLE_FOOD_NAME, null, contentValues);
//        sqLiteDatabase.close();
    }

    public List<Food> getFoodList(String type) {
        List<Food> listFood = new ArrayList<>();

        String query = "SELECT * FROM " + nameContainer.TABLE_FOOD_NAME + " WHERE " + nameContainer.KEY_FOOD_TYPE + " = ?";

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
        String query = "SELECT * FROM " + nameContainer.TABLE_FOOD_NAME + " WHERE " + nameContainer.KEY_FOOD_ID + " = ?";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor != null) {
            cursor.moveToFirst();
            f = new Food(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
        }
        return f;
    }

    //
    //ORDER CONTROLLER
    //

    public HashMap<Food, Integer> getOrderList() {
        HashMap<Food, Integer> listOrder = new HashMap<>();

        String query = "SELECT * FROM " + nameContainer.TABLE_ORDER_NAME;

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

    public void updateOrder(int id){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + nameContainer.TABLE_ORDER_NAME + " WHERE " + nameContainer.KEY_ORDER_ID + " = ?";
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

    public void clearOrderDataBase() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + nameContainer.TABLE_ORDER_NAME;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public int getCountOrder(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + nameContainer.TABLE_ORDER_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = 0;
        if(cursor.getCount() > 0)  count = cursor.getCount();
        return count;
    }

    //
    //USER CONTROLLER
    //


    public void addNewUser(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(nameContainer.KEY_USER_ID, user.getUsername());
        contentValues.put(nameContainer.KEY_USER_PASSWORD, user.getPassword());

        sqLiteDatabase.insert(nameContainer.TABLE_USER_NAME, null, contentValues);
        sqLiteDatabase.close();
    }


    public User getUser(String name){
        User user = null;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + nameContainer.TABLE_USER_NAME + " WHERE " + nameContainer.KEY_USER_ID + " = ?";

        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{name});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            String username = cursor.getString(0);
            String password = cursor.getString(1);

            user = new User(username,password);
        }
        return user;
    }

}
