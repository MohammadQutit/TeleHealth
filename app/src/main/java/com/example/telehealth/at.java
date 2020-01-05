package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URLEncoder;

public class at extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private WebView webView;
    DatabaseReference databaseReference = database.getReference();
    DatabaseReference childReference;


    private  String pdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at);
        Intent e=getIntent();
        childReference=databaseReference.child("assesment").child(e.getStringExtra("key"));;


        webView=findViewById(R.id.webview3);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);







    }



    @Override
    protected void onStart() {
        super.onStart();
        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pdf=dataSnapshot.getValue(String.class);

                try {

                    String x= URLEncoder.encode(pdf);
                    webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+x);
                }catch (Exception e){

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
