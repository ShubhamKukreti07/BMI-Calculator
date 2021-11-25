package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserDetailActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    EditText edtName,edtAge,edtHeight,edtWeight;
    Spinner spnrGender;
    Button btnCalculateBmi;
    String[] gender = {"Select Gender","Male","Female","other" };
    String selectedGender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        edtName=findViewById(R.id.edtName);
        edtAge=findViewById(R.id.edtAge);
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);
        spnrGender=findViewById(R.id.spnrGender);
        btnCalculateBmi=findViewById(R.id.btnCalculateBmi);

        spnrGender.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                gender);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);


        spnrGender.setAdapter(ad);


        btnCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()){
                    float weight= Float.valueOf(edtWeight.getText().toString());
                    float height= Float.valueOf(edtHeight.getText().toString())/100;
                    float BMI = weight / (height * height);

                    Intent intent=new Intent(UserDetailActivity.this,CalculateBmiActivity.class);
                    intent.putExtra("NAME",edtName.getText().toString());
                    intent.putExtra("AGE",edtAge.getText().toString());
                    intent.putExtra("BMI",BMI);
                    intent.putExtra("HEIGHT",edtWeight.getText().toString());
                    intent.putExtra("WEIGHT",edtWeight.getText().toString());
                    intent.putExtra("GENDER",selectedGender);
                    startActivity(intent);

                }
            }
        });


    }

    public boolean isValidate(){
        if(edtName.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter namme",Toast.LENGTH_LONG).show();
            return false;
        }else if(edtAge.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            return false;
        }else if(edtHeight.getText().toString().isEmpty()){
            Toast.makeText(this,"please enter your height",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(edtWeight.getText().toString().isEmpty()){
            Toast.makeText(this,"please enter your weight",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(selectedGender.isEmpty()){
            Toast.makeText(this,"please select gender",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i==0){
            selectedGender="";
        }else{
            selectedGender=gender[i];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}