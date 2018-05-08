package com.example.gamma.lab05;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class formula extends AppCompatActivity {

    SharedPreferences pref;
    String token;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"A","B","C","D","E"};



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        RecibirDatos();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        final FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        int id=menuItem.getItemId();
                        menuItem.setChecked(true);
                         mDrawerLayout.closeDrawers();

                         if(id==R.id.nav_camera){
                             fragmentTransaction.replace(R.id.content_frame,new IngresarFormulario());
                             fragmentTransaction.commit();
                         }
                         else if (id==R.id.nav_gallery){
                             fragmentTransaction.replace(R.id.content_frame,new VerFormularios());
                             fragmentTransaction.commit();
                         }
                         else if(id==R.id.nav_Logout){
                             onLOGOUTClick();
                         }

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLOGOUTClick()
    {

        SharedPreferences.Editor editor = pref.edit();
        editor.remove(token);
        editor.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void RecibirDatos()
    {
        Bundle extras = getIntent().getExtras();

        token = extras.getString("token");


        pref = getSharedPreferences("Sesion", MODE_PRIVATE);


    }




}
