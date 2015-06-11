package gt.edu.kinal.jmonterroso.movies;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import java.sql.SQLData;

import gt.edu.kinal.jmonterroso.movies.HandlerUsers;
import gt.edu.kinal.jmonterroso.movies.HandlerMovies;
import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.FragmentDrawerListener {

    // HandlerMovies mHandler =  new HandlerMovies().getInstance();
    private Toolbar myToolBar;
    private TextView labelWelcome;
    private TextView userTxt;
    private TextView movieTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle userAccount = getIntent().getExtras();

        labelWelcome = (TextView)findViewById(R.id.welcome);
        userTxt = (TextView)findViewById(R.id.txtDB);
        movieTxt = (TextView)findViewById(R.id.txtMV);
        myToolBar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String name = userAccount.getString("userName");

        labelWelcome.setText(labelWelcome.getText()+ " " + name);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean notificationEnable = preferences.getBoolean("is_notification_enable", false);
        Toast.makeText(this, getString(R.string.notifications)+ " " + notificationEnable, Toast.LENGTH_SHORT).show();

        //Fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragmnet);

        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), myToolBar, R.id.navigation_drawer_fragmnet);
        drawerFragment.setDrawerListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
