package com.example.telehealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.app.ProgressDialog;


import org.w3c.dom.Text;

import java.lang.String;
import java.util.HashMap;
import java.util.Timer;
import java.util.regex.Pattern;



public class Register extends AppCompatActivity
{
    DatabaseReference DB;
    Intent  P;
    ProgressDialog load;
    Spinner s1,s2;
    String[] arraySpinner;
    private Button reg;
    private EditText email,password,phone,confirmpassword,fullname,year;
    private String emailtovalidate,passwordtovalidate,phonetovalidate,n,e,ph,pass,d,m,y;

    private boolean valideEmail=false,validePassword=false,
            validePhone=false,cpass=false,valideDay=false,valideMonth=false;


    String emailPattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final Pattern  phonePattern=Pattern.compile("\\(?\\d+\\)?[-.\\s]?\\d+[-.\\s]?\\d+");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DB= FirebaseDatabase.getInstance().getReference();
        fillBirthDaySpinner();
        load=new ProgressDialog(this);
        load.setTitle("create account");
        load.setMessage("please wait a moment");
        load.setCanceledOnTouchOutside(false);

        year=(EditText)findViewById(R.id.year);
        fullname=(EditText)findViewById(R.id.e1);
        email=(EditText)findViewById(R.id.e2);
        phone=(EditText)findViewById(R.id.e3);
        password=(EditText)findViewById(R.id.e4);
        confirmpassword=(EditText)findViewById(R.id.e5);
        reg=(Button)findViewById(R.id.regi);

        emailToValidate();
        phoneToValidte();
        passwordToValidte();
        confirmPassword();
        validateSpeD();
        validateSpeM();


        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ValidationAcc();
            }
        });

    }


    public void ValidationAcc()
    {
        if(valideEmail==true)
        {
            if(validePhone==true)
            {
                if(validePassword==true)
                {
                    if(cpass==true)
                    {
                        if(valideDay==true)
                        {
                            if(valideMonth==true)
                            {
                                if(!TextUtils.isEmpty(year.getText()))
                                {

                                    load.show();
                                    createAcc();

                                }else {Toast.makeText(getApplicationContext(),"fill year field",Toast.LENGTH_SHORT).show();}
                            }else {Toast.makeText(getApplicationContext(),"enter a name of month",Toast.LENGTH_SHORT).show();}
                        }else {Toast.makeText(getApplicationContext(),"enter a day of month",Toast.LENGTH_SHORT).show();}
                    }else {Toast.makeText(getApplicationContext(),"confirmed password is wrong",Toast.LENGTH_SHORT).show();}
                }else {Toast.makeText(getApplicationContext(),"invalid password",Toast.LENGTH_SHORT).show();}
            }else {Toast.makeText(getApplicationContext(),"invalid phone number",Toast.LENGTH_SHORT).show();}
        }else {Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_SHORT).show();}

    }

    public void createAcc()
    {

        n=fullname.getText().toString();
        e=email.getText().toString();
        ph=phone.getText().toString();
        pass=password.getText().toString();
        d=s1.getSelectedItem().toString();
        m=s2.getSelectedItem().toString();
        y=year.getText().toString();
        DB.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                if(!dataSnapshot.child("Users").child(ph).exists())
                {
                    HashMap<String,Object> h=new HashMap<>();
                    h.put("full name",n);
                    h.put("email",e);
                    h.put("phone",ph);
                    h.put("password",pass);
                    h.put("day",d);
                    h.put("month",m);
                    h.put("year",y);


                    DB.child("Users").child(ph).updateChildren(h).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_LONG).show();
                                load.dismiss();
                                P=new Intent(Register.this,MainActivity.class);
                                startActivity(P);
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"faild connection",Toast.LENGTH_SHORT).show();
                                load.dismiss();

                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"this phone number"+ph+"is already exists",Toast.LENGTH_SHORT).show();
                    load.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }

    public void validateSpeD()
    {
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if(s1.getSelectedItem().toString().compareTo("Day")!=0)
                {
                    valideDay=true;
                }else
                {
                    valideDay=false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });


    }

    public void validateSpeM()
    {
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                if(s2.getSelectedItem().toString().compareTo("Month")!=0)
                {
                    valideMonth=true;
                }else
                {
                    valideMonth=false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



    }


    //confirm password
    public void confirmPassword()
    {

        confirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            int i=0;
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(confirmpassword.getText().toString().compareTo(passwordtovalidate)==0)
                {
                    Toast.makeText(getApplicationContext(),"password is confirmed",Toast.LENGTH_SHORT).show();
                    cpass=true;

                }
                else
                {
                    if(i==0){Toast.makeText(getApplicationContext(),"reWrite password ",Toast.LENGTH_SHORT).show();i=1;}
                    else Toast.makeText(getApplicationContext(),"confirmed password is wrong , retry",Toast.LENGTH_SHORT).show();
                    cpass=false;
                }
            }
        });

    }


    // fill birthday method
    public void fillBirthDaySpinner()
    {


        arraySpinner = new String[32] ;
        arraySpinner[0]="Day";
        for(int i=1;i<32;i++)
        {
            arraySpinner[i]=""+i;
        }

        String[] month=
                new String[]{"Month","January", "February", "March" ,
                        "April" , "May" , "June" , "July" ,
                        "August" , "September" , "October",  "November" ,"December"};

        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);

        s1.setAdapter(adapter);
        s2.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month));


    }


    //password validation method
    public void passwordToValidte()
    {
        password.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            int i=0;
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

                passwordtovalidate=password.getText().toString().trim();

                if (PASSWORD_PATTERN.matcher(passwordtovalidate).matches())
                {
                    Toast.makeText(getApplicationContext(), "valid password", Toast.LENGTH_SHORT).show();
                    validePassword = true;
                }
                else
                {
                    if(i==0){Toast.makeText(getApplicationContext(),"write valid password,at least 8 characters,1 digit,1 lower case and upper case letter",Toast.LENGTH_LONG).show();i=1;}
                    else Toast.makeText(getApplicationContext(), "invalid password, write valid password,at least 8 characters,1 digit,1 lower case and upper case letter", Toast.LENGTH_LONG).show();
                    validePassword = false;
                }

            }
        });

    }


    //phone validation method
    public void phoneToValidte()
    {
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            int i=0;
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {

                phonetovalidate=phone.getText().toString().trim();
                if (phonePattern.matcher(phonetovalidate).matches())
                {
                    Toast.makeText(getApplicationContext(),"valid phone number",Toast.LENGTH_SHORT).show();
                    validePhone=true;
                }
                else
                {
                    if(i==0){Toast.makeText(getApplicationContext(),"Write valid phone number",Toast.LENGTH_SHORT).show();i=1;}
                    else Toast.makeText(getApplicationContext(),"invalid phone number",Toast.LENGTH_SHORT).show();
                    validePhone=false;
                }
            }
        });

    }

    //email validation method
    public void emailToValidate()
    {

        email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            int i=0;
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                emailtovalidate = email.getText().toString().trim();

                if (emailtovalidate.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    valideEmail=true;
                }
                else
                {
                    if(i==0){Toast.makeText(getApplicationContext(),"Write valid email address",Toast.LENGTH_SHORT).show();i=1;}
                    else Toast.makeText(getApplicationContext(),"invalid email address",Toast.LENGTH_SHORT).show();
                    valideEmail=false;
                }

            }
        });

    }


}
