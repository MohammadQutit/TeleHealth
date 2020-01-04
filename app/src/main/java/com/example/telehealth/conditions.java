package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class conditions extends AppCompatActivity {
    private ArrayList<String> names=new ArrayList<String>();
   private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
   private DatabaseReference all=firebaseDatabase.getReference();
   private DatabaseReference conditions=all.child("conditions");
   private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        listView=findViewById(R.id.list);
        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosecondition(position);

            }
        });

        conditions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String myvalues=dataSnapshot.getKey();
                names.add(myvalues);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void choosecondition(int pos){
        Intent i=new Intent(this,pdfviewer.class);
        i.putExtra("key",names.get(pos));
        Toast.makeText(this,names.get(pos),Toast.LENGTH_LONG).show();
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);

                }catch (Exception e){

                }

            }
        };
        thread.start();
        startActivity(i);

    }


}
