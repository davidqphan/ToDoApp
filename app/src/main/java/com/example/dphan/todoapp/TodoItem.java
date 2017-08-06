package com.example.dphan.todoapp;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by dphan on 8/5/17.
 */


@Table(database = TodoItemDatabase.class)
public class TodoItem extends BaseModel {

    @Column
    @PrimaryKey
    long id;

    @Column
    String todoName;

    public TodoItem() {

    }

    public TodoItem(String name) {
        this.todoName = name;
    }

    public void setName(String name) {
        this.todoName = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodoName() {
        return todoName;
    }

    public long getId() {
        return id;
    }
}
