package com.example.mscapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button signOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
                    Toast.makeText(Settings.this,"Profile",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }
                else if(id == R.id.page1){
                    Toast.makeText(Settings.this,"Maps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Maps.class));

                }
                else if(id == R.id.page2){
                    Toast.makeText(Settings.this,"Notes",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Diary.class));
                }
                else if(id == R.id.page3){
                    Toast.makeText(Settings.this,"Bmi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BMI.class));
                }
                else if(id == R.id.page4){
                    Toast.makeText(Settings.this,"Settings",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                }
                else if(id == R.id.page5){
                    Toast.makeText(Settings.this,"Steps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Steps.class));
                }

                return true;
            }
        });
        signOut = findViewById(R.id.SignoutButton);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Settings.this,Login.class));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    }
