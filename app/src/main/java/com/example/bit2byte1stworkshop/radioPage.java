package com.example.bit2byte1stworkshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class radioPage extends AppCompatActivity {

    Button applyButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_page);

        applyButton = (Button)findViewById(R.id.button);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup2);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(radioId);
                //Toast.makeText(radioPage.this, "clicked "+radioButton.getText(), Toast.LENGTH_SHORT).show();
                String value = radioButton.getText().toString().trim();
                if(value.equals("Kg-Meter")){

                    Intent i = new Intent(radioPage.this,MainActivity.class);
                    startActivity(i);
                }
                else{

                    Intent i = new Intent(radioPage.this,MainActivityKgFeet.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}