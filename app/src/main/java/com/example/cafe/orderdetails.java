package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class orderdetails extends AppCompatActivity {
    private TextView txtName,txtQuantity,total;
    private EditText edit_name,edit_phone,edit_address;
    private Button btnOrderNow;
    ListView listView;
    Button continueOrder;
    public TextView totalPricetv;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        txtName=findViewById(R.id.txtName);
      txtQuantity=findViewById(R.id.txtQuantity);
        total=findViewById(R.id.total);

     edit_name=findViewById(R.id.edit_name);
     edit_phone=findViewById(R.id.edit_phone);
     edit_address=findViewById(R.id.edit_address);
     continueOrder=findViewById(R.id.continueOrder);
     continueOrder.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (ContextCompat.checkSelfPermission(orderdetails.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                 sendsms();
             }
             else {
                 ActivityCompat.requestPermissions(orderdetails.this,new String[]{Manifest.permission.SEND_SMS},100);
             }
         }
     });
    }
    private void sendsms() {
        String num = "7028596347";
        String item_name = txtName.getText().toString().trim();
        String Quantity = txtQuantity.getText().toString().trim();
        String total = totalPricetv.getText().toString().trim();
//        int temp=Integer.parseInt(et1.getText().toString());
//        int price = (temp * 59);
        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(num,null,item_name +"=" +Quantity +"\n Address="+total+"\nTotal Price="+totalPricetv,null,null);
        Toast.makeText(getApplicationContext(), "Sucessfully send", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, item_name +"=" +Quantity +"\nAddress="+total+"\nTotal Price="+totalPricetv, Toast.LENGTH_LONG).show();




    }
}