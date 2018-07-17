package com.example.kokwei217.unmcrs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
     DrawerLayout drawer;
     Toolbar toolbar;
     NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        toolbar = findViewById(R.id.toolbar_main);
        drawer = findViewById(R.id.drawer_main);
        navigationView = findViewById(R.id.nv_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });


//-----------------------------ListView----------------------------------------------------------------------------
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


    public void selectDrawerItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_first:
                Toast.makeText(MainActivity.this, "first", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_second:
                Toast.makeText(MainActivity.this, "second", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.sub_first:
                Toast.makeText(MainActivity.this, "Made by ", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        drawer.closeDrawers();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;

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