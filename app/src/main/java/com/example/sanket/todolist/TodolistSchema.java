package com.example.sanket.todolist;

/**
 * Created by Happy on 11/8/2016.
 */
public class TodolistSchema {
    public static final class ItemTable{
        public static final String TABLENAME = "item";
        public static final String COLUMN_NAME_ID = "item_id";
        public static final String COLUMN_NAME_ITEM_TITLE = "item_title";
        public static final String COLUMN_NAME_ITEM_DETAIL = "item_detail";
        public static final String COLUMN_NAME_IS_PASSWORD = "is_password";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_LAST_UPDATE = "last_update";
    }
}
