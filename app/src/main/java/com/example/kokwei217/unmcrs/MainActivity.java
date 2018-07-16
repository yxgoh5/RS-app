package com.example.kokwei217.unmcrs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        final List<String> itemList = new ArrayList<>();
        itemList.add("Raspberry Pi");
        itemList.add("Arduino Uno");
        itemList.add("LED");
        itemList.add("Buttons");
        itemList.add("USB cables");
        itemList.add("whatever");

        final List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.raspi);
        imgList.add(R.drawable.arduino);
        imgList.add(R.drawable.led);
        imgList.add(R.drawable.buttons);
        imgList.add(R.drawable.usb);
        imgList.add(R.drawable.whatever);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList);
        final ListView menuList = findViewById(R.id.item_list);
        menuList.setAdapter(arrayAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ItemImageActivity.class);
                intent.putExtra(ItemImageActivity.itemNameKey, itemList.get(i));
                intent.putExtra(ItemImageActivity.itemImgKey, imgList.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                Toast.makeText(MainActivity.this, "searching", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}