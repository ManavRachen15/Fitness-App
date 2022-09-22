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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BMI extends AppCompatActivity {


    private Button calculate;
    private EditText Weight,Height;
    private TextView Answer;
    private DatabaseReference ref;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        calculate = findViewById(R.id.calculate);
        Weight = findViewById(R.id.etWeight);
        Height = findViewById(R.id.etHeight);
        Answer = findViewById(R.id.tvAnswer);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getW = Weight.getText().toString();
                String getH = Height.getText().toString();

                float W=Float.parseFloat(getW);
                float H=Float.parseFloat(getH);

                float newH=H/100;
                float bmi=W/(newH*newH);

                if(bmi<18.5)
                {
                    Answer.setText("You are underweight");
                }
                else if (bmi>=18.5&&bmi<25){
                    Answer.setText("You are Normal");
                }
                else{
                    Answer.setText("You are overweight");

                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.profile){
                    Toast.makeText(BMI.this,"Profile",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }
                else if(id == R.id.page1){
                    Toast.makeText(BMI.this,"Maps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Maps.class));

                }
                else if(id == R.id.page2){
                    Toast.makeText(BMI.this,"Notes",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Diary.class));
                }
                else if(id == R.id.page3){
                    Toast.makeText(BMI.this,"Bmi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BMI.class));
                }
                else if(id == R.id.page4){
                    Toast.makeText(BMI.this,"Settings",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                }
                else if(id == R.id.page5){
                    Toast.makeText(BMI.this,"Steps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Steps.class));
                }

                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}