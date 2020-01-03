package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
Intent i;
DatabaseReference DB;
EditText id,pass;
CheckBox rem;
String acc,password;
ProgressDialog load;
SharedPreferences reme;
Bundle b;
boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DB= FirebaseDatabase.getInstance().getReference();
        id=(EditText)findViewById(R.id.ID);
        pass=(EditText)findViewById(R.id.Password);
        rem=(CheckBox)findViewById(R.id.stay);

        reme=getSharedPreferences("myapp",MODE_PRIVATE);
        getPrefe();

        load=new ProgressDialog(this);
        load.setTitle("access to mainpage");
        load.setMessage("please wait a moment");
        load.setCanceledOnTouchOutside(false);



    }

     protected void onSaveInstanceState(Bundle outState)
    {

        if(checked)
        {
            outState.putString("id",id.getText().toString());
            outState.putString("pass",pass.getText().toString());
            outState.putBoolean("checked",checked);
            super.onSaveInstanceState(outState);
        }

        else super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        id.setText(savedInstanceState.getString("id"));
        pass.setText(savedInstanceState.getString("pass"));
        rem.setChecked(savedInstanceState.getBoolean("checked"));
        checked=rem.isChecked();
    }

    public void onClickRegHere(View v)
    {

        i=new Intent(this,Register.class);
        startActivity(i);
    }

    public void getPrefe()
    {
        if (reme.contains("id"))
        {
            id.setText(reme.getString("id","not found"));
        }
        if (reme.contains("pass"))
        {
            pass.setText(reme.getString("pass","not found"));
        }
        if (reme.contains("checked"))
        {
            rem.setChecked(reme.getBoolean("checked",false));
        }
    }

    public void prefe()
    {

        if(rem.isChecked())
        {
            checked=rem.isChecked();
            SharedPreferences.Editor editor=reme.edit();
            editor.putString("id",id.getText().toString());
            editor.putString("pass",pass.getText().toString());
            editor.putBoolean("checked",checked);
            editor.apply();
        }
        else {reme.edit().clear().apply();}

    }

    public void onClickLogin(View v)
    {
      //  i = new Intent(MainActivity.this, Mainpage.class);
      //  startActivity(i);

      prefe();
      acc=id.getText().toString();
      password=pass.getText().toString();

      if(!TextUtils.isEmpty(acc))
      {

         if(!TextUtils.isEmpty(password))
         {

            DB.addListenerForSingleValueEvent(new ValueEventListener()
            {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if (dataSnapshot.child("Users").child(acc).exists())
                    {
                        if (dataSnapshot.child("Users").child(acc).child("password").getValue().equals(password))
                        {
                            b=new Bundle();

                            load.show();

                            i = new Intent(MainActivity.this, Mainpage.class);
                            b.putString("phone",acc);
                            i.putExtras(b);

                            startActivity(i);

                        }
                        else
                            Toast.makeText(getApplicationContext(), "your password is wrong", Toast.LENGTH_SHORT).show();


                    }
                    else
                        Toast.makeText(getApplicationContext(), "your phone number is invalid", Toast.LENGTH_SHORT).show();


                }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError)
                 {

                 }

            });


         }else{Toast.makeText(getApplicationContext(),"please enter your password",Toast.LENGTH_SHORT).show();}



      }else{Toast.makeText(getApplicationContext(),"please enter your phone number",Toast.LENGTH_SHORT).show();}




    }

}



