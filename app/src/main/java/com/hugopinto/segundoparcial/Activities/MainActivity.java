package com.hugopinto.segundoparcial.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hugopinto.segundoparcial.Adapters.GameAdapter;
import com.hugopinto.segundoparcial.Classes.Game;
import com.hugopinto.segundoparcial.Fragments.GSCOFragment;
import com.hugopinto.segundoparcial.Fragments.NewsFragment;
import com.hugopinto.segundoparcial.R;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GSCOFragment.OnFragmentInteractionListener, NewsFragment.OnFragmentInteractionListener{

    RecyclerView rv;
    GameAdapter adapter;
    ArrayList<Game> series;
    GridLayoutManager gManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenido,new NewsFragment()).commit();
        getSupportActionBar().setTitle("Game News UCA");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean FragmentSeleccionado = false;
        Fragment miFragment=null;
        boolean ActSeleccionada =false;
        Activity miActivity = null;

        if (id == R.id.nav_news) {
            // Handle the camera action
            miFragment = new NewsFragment();
            FragmentSeleccionado = true;
            getSupportActionBar().setTitle("Noticias");

        } else if (id == R.id.nav_CSGO) {
            miFragment = new GSCOFragment();
            FragmentSeleccionado = true;
            getSupportActionBar().setTitle("Counter Strike: GO");

        }  else if (id == R.id.nav_LOL) {
            miFragment = new GSCOFragment();
            FragmentSeleccionado = true;
            getSupportActionBar().setTitle("League of Legends");

        } else if (id == R.id.nav_DOTA) {
            miFragment = new GSCOFragment();
            FragmentSeleccionado = true;
            getSupportActionBar().setTitle("DOTA");

        }
        else if (id == R.id.nav_favoritos) {


        }else if (id == R.id.nav_configuracion) {
            miActivity = new Settings();
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);

        }
        if(FragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
