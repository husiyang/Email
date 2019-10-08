package com.example.email;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText e1 ;
    EditText e2 ;
    EditText e3 ;
    EditText e4 ;
    EditText e5 ;
    EditText e6 ;
    Button clear;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        jump();
        display();
    }

    public void init(){
        e1 = findViewById(R.id.edit1);
        e2 = findViewById(R.id.edit2);
        e3 = findViewById(R.id.edit3);
        e4 = findViewById(R.id.edit4);
        e5 = findViewById(R.id.edit5);
        e6 = findViewById(R.id.edit6);
        clear = findViewById(R.id.button1);
        clear.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
            }
        });
    }
    public void jump(){
        send = findViewById(R.id.button2);
        send.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                String from = ((EditText)findViewById(R.id.edit1)).getText().toString();
                String to = ((EditText)findViewById(R.id.edit2)).getText().toString();
                String cc = ((EditText)findViewById(R.id.edit3)).getText().toString();
                String bcc = ((EditText)findViewById(R.id.edit4)).getText().toString();
                String subject = ((EditText)findViewById(R.id.edit5)).getText().toString();
                String body = ((EditText)findViewById(R.id.edit6)).getText().toString();
                final SharedPreferences sp = getSharedPreferences("store", Context.MODE_PRIVATE);
                if (!"".equals(from) && !"".equals(to) && !"".equals(subject) && !"".equals(body)){
                    Intent intent = new Intent(MainActivity.this, readingLayout.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("from",from);
                    bundle.putCharSequence("to",to);
                    bundle.putCharSequence("cc",cc);
                    bundle.putCharSequence("bcc",bcc);
                    bundle.putCharSequence("subject",subject);
                    bundle.putCharSequence("body",body);
                    intent.putExtras(bundle);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("from",from);
                    editor.putString("to",to);
                    editor.putString("cc",cc);
                    editor.putString("bcc",bcc);
                    editor.putString("subject",subject);
                    editor.putString("body",body);
                    editor.commit();
                    Toast.makeText(MainActivity.this,"input has been preserved", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
    public void display(){
        SharedPreferences sp = getSharedPreferences("store",Context.MODE_PRIVATE);
        String from = sp.getString("from","");
        String to = sp.getString("to","");
        String cc = sp.getString("cc","");
        String bcc = sp.getString("bcc","");
        String subject = sp.getString("subject","");
        String body = sp.getString("body","");
        TextView tv_from = findViewById(R.id.edit1);
        TextView tv_to = findViewById(R.id.edit2);
        TextView tv_cc = findViewById(R.id.edit3);
        TextView tv_bcc = findViewById(R.id.edit4);
        TextView tv_subject = findViewById(R.id.edit5);
        TextView tv_body = findViewById(R.id.edit6);
        tv_from.setText(from);
        tv_to.setText(to);
        tv_cc.setText(cc);
        tv_bcc.setText(bcc);
        tv_subject.setText(subject);
        tv_body.setText(body);
    }
}
