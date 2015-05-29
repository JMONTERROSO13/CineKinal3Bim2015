package gt.edu.kinal.jmonterroso.movies;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.TabHost;
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
            new Titular("El Castillo Vagabundo", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 1"),
            new Titular("Carrie", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 2"),
            new Titular("Beautiful Creatures", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 3"),
            new Titular("Drgaon Ball Z: La resuccion de Freezer",  "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 4"),
            new Titular("Send to Chihiro", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 5"),
            new Titular("Pokemon 2000", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 6"),
            new Titular("Big Hero", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 7"),
            new Titular("A Single Shot", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 8"),
            new Titular("The perks of being a wallflower", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 9"),
            new Titular("The Giver", "Esta es una descripcion de un titular que esta siendo llamado desde el mainactivity, Titular 10")
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

        Resources res = getResources();
        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("miTab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Inicio", res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("miTab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Favoritos", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        registerForContextMenu(listMovies);

        //Fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragmnet);

        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout), myToolBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo infoMenu){
        super.onCreateContextMenu(menu, view, infoMenu);
        MenuInflater menuInflater = getMenuInflater();

        if(view.getId() == R.id.listMovies){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)infoMenu;
            Titular titularMenu = (Titular)listMovies.getAdapter().getItem(info.position);
            menu.setHeaderTitle(titularMenu.getNameMovie());
            menuInflater.inflate(R.menu.menu_ctx_movies, menu);
        }

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

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.addFav:
                Toast.makeText(getApplicationContext(), "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.viewDetails:
                Toast.makeText(getApplicationContext(), "Accediendo al detalle", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

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
