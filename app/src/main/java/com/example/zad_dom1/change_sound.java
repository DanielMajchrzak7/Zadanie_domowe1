package com.example.zad_dom1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class change_sound extends AppCompatActivity  {

    private Button submitButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sound);

        String[] tabSpinner = new String[]{
          "Ringtone1", "Ringtone2","Ringtone3"
        };
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, tabSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cancelButton = (Button)findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner submitSpinner = (Spinner) findViewById(R.id.spinner);
                int ringtone = submitSpinner.getSelectedItemPosition();
                Intent data = new Intent();
                data.putExtra(MainActivity.RING, ringtone);
                setResult(Activity.RESULT_OK,data);
                finish();
            }
        });
    }
}
