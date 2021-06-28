package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cafe.Adapter.FoodAdapter;
import com.example.cafe.Models.Food;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    FoodAdapter adapter;
    SearchView mySearchView;
    ListView mylist;
    TextView txtMarquee;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        txtMarquee=(TextView) findViewById(R.id.marquee) ;
        txtMarquee.setSelected(true);


//        mySearchView=(SearchView)findViewById(R.id.searchView);
//        mylist=(ListView)findViewById(R.id.mylist);

        final ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Chicken Kheema Pizza",R.drawable.p1, 100,"Loaded with chicken keema, this pizza is a treat for all the chicken lovers. The base is made with whole wheat flour which makes this healthy"));
        foods.add(new Food("Chicken Pizza",R.drawable.p2, 100,"chicken pizza recipe is a delicious mix of flat bread or base topped with cheese, chillies, onion, garlic sauce"));
        foods.add(new Food("Chicken Barbeque",R.drawable.p3, 100,"Italian Pasta Food"));
        foods.add(new Food("Chocolate Sandwich",R.drawable.c1, 70,"We are fans of chocolate. There are various ways one can have chocolate and this chocolate sandwich is one such delicious way. Kids love these sandwiches "));
        foods.add(new Food("Aloo Tikki Roll ",R.drawable.r, 90,"Perfectly Made Potato Cutlets Wrapped Inside A Paratha Drizzled With Mint, Tandoori Mayo, Served With A Sprinkle Of Chaat Masala."));
        foods.add(new Food("Egg Roll ",R.drawable.r1, 90," Egg roll in a bowl is a delicious way to eat the flavors of an egg roll without all the deep frying of a  traditional egg roll"));
        foods.add(new Food("French Fries",R.drawable.f, 100,"French fries, or simply fries (North American English), chips finger chips (Indian English), hot chips (Australian English) or French-fried potatoes, are batonnet"));
        foods.add(new Food("Chicken Chilly Momos",R.drawable.m1, 90,"Asian Pasta Food"));
        foods.add(new Food("Periperi Fries",R.drawable.f1, 100,"Asian Pasta Food"));



//        adapter=new FoodAdapter()Adapter<>(this, android.R.layout.simple_list_item_1,foods);

        adapter = new FoodAdapter(this, foods);
        ListView orderListView = (ListView) findViewById(R.id.mylist);
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MenuActivity.this,FoodDetailActivity.class);

                Food currentFood = foods.get(position);
                Log.e("FOOD NAME", currentFood.getFoodName());
                i.putExtra("name", currentFood.getFoodName());
                i.putExtra("image", currentFood.getmImageResource());
                i.putExtra("price", currentFood.getFoodPrice());
                i.putExtra("type", currentFood.getType());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_cart: Intent i = new Intent(MenuActivity.this, CartListActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}





