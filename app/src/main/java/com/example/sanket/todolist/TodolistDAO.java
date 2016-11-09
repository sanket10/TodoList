package com.example.sanket.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Happy on 11/8/2016.
 */
public class TodolistDAO extends TodoListDB {
    public static int best_no_of_correct;
    public static int worst_no_of_correct;
    public static String tmp;

    public TodolistDAO(Context context) {
        super(context);
    }
    public long insertItem(ListItem listItem){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_ID,listItem.getItem_id());
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_ITEM_TITLE,listItem.getTitle());
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_ITEM_DETAIL,listItem.getDetail());
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_IS_PASSWORD,listItem.ispassword());
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_PASSWORD,listItem.getPassword());
        contentValues.put(TodolistSchema.ItemTable.COLUMN_NAME_LAST_UPDATE,listItem.getLast_update_date());
        long result = sqLiteDatabase.insert(TodolistSchema.ItemTable.TABLENAME,null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<ListItem> getAllItems(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        List<ListItem> listItems = new LinkedList<ListItem>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from item",null);
        while(cursor.moveToNext()){
            ListItem listItem = new ListItem();
            listItem.setTitle(cursor.getString(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_ITEM_TITLE)));
            listItem.setDetail(cursor.getString(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_ITEM_DETAIL)));
            listItem.setItem_id(cursor.getLong(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_ID)));
            listItem.setIspassword(cursor.getInt(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_IS_PASSWORD)));
            listItem.setPassword(cursor.getString(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_PASSWORD)));
            listItem.setLastUpdateDate(cursor.getString(cursor.getColumnIndex(TodolistSchema.ItemTable.COLUMN_NAME_LAST_UPDATE)));
            listItems.add(listItem);
        }
        sqLiteDatabase.close();
        cursor.close();
        return listItems;
    }

    public boolean updatePassword(ListItem listItem){
        Log.d("TodolistDAO","enter updatePassword()");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE item set is_password = ? , password = ? where item_id = "+listItem.getItem_id(),
                new String[]{1+"",listItem.getPassword()});
        Log.d("TodolistDAO","return updatePassword()");
        return true;
    }

}
