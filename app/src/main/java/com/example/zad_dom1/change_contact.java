package com.example.zad_dom1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class change_contact extends AppCompatActivity {

    private Button cancelButton;
    private Button submitButton;
    private RadioGroup radioGroup;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checked = false;

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
                if(checked)
                {
                    RadioButton checkedRadio = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                    String zaznaczoneNazwisko = checkedRadio.getText().toString();
                    Intent data = new Intent();
                    data.putExtra(MainActivity.NAZWISKO, zaznaczoneNazwisko);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        checked = true;
    }
}
