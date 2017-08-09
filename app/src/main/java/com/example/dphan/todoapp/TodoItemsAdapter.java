package com.example.dphan.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dphan on 8/5/17.
 */

public class TodoItemsAdapter extends ArrayAdapter<TodoItem> {

    public TodoItemsAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, R.layout.item_todo, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TodoItem todoItem = getItem(position);
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvTodoItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(todoItem.getTodoName());

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
    }
}
