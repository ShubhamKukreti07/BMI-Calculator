package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalculateBmiActivity extends AppCompatActivity {
TextView tvName,tvBmiValue,tvCategory;
Button logout;
String name, age,height,weight,gender;
LinearLayout mainLayout;
float bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
        tvName=findViewById(R.id.tvName);
        tvBmiValue=findViewById(R.id.tvBmiValue);
        tvCategory=findViewById(R.id.tvBmiCat);
        logout=findViewById(R.id.btnlogout);
        mainLayout=findViewById(R.id.mainLayout);

        Intent intent= getIntent();
        name=intent.getStringExtra("NAME");
        age=intent.getStringExtra("AGE");
        bmi=Math.round((intent.getFloatExtra("BMI", 0) * 100) / 100);
        height=intent.getStringExtra("HEIGHT");
        weight=intent.getStringExtra("WEIGHT");
        gender=intent.getStringExtra("GENDER");

        if(gender.equals("Male")){
            mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.blue));
        }
        else if(gender.equals("Female")){
            mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.pink));
        }else if(gender.equals("other")){
            mainLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.purple));
        }

        tvName.setText("Hi, "+name);

        tvBmiValue.setText("BMI Value = "+bmi);

        String cat=getCategory(bmi);
        tvCategory.setText("You are in "+cat+"category");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalculateBmiActivity.this,LoginActivity.class));
                finishAffinity();
            }
        });

    }

    public String getCategory (float result) {
        String category;
        if (result < 15) {
            category = "Very severely underweight";
        } else if (result >=15 && result <= 16) {
            category = "Severely underweight";
        } else if (result >16 && result <= 18.5) {
            category = "Underweight";
        } else if (result >18.5 && result <= 25) {
            category = "Normal (healthy weight)";
        } else if (result >25 && result <= 30) {
            category = "Overweight";
        } else if (result >30 && result <= 35) {
            category = "Moderately obese";
        } else if (result >35 && result <= 40) {
            category = "Severely obese";
        } else {
            category ="Very severely obese";
        }
        return category;
    }
}