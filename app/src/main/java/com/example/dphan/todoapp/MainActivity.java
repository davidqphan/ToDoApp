package com.example.dphan.todoapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditItemDialogFragment.EditItemDialogListener {
    ArrayList<TodoItem> items;
    TodoItemsAdapter itemsAdapter;
    ListView lvItems;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                setUpDialogFragmentForNewItem();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpDialogFragmentForNewItem() {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialogFragment editItemDialogFragment = EditItemDialogFragment
                .newInstance(new TodoItem(), items.size());
        editItemDialogFragment.show(fm, "fragment_edit_item");
    }

    private void setUpListViewListener() {
        setUpRemoveItemListener();
        setUpEditItemListener();
    }

    private void setUpRemoveItemListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                TodoItem todoItem = items.get(pos);

                items.remove(pos);
                itemsAdapter.notifyDataSetChanged();

                todoItem.delete();
                return true;
            }
        });
    }

    private void setUpEditItemListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                setUpDialogFragmentForEdit(pos);
            }
        });
    }

    private void setUpDialogFragmentForEdit(int pos) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialogFragment editItemDialogFragment = EditItemDialogFragment
                .newInstance(items.get(pos), pos);
        editItemDialogFragment.show(fm, "fragment_edit_item");
    }

    @Override
    public void onFinishEditDialog(TodoItem todoItem, int position) {
        todoItem.save();

        if(position != items.size()) {
            items.set(position, todoItem);
        } else {
            items.add(todoItem);
        }
        itemsAdapter.notifyDataSetChanged();
    }
}
