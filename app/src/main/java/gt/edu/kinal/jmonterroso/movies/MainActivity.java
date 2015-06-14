package gt.edu.kinal.jmonterroso.movies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.FragmentDrawerListener {

    private Toolbar myToolBar;
    private TextView labelWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelWelcome = (TextView)findViewById(R.id.welcome);
        myToolBar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //Shared
        SharedPreferences pref = MainActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);

        labelWelcome.setText(labelWelcome.getText()+ " " + pref.getString(getString(R.string.userRemembered), "hola"));

        //Fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragmnet);

        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), myToolBar, R.id.navigation_drawer_fragmnet);
        drawerFragment.setDrawerListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class );
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class );
            startActivity(intent);
            return true;
        }
        if (id == R.id.log_out) {
            SharedPreferences pref = MainActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);
            pref.edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class );
            startActivity(intent);
            finish();
            return true;
        }

            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (position){
            case 1:
                fragment =  new SettingFragment();
                title = getString(R.string.settings);
                break;
            case 2:
                fragment =  new MoviesFragment();
                title = getString(R.string.peli);
                break;
            case 3:
                fragment =  new FavoritesFragment();
                title = getString(R.string.favorites);
                break;
            case 4:
                fragment =  new HoursFragment();
                title = getString(R.string.hours);
                break;
            default:

            break;
        }

        if(fragment != null){
            ((RelativeLayout)findViewById(R.id.ContainerLayout)).removeAllViewsInLayout();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ContainerLayout, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

}
