package com.example.mpsan.laboratorio2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.mpsan.laboratorio2.databases.FormDatabase;
import com.example.mpsan.laboratorio2.models.Forms;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //networkManager = NetworkManager.getInstance(this);

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();

                        if (id == R.id.drawer_add_form) {
                            Fragment fragment;
                            fragment = new AddFormFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
                        }
                        else if(id == R.id.drawer_forms){
                            Fragment fragment;
                            fragment = new FormFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
                        }
                        else if (id == R.id.drawer_summary_form) {
                            Fragment fragment;
                            fragment = new FormSummaryFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
                        }
                        else if (id == R.id.logout) {
                            /*SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(getString(R.string.email), String.valueOf("null"));
                            editor.putString(getString(R.string.passsword), String.valueOf("null"));
                            editor.commit();*/
                            Intent goToFormsIntent = new Intent(MainActivity.this, LoginActivity.class);
                            MainActivity.this.startActivity(goToFormsIntent);

                        }

                        DrawerLayout drawer = findViewById(R.id.drawer);
                        drawer.closeDrawer(GravityCompat.START);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        /*SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.default_email);
        String highScore = sharedPref.getString(getString(R.string.email), defaultValue);

        if(Objects.equals(highScore, "null")) {
            Intent goToFormsIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(goToFormsIntent);
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_add_form) {
            Fragment fragment;
            fragment = new AddFormFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
        }
        else if(id == R.id.drawer_forms){
            Fragment fragment;
            fragment = new FormFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
        }
        else if (id == R.id.drawer_summary_form) {
            Fragment fragment;
            fragment = new FormSummaryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onLoginClick(View view) {

        try {
            networkManager.login("ignacio@magnet.cl", "usuarioprueba", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    getForms();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            });
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                System.out.println(error);
            }
        });
    }
}

/* References: https://www.youtube.com/watch?v=mN6kM_1M0cY */