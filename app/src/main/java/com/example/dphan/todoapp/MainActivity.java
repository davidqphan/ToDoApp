package com.example.dphan.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoItem> items;
    TodoItemsAdapter itemsAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachAdapterToListView();

        setUpListViewListener();
    }

    private void attachAdapterToListView() {

        lvItems = (ListView)findViewById(R.id.lvItems);

        items = new ArrayList<>(SQLite.select().from(TodoItem.class).queryList());

        itemsAdapter = new TodoItemsAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
    }

    private void setUpListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                           View item, int pos, long id) {
                        TodoItem todoItem = items.get(pos);

                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();

                        todoItem.delete();
                        return true;
                    }
        });

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        Intent editItemIntent = new Intent(getApplicationContext(), EditItemActivity.class);
                        editItemIntent.putExtra("itemText", items.get(pos).getTodoName());
                        editItemIntent.putExtra("positionText", pos);
                        startActivityForResult(editItemIntent, REQUEST_CODE);
                    }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String itemText = data.getExtras().getString("itemText");
            int positionText = data.getExtras().getInt("positionText", 0);
            TodoItem todoItem = new TodoItem(itemText);
            items.set(positionText, todoItem);
            todoItem.save();
        }
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText)findViewById(R.id.etEditText);
        String itemText = etNewItem.getText().toString();
        TodoItem todoItem = new TodoItem(itemText);
        itemsAdapter.add(todoItem);
        etNewItem.setText("");
        todoItem.save();
    }
}
