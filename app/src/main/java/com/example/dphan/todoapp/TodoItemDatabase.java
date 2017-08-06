package com.example.dphan.todoapp;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by dphan on 8/5/17.
 */

@Database(name = TodoItemDatabase.NAME, version = TodoItemDatabase.VERSION)
public class TodoItemDatabase {

    public static final String NAME = "TodoItemDataBase";

    public static final int VERSION = 1;
}
