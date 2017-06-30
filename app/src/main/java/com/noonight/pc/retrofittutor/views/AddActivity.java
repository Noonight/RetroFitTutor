package com.noonight.pc.retrofittutor.views;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.noonight.pc.retrofittutor.R;

public class AddActivity extends AppCompatActivity {

    private EditText etNumber;
    private EditText etText;
    private FloatingActionButton faBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        etNumber = (EditText) findViewById(R.id.etNumber);
        etText = (EditText) findViewById(R.id.etText);
        faBtnAdd = (FloatingActionButton) findViewById(R.id.faBtnAdd);

        faBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
