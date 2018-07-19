package com.example.kokwei217.unmcrs;


import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.lv_cart);
        final String cartName = getIntent().getStringExtra("CartName");
        int cartQuantity = getIntent().getIntExtra("CartQuantity", 0);
//        ArrayList<CartComponent> arrayOfCart = ItemImageActivity.getComponents();
        SharedPreferences result = getSharedPreferences("Data", MODE_PRIVATE);
        String name = result.getString("Name", "no Data");
        int quantity = result.getInt("Quantity", 0);

        ArrayList<CartComponent> arrayOfCart = new ArrayList<>();
        arrayOfCart.add(new CartComponent(name, quantity));

        CustomCartAdapter customCartAdapter = new CustomCartAdapter(this, arrayOfCart);
        listView.setAdapter(customCartAdapter);
//        CartComponent newCartComponent = new CartComponent(cartName,cartQuantity);
//        customCartAdapter.add(newCartComponent);

    }
}
