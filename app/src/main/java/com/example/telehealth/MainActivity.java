package com.example.telehealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
{
Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickRegHere(View v)
    {


        i=new Intent(this,Register.class);
        startActivity(i);

    }
    public void onClickLogin(View v)
    {


        i=new Intent(this,Mainpage.class);
        startActivity(i);

    }

}
