package com.example.kokwei217.unmcrs;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CartActivity extends AppCompatActivity {
    String name;
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        setContentView(R.layout.activity_cart);
        setTitle("Cart");
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.lv_cart);
//        cartName = getIntent().getStringExtra("CartName");
//        cartQuantity = getIntent().getIntExtra("CartQuantity", 0);
//        ArrayList<CartComponent> arrayOfCart = ItemImageActivity.getComponents();
        SharedPreferences result = getSharedPreferences("Data", MODE_PRIVATE);
        name = result.getString("Name", "no Data");
        quantity = result.getInt("Quantity", 0);

        ArrayList<CartComponent> arrayOfCart = new ArrayList<>();
        arrayOfCart.add(new CartComponent(name, quantity));

        CustomCartAdapter customCartAdapter = new CustomCartAdapter(this, arrayOfCart);
        listView.setAdapter(customCartAdapter);
//        CartComponent newCartComponent = new CartComponent(cartName,cartQuantity);
//        customCartAdapter.add(newCartComponent);

    }

    public void checkout(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Component Request");
        alertDialog.setMessage("Press Ok to confirm the request");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveData();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }

    public void saveData(){
        StringBuilder sb = new StringBuilder();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), "RequestData.csv"));
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String filePath = file.getAbsolutePath() + "/RequestData.csv";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            sb.append("admin" + "," + timeStamp + "," + name + "," + quantity + "\n");
            writer.append(sb.toString());
            writer.close();
            Toast.makeText(this, "Request sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
