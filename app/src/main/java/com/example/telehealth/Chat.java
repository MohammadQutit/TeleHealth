package com.example.telehealth;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Chat extends AppCompatActivity
{
    LinearLayout layout;
    RelativeLayout layout_2;

    ImageView sendButton;

    EditText messageArea;

    ScrollView scrollView;

    DatabaseReference reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout)findViewById(R.id.layout2);

        sendButton = (ImageView)findViewById(R.id.sendButton);

        messageArea = (EditText)findViewById(R.id.messageArea);

        scrollView = (ScrollView)findViewById(R.id.scrollView);


        reference1=FirebaseDatabase.getInstance().getReference().
                child("messages").child(UserDetails.username+"_"+UserDetails.chatWith);
        reference2=FirebaseDatabase.getInstance().getReference().
                child("messages").child(UserDetails.chatWith+"_"+UserDetails.username);



        sendButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals(""))
                {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });


        reference1.addChildEventListener(new ChildEventListener()
        {


            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if (userName.equals(UserDetails.username))
                {
                    addMessageBox("You:-\n" + message, 1);
                }
                else
                    {
                    addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                    }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

        }) ;

    }


    public void addMessageBox(String message, int type)
    {
        TextView textView = new TextView(Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        lp2.weight = 1.0f;

        if(type == 1)
        {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        }
        else
        {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }

        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }

}