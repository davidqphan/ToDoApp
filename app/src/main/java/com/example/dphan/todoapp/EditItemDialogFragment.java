package com.example.dphan.todoapp;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dphan on 8/7/17.
 */

public class EditItemDialogFragment extends DialogFragment {

    private TodoItem todoItem;
    private EditText etEditItem;
    private Button btnEditItem;

    public EditItemDialogFragment() {
    }

    public static EditItemDialogFragment newInstance(TodoItem todoItem, int position) {
        EditItemDialogFragment frag = new EditItemDialogFragment();
        frag.setTodoItem(todoItem);
        Bundle args = new Bundle();
        args.putInt("pos", position);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setRetainInstance(true);
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        setUpEditTextField(view);
        setUpSaveButton(view);
    }

    private void setUpEditTextField(View view) {
        etEditItem = (EditText)view.findViewById(R.id.etEditItem);
        etEditItem.requestFocus();

        if(todoItem.getTodoName() != null) {
            etEditItem.append(todoItem.getTodoName());
        }

        setUpEditTextListener();
    }

    private void setUpEditTextListener() {
        etEditItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnEditItem.setEnabled(charSequence.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpSaveButton(View view) {
        btnEditItem = (Button)view.findViewById(R.id.btnEditItem);

        btnEditItem.setEnabled(etEditItem.getText().length() > 0);
        btnEditItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                todoItem.setName(etEditItem.getText().toString());

                if(todoItem.getId() == 0) {
                    todoItem.setId(System.currentTimeMillis());
                }

                EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                listener.onFinishEditDialog(todoItem, getArguments().getInt("pos"));

                dismiss();
            }
        });
    }

    public interface EditItemDialogListener {
        void onFinishEditDialog(TodoItem todoItem, int position);
    }
}
