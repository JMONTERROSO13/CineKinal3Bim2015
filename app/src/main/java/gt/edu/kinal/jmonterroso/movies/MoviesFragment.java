package gt.edu.kinal.jmonterroso.movies;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import gt.edu.kinal.jmonterroso.movies.models.Titular;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


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

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        listMovies = (ListView)view.findViewById(R.id.listMovies);
        AdaptadorTitulares adaptadorTitulares = new AdaptadorTitulares(getActivity(), datosTitular);
        listMovies.setAdapter(adaptadorTitulares);

        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTitular = new Intent(getActivity(), TitularActivity.class);
                Titular titularSeleccionado = (Titular) parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("Titulo", titularSeleccionado.getNameMovie());
                extras.putString("Descripcion", titularSeleccionado.getDescriptionMovie());
                intentTitular.putExtras(extras);
                startActivity(intentTitular);
            }
        });

        registerForContextMenu(listMovies);

        return view;
    }



    class AdaptadorTitulares extends ArrayAdapter<Titular> {
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo infoMenu){
        super.onCreateContextMenu(menu, view, infoMenu);
        MenuInflater menuInflater = getActivity().getMenuInflater();

        if(view.getId() == R.id.listMovies){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)infoMenu;
            Titular titularMenu = (Titular)listMovies.getAdapter().getItem(info.position);
            menu.setHeaderTitle(titularMenu.getNameMovie());
            menuInflater.inflate(R.menu.menu_ctx_movies, menu);
        }
   }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.addFav:
                Toast.makeText(getActivity().getApplicationContext(), "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.viewDetails:
                Toast.makeText(getActivity().getApplicationContext(), "Accediendo al Detalle", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

}
