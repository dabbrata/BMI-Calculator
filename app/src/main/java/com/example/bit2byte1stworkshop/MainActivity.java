package com.example.bit2byte1stworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText weight,height;
    TextView bmitext,levelText,conditionText;
    Button bmiButton;
    ImageView imageView;
    ImageButton backButton,refreshButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        weight = (EditText)findViewById(R.id.weightId);
        height = (EditText)findViewById(R.id.heightMeterId);
        bmitext = (TextView)findViewById(R.id.textView6);
        levelText = (TextView)findViewById(R.id.textView7);
        conditionText = (TextView)findViewById(R.id.textView8);
        bmiButton = (Button)findViewById(R.id.button2);
        imageView = (ImageView)findViewById(R.id.imageView2);
        backButton = (ImageButton)findViewById(R.id.imageButton);
        refreshButton = (ImageButton)findViewById(R.id.imageButton2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,radioPage.class);
                startActivity(intent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = weight.getText().toString().trim();
                String heightStr = height.getText().toString().trim();
                if(weightStr.isEmpty()){
                    weight.setError("provide your weight in (Kg)");
                    weight.requestFocus();
                    return;
                }
                if(heightStr.isEmpty()){
                    height.setError("provide your height in (Meter)");
                    height.requestFocus();
                    return;
                }

                double w = Double.parseDouble(weightStr);
                double h = Double.parseDouble(heightStr);

                double bmi = (double)w/(h*h);
                String strDouble = String.format("%.2f", bmi);
                Double num = Double.parseDouble(strDouble);

                if(num < 18.5){
                    bmitext.setText("Your BMI : "+num);
                    levelText.setText("Category : UnderWeight");
                    conditionText.setText("Physical Condition : NOT GOOD!");
                    imageView.setImageResource(R.drawable.bmi_low);
                }
                else if(num >=18.5 && num <=24.9){
                    bmitext.setText("Your BMI : "+num);
                    levelText.setText("Category : Normal");
                    conditionText.setText("Physical Condition : GOOD!");
                    imageView.setImageResource(R.drawable.bmi_normal);
                }
                else if(num >24.9 && num <=29.9){
                    bmitext.setText("Your BMI : "+num);
                    levelText.setText("Category : OverWeight");
                    conditionText.setText("Physical Condition : NOT BAD!");
                    imageView.setImageResource(R.drawable.bmi_overweight);
                }
                else if(num >29.9 && num <=34.9){
                    bmitext.setText("Your BMI : "+num);
                    levelText.setText("Category : Obese(Fat)");
                    conditionText.setText("Physical Condition : BAD!");
                    imageView.setImageResource(R.drawable.bmi_fat1);
                }
                else{
                    bmitext.setText("Your BMI : "+num);
                    levelText.setText("Category : ExtremelyObese(OverFat)");
                    conditionText.setText("Physical Condition : VERY BAD!");
                    imageView.setImageResource(R.drawable.bmi_fat2);
                }


            }
        });
    }
}