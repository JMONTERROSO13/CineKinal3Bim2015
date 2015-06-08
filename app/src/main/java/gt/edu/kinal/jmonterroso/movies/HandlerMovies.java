package gt.edu.kinal.jmonterroso.movies;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.movies.models.Titular;

public class HandlerMovies {

    public static ArrayList<Titular> favList = new ArrayList<Titular>();
    public static ArrayList<Titular> moviesList = new ArrayList<Titular>();

    private static HandlerMovies instance;

    private HandlerMovies() {
    }

    public static HandlerMovies getInstance() {
        if(instance == null) {
            instance = new HandlerMovies();
        }
        return instance;
    }

    public void addFav(Titular fav){
        favList.add(fav);
    }

    public ArrayList<Titular> getListFavorites(){
        return this.favList;
    }

    public void addMovies(Titular movie){
        moviesList.add(movie);
    }

    public ArrayList<Titular> getListMovies(){
        return this.moviesList;
    }

    public void removeMovies(Titular revMovie){
        moviesList.remove(revMovie);
    }

    public void removeFav(Titular movie){
        favList.remove(movie);
    }

}
