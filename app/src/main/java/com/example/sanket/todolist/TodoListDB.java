package com.example.sanket.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class TodoListDB extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todolist.db";
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TodolistSchema.ItemTable.TABLENAME + " (" +
                    TodolistSchema.ItemTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    TodolistSchema.ItemTable.COLUMN_NAME_ITEM_TITLE + " text , " +
                    TodolistSchema.ItemTable.COLUMN_NAME_ITEM_DETAIL + " text , " +
                    TodolistSchema.ItemTable.COLUMN_NAME_IS_PASSWORD + " INTEGER , " +
                    TodolistSchema.ItemTable.COLUMN_NAME_PASSWORD + " TEXT , " +
                    TodolistSchema.ItemTable.COLUMN_NAME_LAST_UPDATE + " TEXT" + " )";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TodolistSchema.ItemTable.TABLENAME;

    public TodoListDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}