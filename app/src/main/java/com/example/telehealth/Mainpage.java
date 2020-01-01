package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class Mainpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayout);

       ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigatio_menu, menu);
        return true;
    }*/

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();
    }}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.nav_logout){
            Toast.makeText(this,"asdasda",Toast.LENGTH_LONG).show();
        }



drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
