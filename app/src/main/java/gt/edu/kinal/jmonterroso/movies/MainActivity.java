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

import gt.edu.kinal.jmonterroso.movies.models.Titular;


public class MainActivity extends ActionBarActivity {



    private Toolbar myToolBar;
    private TextView labelWelcome;
    private TextView spinTxt;
    final String[] datos = new String[]{"Pelicula 1","Pelicula 2","Pelicula 3","Pelicula 4","Pelicula 5","Pelicula 6","Pelicula 7","Pelicula 8"};
    private Spinner spinners;
    private ListView listMovies;

    private Titular[] datosTitular = new Titular[]{
            new Titular("Pelicula 1", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 1"),
            new Titular("Pelicula 2", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 2"),
            new Titular("Pelicula 3", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 3"),
            new Titular("Pelicula 4",  "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 4"),
            new Titular("Pelicula 5", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 5"),
            new Titular("Pelicula 6", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 6"),
            new Titular("Pelicula 7", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 7"),
            new Titular("Pelicula 8", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 8"),
            new Titular("Pelicula 9", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 9"),
            new Titular("Pelicula 10", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 10")
    };

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

        /* listView Movies
        ArrayAdapter<String> arrayList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listMovies = (ListView)findViewById(R.id.listMovies);
        listMovies.setAdapter(arrayList);
        */
        //ListView Implements with objects

        listMovies = (ListView)findViewById(R.id.listMovies);
        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(this, datosTitular);
        listMovies.setAdapter(adaptadorTitulares);

        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTitular = new Intent(MainActivity.this, TitularActivity.class);
                Titular titularSeleccionado = (Titular) parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("Titulo", titularSeleccionado.getNameMovie());
                extras.putString("Descripcion", titularSeleccionado.getDescriptionMovie());
                intentTitular.putExtras(extras);
                startActivity(intentTitular);
            }
        });

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

    class AdaptadorTitulares extends ArrayAdapter<Titular>{
        public AdaptadorTitulares(Context context, Titular[] datos){
            super(context, R.layout.listitem_movie, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_movie, null);

            TextView tvPelicula = (TextView)item.findViewById(R.id.txtName);
            TextView tvDescrption = (TextView)item.findViewById(R.id.txtDescription);

            String tituloPelicula = datosTitular[position].getNameMovie();
            String descriptionMovie = datosTitular[position].getDescriptionMovie();

            tvPelicula.setText(tituloPelicula);
            tvDescrption.setText(descriptionMovie);

            return item;
        }
    }

}
