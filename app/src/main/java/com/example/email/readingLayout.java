package com.example.email;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class readingLayout extends Activity {
    Button back;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_layout);
        back();
        getInfo();
        sendInfo();
    }

    public void back(){
        back = findViewById(R.id.button3);
        back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(readingLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getInfo(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String from = bundle.getString("from");
        String to = bundle.getString("to");
        String cc = bundle.getString("cc");
        String bcc = bundle.getString("bcc");
        String subject = bundle.getString("subject");
        String body = bundle.getString("body");
        TextView tv_from = findViewById(R.id.info1);
        TextView tv_to = findViewById(R.id.info2);
        TextView tv_cc = findViewById(R.id.info3);
        TextView tv_bcc = findViewById(R.id.info4);
        TextView tv_subject = findViewById(R.id.info5);
        TextView tv_body = findViewById(R.id.info6);
        tv_from.setText(from);
        tv_to.setText(to);
        tv_cc.setText(cc);
        tv_bcc.setText(bcc);
        tv_subject.setText(subject);
        tv_body.setText(body);
    }
    public void sendInfo(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String[] to = {bundle.getString("to")};
        final String[] cc = {bundle.getString("cc")};
        final String[] bcc = {bundle.getString("bcc")};
        final String subject = bundle.getString("subject");
        final String body = bundle.getString("body");
        send = findViewById(R.id.button5);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, to);
                email.putExtra(android.content.Intent.EXTRA_CC, cc);
                email.putExtra(android.content.Intent.EXTRA_BCC, bcc);
                email.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
                email.putExtra(android.content.Intent.EXTRA_TEXT, body);
                Intent.createChooser(email,"choose email");
                startActivity(email);
            }
        });
    }
}
