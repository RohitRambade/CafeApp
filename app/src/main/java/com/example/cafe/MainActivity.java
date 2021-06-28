package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    private Button searchOrder;
    private Button call_phone;
//    TextView txtMarquee;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
//        txtMarquee=(TextView) findViewById(R.id.marquee) ;
//        txtMarquee.setSelected(true);
//        TextView txtMarquee;

        searchOrder = (Button) findViewById(R.id.btn_searchOrder);
        searchOrder.setOnClickListener(this);

        call_phone = (Button) findViewById(R.id.btn_call_phone);
        call_phone.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_searchOrder:
                Intent moveIntent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_call_phone:
                String phoneNumber = "7028596347";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
        }
    }
}