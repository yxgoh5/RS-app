package com.example.kokwei217.unmcrs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemImageActivity extends AppCompatActivity {
    public static String itemNameKey = "itemNameKey";
    public static String itemImgKey = "itemImageKey";
    private static final int QUANTITY = 10;
    public static ArrayList<CartComponent> components;
    public static int requestAmount = 0;
//    public static String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_image);
        Toolbar toolbar = findViewById(R.id.tb_ignore);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final String itemName = getIntent().getStringExtra(itemNameKey);
        int itemImg = getIntent().getIntExtra(itemImgKey, 0);
        setTitle(itemName);
        TextView itemNameTV = findViewById(R.id.item_name);
        TextView availableQuantity = findViewById(R.id.available_quantity);
        ImageView itemImage = findViewById(R.id.item_img);

        itemImage.setImageResource(itemImg);
        itemNameTV.setText(itemName);
        availableQuantity.setText("Available Quantity:" + QUANTITY);

        Button requestBtn = findViewById(R.id.request_btn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText quantityET = findViewById(R.id.quantity_ET);
                try {
                    requestAmount = Integer.valueOf(quantityET.getText().toString());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(ItemImageActivity.this, "Not a valid number", Toast.LENGTH_SHORT).show();
                }
                if (requestAmount == 0) {
                    Toast.makeText(ItemImageActivity.this, "Amount cant be 0", Toast.LENGTH_SHORT).show();
                } else if (requestAmount > QUANTITY) {
                    Toast.makeText(ItemImageActivity.this, "Dont Request so much cb", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ItemImageActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    components = new ArrayList<CartComponent>();
                    components.add(new CartComponent(itemName, requestAmount));
                    SharedPreferences sharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Name", itemName);
                    editor.putInt("Quantity" , requestAmount);
                    editor.apply();

//                    Intent intent = new Intent(ItemImageActivity.this, CartActivity.class);
//                    intent.putExtra("CartName" ,itemName);
//                    intent.putExtra("CartQuantity", requestAmount);
//                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public static ArrayList<CartComponent> getComponents() {
//        ArrayList<CartComponent> components = new ArrayList<CartComponent>();
//        components.add(new CartComponent(itemName, requestAmount));
//        return components;
//    }
}
