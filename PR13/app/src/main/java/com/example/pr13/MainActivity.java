package com.example.pr13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.contacts) {
                // Handle the camera action
                Toast.makeText(this, "Контакты", Toast.LENGTH_LONG).show();
            } else if (id == R.id.archive) {
                Toast.makeText(this, "Архив", Toast.LENGTH_LONG).show();
            } else if (id == R.id.settings) {
                Toast.makeText(this, "Настройки", Toast.LENGTH_LONG).show();
            }

            drawer = findViewById(R.id.draw);
            return true;
        }

    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.draw);

        findViewById(R.id.imgomg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setFragment(new FirstFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first_item:
                        setFragment(new FirstFragment());
                        return true;
                    case R.id.second_item:
                        setFragment(new SecondFragment());
                        return true;
                    case R.id.third_item:
                        setFragment(new ThridFragment());
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.draw);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int idItem = item.getItemId();
        switch (idItem){
            case R.id.settings:
                Toast.makeText(this, "Настройки", Toast.LENGTH_LONG).show();
                return true;
            case R.id.frag_first:
                setFragment(new FirstFragment());
                return true;
            case R.id.frag_second:
                setFragment(new SecondFragment());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment,null)
                .commit();
    }


}