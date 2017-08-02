package com.example.dphan.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLOutput;

/**
 * Created by dphan on 8/1/17.
 */

public class EditItemActivity extends AppCompatActivity {

    private String editText;
    private int positionText;
    private EditText etMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editText = getIntent().getStringExtra("itemText");
        positionText = getIntent().getIntExtra("positionText", 0);

        etMulti = (EditText) findViewById(R.id.etMulti);
        etMulti.setText(editText);
        etMulti.setSelection(editText.length());
    }

    public void onSave(View view) {

        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("itemText", etMulti.getText().toString());
        data.putExtra("positionText", positionText); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

}
