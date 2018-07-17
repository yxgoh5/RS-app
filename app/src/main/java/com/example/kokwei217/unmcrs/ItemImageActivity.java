package com.example.kokwei217.unmcrs;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemImageActivity extends AppCompatActivity {
    public static String itemNameKey = "itemNameKey";
    public static String itemImgKey = "itemImageKey";
    private static final int QUANTITY = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_image);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String itemName = getIntent().getStringExtra(itemNameKey);
        int itemImg = getIntent().getIntExtra(itemImgKey, 0);
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
                int requestAmount = 0;
                EditText quantityET = findViewById(R.id.quantity_ET);
                try {
                    requestAmount = Integer.valueOf(quantityET.getText().toString());
                }catch (NumberFormatException nfe){
                    Toast.makeText(ItemImageActivity.this, "Not a valid number", Toast.LENGTH_SHORT).show();
                }
                if ( requestAmount == 0){
                    Toast.makeText(ItemImageActivity.this, "Amount cant be 0", Toast.LENGTH_SHORT).show();
                }else if(requestAmount > QUANTITY){
                    Toast.makeText(ItemImageActivity.this, "Dont Request so much cb", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ItemImageActivity.this , "request successful" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
