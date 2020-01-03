package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
String acc,password;
ProgressDialog load;
Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DB= FirebaseDatabase.getInstance().getReference();
        id=(EditText)findViewById(R.id.ID);
        pass=(EditText)findViewById(R.id.Password);
        load=new ProgressDialog(this);
        load.setTitle("access to mainpage");
        load.setMessage("please wait a moment");
        load.setCanceledOnTouchOutside(false);



    }

    public void onClickRegHere(View v)
    {


        i=new Intent(this,Register.class);
        startActivity(i);

    }
    public void onClickLogin(View v)
    {
        i = new Intent(MainActivity.this, Mainpage.class);
        startActivity(i);

      /*acc=id.getText().toString();
      password=pass.getText().toString();
      if(!TextUtils.isEmpty(acc))
      {
        if(!TextUtils.isEmpty(password))
        {
            DB.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Users").child(acc).exists()) {
                        if (dataSnapshot.child("Users").child(acc).child("password").getValue().equals(password)) {
                            load.show();
                            i = new Intent(MainActivity.this, Mainpage.class);
                            b=new Bundle();
                            b.putString("phone",acc);
                            i.putExtras(b);
                            startActivity(i);
                        } else
                            Toast.makeText(getApplicationContext(), "your password is wrong", Toast.LENGTH_SHORT).show();
                        ;
                    } else
                        Toast.makeText(getApplicationContext(), "your id is invalid", Toast.LENGTH_SHORT).show();
                    ;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{Toast.makeText(getApplicationContext(),"please enter your password",Toast.LENGTH_SHORT).show();}

    }else{Toast.makeText(getApplicationContext(),"please enter your email",Toast.LENGTH_SHORT).show();}



   */ }

}
