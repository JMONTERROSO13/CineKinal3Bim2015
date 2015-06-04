package gt.edu.kinal.jmonterroso.movies;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.movies.models.Titular;

public class HandlerMovies {

    private static ArrayList<Titular> favList = new ArrayList<Titular>();

    public static void addFav(Titular movie){
        favList.add(movie);
    }

    

}
