package gt.edu.kinal.jmonterroso.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {



    private Toolbar myToolBar;
    private TextView labelWelcome;
    private TextView spinTxt;
    final String[] datos = new String[]{"Pelicula 1","Pelicula 2","Pelicula 3","Pelicula 4","Pelicula 5","Pelicula 6","Pelicula 7","Pelicula 8"};
    private Spinner spinners;
    private ListView listMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle userAccount = getIntent().getExtras();

        labelWelcome = (TextView)findViewById(R.id.welcome);
        spinTxt = (TextView)findViewById(R.id.txtSpin);
        myToolBar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String name = userAccount.getString("userName");

        labelWelcome.setText(labelWelcome.getText()+ " " + name);

        ArrayAdapter<String> arreglo = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, datos);

        spinners = (Spinner)findViewById(R.id.spin);
        arreglo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinners.setAdapter(arreglo);

        spinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinTxt.setText("Elemento " + adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinTxt.setText("Elemento Nulo ");
            }
        });

        //listView Movies
        ArrayAdapter<String> arrayList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listMovies = (ListView)findViewById(R.id.listMovies);
        listMovies.setAdapter(arrayList);

        //ListView Implements with objects


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
