package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Mainpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
private int image[]={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4};
private  ViewFlipper viewFlipper;
private ImageButton conditions,center,early,listot;

Intent i2;


DatabaseReference DB;
DatabaseReference d;
String name;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewFlipper=findViewById(R.id.flibber);
        drawerLayout=findViewById(R.id.drawerlayout);
        center=(ImageButton)findViewById(R.id.center);
        early=(ImageButton)findViewById(R.id.early);
        listot=(ImageButton)findViewById(R.id.listot);
        conditions=(ImageButton)findViewById(R.id.conditions);
        conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionOpen();
            }
        });

        early.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screeningOpen();
            }
        });

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
         i=getIntent();
         getmap();
         getchat();


        for(int img:image){
            change(img);
        }
        DB= FirebaseDatabase.getInstance().getReference();

        DB.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
               name= dataSnapshot.child("Users").child(i.getStringExtra("phone").toString()).child("full name").getValue().toString();

               UserDetails.username=name;
               TextView textView=findViewById(R.id.UserMP);
               textView.setText(name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }




    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();
    }}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_about:
                Intent intent=new Intent(this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent i=new Intent(Mainpage.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_at:
                Intent x=new Intent(Mainpage.this,at.class);
                x.putExtra("key","Assessment-tools");
                startActivity(x);
            case R.id.nav_milestone:
                Intent x1=new Intent(this,at.class);
                x1.putExtra("key","DEVELOPMENT-OF-COGNITIVE-SKILLS");
                startActivity(x1);

        }


drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getmap()
    {

        center.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               i2=new Intent(Mainpage.this,MapsActivity.class);
               startActivity(i2);
            }
        });

    }

    public void getchat()
    {

        listot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i2=new Intent(Mainpage.this,Users.class);
                startActivity(i2);
            }


    }


    private  void change(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2500);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);


    }
    private void conditionOpen(){
        Intent i=new Intent(this, com.example.telehealth.conditions.class);
        startActivity(i);

    }

    private void screeningOpen(){
        Intent early=new Intent(this,Early_Screening.class);
        early.putExtra("name",i.getStringExtra(name));
        startActivity(early);


    }

}
