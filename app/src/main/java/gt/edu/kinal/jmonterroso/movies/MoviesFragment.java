package gt.edu.kinal.jmonterroso.movies;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;
import gt.edu.kinal.jmonterroso.movies.models.Titular;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


    private ListView listMovies;
    private UserSQLite sqlite;
    private SQLiteDatabase db;
    ArrayList<Titular> listaPelicula = new ArrayList<Titular>();


     public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        listMovies = (ListView)view.findViewById(R.id.listMovies);
        listaAdapter();
        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTitular = new Intent(getActivity(), TitularActivity.class);
                Titular titularSeleccionado = (Titular)parent.getItemAtPosition(position);
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


    public void listaAdapter() {
        listaPelicula.clear();

        sqlite = new UserSQLite(getActivity().getBaseContext());
        db = sqlite.getReadableDatabase();

        String Sql = "SELECT * FROM Movies";

        Cursor cc = db.rawQuery(Sql, null);
        Titular obj;
        db.close();
        if (cc.moveToFirst()) {
            do {

                obj = new Titular();
                obj.setNameMovie(cc.getString(1));
                obj.setDescriptionMovie(cc.getString(2));
                listaPelicula.add(obj);

            } while (cc.moveToNext());
        }
        AdaptadorTitulares adapter = new AdaptadorTitulares(getActivity(),listaPelicula);
        listMovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    class AdaptadorTitulares extends ArrayAdapter<Titular> {
        public AdaptadorTitulares(Context context,  ArrayList<Titular> datos){
            super(context, R.layout.listitem_movie, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_movie, null);

            TextView tvPelicula = (TextView)item.findViewById(R.id.txtName);
            TextView tvDescrption = (TextView)item.findViewById(R.id.txtDescription);

            String tituloPelicula = listaPelicula.get(position).getNameMovie();
            String descriptionMovie = listaPelicula.get(position).getDescriptionMovie();

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
/*
                Titular peliFav = (Titular)listMovies.getAdapter().getItem(info.position);
                sqlite = new UserSQLite(getActivity().getBaseContext());
                db = sqlite.getReadableDatabase();

                String Sql = "SELECT idMovie FROM Favorites WHERE idUser='"+peliFav.+"'";
                Cursor cc = db.rawQuery(Sql, null);
                int exists = 0;
                if (cc.moveToFirst())
                {
                    do {
                        exists++;
                    } while (cc.moveToNext());
                }
                db.close();
                if (exists <= 0 ){
                    ContentValues users = new ContentValues();
                    sqlite = new UserSQLite(getActivity().getBaseContext());
                    db = sqlite.getWritableDatabase();
                    try{

                        users.put("userName", userName.getText().toString());
                        users.put("password", password.getText().toString());
                        users.put("email", email.getText().toString());

                        db.insert("Users", null, users);
                        db.close();


                    } catch (Exception e) {
                        db.close();
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),"3)" + e.toString(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else
                {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),"El nombre de usuario ya existe", Toast.LENGTH_SHORT);
                    toast.show();
                }
                */
                Toast.makeText(getActivity().getApplicationContext(), "Agregado a Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.viewDetails:
                Intent intentTitular = new Intent(getActivity(), TitularActivity.class);
                Titular peliSelect = (Titular)listMovies.getAdapter().getItem(info.position);
                Bundle extras = new Bundle();
                extras.putString("Titulo", peliSelect.getNameMovie());
                extras.putString("Descripcion", peliSelect.getDescriptionMovie());
                intentTitular.putExtras(extras);
                startActivity(intentTitular);
                Toast.makeText(getActivity().getApplicationContext(), "Accediendo al Detalle", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

}
