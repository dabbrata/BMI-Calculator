package com.example.bit2byte1stworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityKgFeet extends AppCompatActivity {

    EditText weight,height;
    TextView bmitext,levelText,conditionText;
    Button bmiButton;
    ImageView imageView;
    ImageButton backButton,refreshButton;
    int cnt=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kg_feet);
        getSupportActionBar().hide();

        weight = (EditText)findViewById(R.id.weightId);
        height = (EditText)findViewById(R.id.heightFeetId);
        bmitext = (TextView)findViewById(R.id.textView6);
        levelText = (TextView)findViewById(R.id.textView7);
        conditionText = (TextView)findViewById(R.id.textView8);
        bmiButton = (Button)findViewById(R.id.button2);
        imageView = (ImageView)findViewById(R.id.imageView);
        backButton = (ImageButton)findViewById(R.id.imageButton);
        refreshButton = (ImageButton)findViewById(R.id.imageButton2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityKgFeet.this,radioPage.class);
                startActivity(intent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivityKgFeet.this,MainActivityKgFeet.class);
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
                    height.setError("provide your height in (Feet)");
                    height.requestFocus();
                    return;
                }
                if(heightStr.length()==1){
                    height.setError("Height formation is like 5.0!");
                    height.requestFocus();
                    return;
                }
                Double number1 = Double.parseDouble(heightStr);
                if(number1>=10.0){
                    height.setError("Unreal height.Height must be less than 10 Feet!");
                    height.requestFocus();
                    return;
                }
                String[] arrOfStr = heightStr.split("\\.");
                double w = Double.parseDouble(weightStr);
                double h = Double.parseDouble(heightStr);
                Double startNum = Math.floor(h);
                Double startNumCmp = Math.ceil(h);
                String string = arrOfStr[1];
                Double endNum = Double.parseDouble(string);
                if(endNum>11.0){
                    height.setError("After decimal point number(inch) must not greater than 11!");
                    height.requestFocus();
                    return;
                }
                Double ftom = (startNum*0.3048) + (endNum*0.0254);
                double bmi = (double)w/(ftom*ftom);
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