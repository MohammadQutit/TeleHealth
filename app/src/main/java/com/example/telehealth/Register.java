package com.example.telehealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Register extends AppCompatActivity
{
Spinner s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String[] arraySpinner = new String[32] ;
        arraySpinner[0]="Day";
        for(int i=1;i<32;i++)
        {
            arraySpinner[i]=""+i;
        }
String[] month=
        new String[]{"Month","January", "February", "March" ,
        "April" , "May" , "June" , "July" ,
        "August" + "September" , "October",  "November" ,"December"};

        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);

        s1.setAdapter(adapter);
        s2.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month));

    }

}
