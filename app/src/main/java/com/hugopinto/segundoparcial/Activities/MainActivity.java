package com.hugopinto.segundoparcial.Activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.Adapters.ImagesAdapter;
import com.hugopinto.segundoparcial.Adapters.JuegosAdapter;
import com.hugopinto.segundoparcial.Fragments.GenericFragment;
import com.hugopinto.segundoparcial.Fragments.JuegosFragment;
import com.hugopinto.segundoparcial.Fragments.NewsFragment;
import com.hugopinto.segundoparcial.R;
import com.hugopinto.segundoparcial.ROOM.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JuegosFragment.OnFragmentInteractionListener{

    public static Boolean validacion = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view3);
        navigationView2.setNavigationItemSelectedListener(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.incluido, new JuegosFragment()).commit();
        if(validacion){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido, NewsFragment.newInstance("Noticias")).commit();
            SharedPreferences sharedPreferences = this.getSharedPreferences("Juego", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Juegos","Noticias");
            editor.apply();

            validacion=false;
        }






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
            miFragment = NewsFragment.newInstance(item.getTitle().toString());
            FragmentSeleccionado = true;
            getSupportActionBar().setTitle("Noticias");

        } else if (id == R.id.nav_configuracion) {
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
