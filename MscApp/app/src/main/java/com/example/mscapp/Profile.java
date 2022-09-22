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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private EditText editEmail, editName, editAge;
    private FirebaseAuth firebaseAuth;
    private String usersID;
    private Button save;
    FirebaseUser users;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
                    Toast.makeText(Profile.this,"Profile",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                }
                else if(id == R.id.page1){
                    Toast.makeText(Profile.this,"Maps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Maps.class));

                }
                else if(id == R.id.page2){
                    Toast.makeText(Profile.this,"Notes",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Diary.class));
                }
                else if(id == R.id.page3){
                    Toast.makeText(Profile.this,"Bmi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BMI.class));
                }
                else if(id == R.id.page4){
                    Toast.makeText(Profile.this,"Settings",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                }
                else if(id == R.id.page5){
                    Toast.makeText(Profile.this,"Steps",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Steps.class));
                }

                return true;
            }
        });

        users = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        usersID = users.getUid();
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference. child(usersID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User users = snapshot.getValue(User.class);
                if(users != null){
                    String FullName = users.FullName;
                    String Age = users.Age;
                    String Email = users.Email;

                    editName.setText(FullName);
                    editEmail.setText(Email);
                    editAge.setText(Age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}