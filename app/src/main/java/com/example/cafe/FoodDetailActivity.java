package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cafe.Database.SQLiteHelper;

public class FoodDetailActivity extends AppCompatActivity {
    private int item = 0;
    private int price = 0;
    TextView sumTextView;
    TextView priceTotalTextView;
    TextView txtName, txtQuantity, txtPrice;
    Button btnAdd, btnCart;
    public static SQLiteHelper sqLiteHelper;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        init();
        sqLiteHelper = new SQLiteHelper(this,"FoodDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CART (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, quantity INTEGER, price INTEGER)");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item<1){
                    Toast.makeText(FoodDetailActivity.this, "Sorry Add 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Log.i("TXT PRICE", txtName.getText().toString());
                    sqLiteHelper.insertData(
                            txtName.getText().toString().trim(),
                            txtQuantity.getText().toString().trim(),
                            txtPrice.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(),"Added !", Toast.LENGTH_SHORT).show();
                    txtQuantity.setText("0");

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            //   );
            //                    Toast.makeText(getApplicationContext(),"Added !", Toast.LENGTH_SHORT).show();
            //                    txtQuantity.setText("0");
            //
            //                }
            //                catch (Exception e){
            //                    e.printStackTrace();
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetailActivity.this,CartListActivity.class);
                startActivity(intent);
            }
        });
        String name = getIntent().getStringExtra("name");
        String typs = getIntent().getStringExtra("type");
        int image;
        image = getIntent().getIntExtra("image", -1);
        price = getIntent().getIntExtra("price", -1);
        Log.e("SECOND ACTIVITY", name);
        TextView nameTextView = (TextView) findViewById(R.id.food_name_text_view);
        nameTextView.setText(name);
        TextView type = (TextView) findViewById(R.id.type);
        type.setText(typs);
        ImageView imageView = (ImageView) findViewById(R.id.food_image);
        imageView.setImageResource(image);
        imageView.setVisibility(View.VISIBLE);
        //error sakali karu
        // TextView priceTextView = (TextView) findViewById(R.id.price_detail_text_view);
        //        priceTextView.setText(Integer.toString(price));
        //        sumTextView = (TextView) findViewById(R.id.sum_text_view);
        //        priceTotalTextView = (TextView) findViewById(R.id.price_total_text_view);
        //        Button incrementButton = (Button) findViewById(R.id.increment_button);
        TextView priceTextView = (TextView) findViewById(R.id.price_detail_text_view);
        priceTextView.setText(Integer.toString(price));
        sumTextView = (TextView) findViewById(R.id.sum_text_view);
        priceTotalTextView = (TextView) findViewById(R.id.price_total_text_view);
        Button incrementButton = (Button) findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
        Button decreamentButton = (Button) findViewById(R.id.decrement_button);
        decreamentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreament();
            }
        });
    }
    private void init(){
        txtName = findViewById(R.id.food_name_text_view);
        txtQuantity = findViewById(R.id.sum_text_view);
        txtPrice = findViewById(R.id.price_total_text_view);
        btnAdd = findViewById(R.id.btnAddtoCart);
        btnCart = findViewById(R.id.btnviewCart);
    }
    private void increment(){
        item++;
        sumTextView.setText(Integer.toString(item));
        priceTotalTextView.setText(Integer.toString(sumOfProduct(price)));
    }
    private void decreament(){
        if (item<1){
            Toast.makeText(this, "Sorry Add 1", Toast.LENGTH_SHORT).show();
            return;
        }
        item = item - 1;
        sumTextView.setText(Integer.toString(item));
        priceTotalTextView.setText(Integer.toString(sumOfProduct(price)));
    }
    private int sumOfProduct(int price){
        return item * price;
    }
}
