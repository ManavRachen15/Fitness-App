package com.example.mscapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;

public class Diary extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

       if (item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), Note.class);
            startActivity(intent);
            return  true;
       }
        if (actionBarDrawerToggle !=null){
            return actionBarDrawerToggle.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.profile){
                    Toast.makeText(Diary.this,"Profile",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }
                else if(id == R.id.page1){
                    Toast.makeText(Diary.this,"Maps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Maps.class));

                }
                else if(id == R.id.page2){
                    Toast.makeText(Diary.this,"Notes",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Diary.class));
                }
                else if(id == R.id.page3){
                    Toast.makeText(Diary.this,"Bmi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BMI.class));
                }
                else if(id == R.id.page4){
                    Toast.makeText(Diary.this,"Settings",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                }
                else if(id == R.id.page5){
                    Toast.makeText(Diary.this,"Steps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Steps.class));
                }

                return true;
            }
        });

        ListView listView =  findViewById(R.id.ListView);
        SharedPreferences sharedPreferences  = getApplicationContext().getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if(set == null) {

            notes.add("example note");

        }else {
            notes = new ArrayList<>(set);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),Note.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int positive, long id) {

                new AlertDialog.Builder(Diary.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("are you sure")
                        .setMessage("Do you want to delete this note")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        notes.remove(positive);
                                        arrayAdapter.notifyDataSetChanged();
                                        SharedPreferences sharedPreferences  = getApplicationContext().getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);

                                        HashSet<String> set = new HashSet(Diary.notes);

                                        sharedPreferences.edit().putStringSet("notes",set).apply();

                                    }
                                }
                        )
                        .setNegativeButton("No", null)
                        .show();


                return true;
            }

        });

    }


}