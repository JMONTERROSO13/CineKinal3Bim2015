package gt.edu.kinal.jmonterroso.movies.models;

/**
 * Created by JMONTERROSO on 24/05/2015.
 */
public class Titular {

    private String nameMovie;
    private String descriptionMovie;

    public String getNameMovie() {
        return nameMovie;
    }

    public String getDescriptionMovie() {
        return descriptionMovie;
    }

    public Titular(String nameMovie, String descriptionMovie) {
        this.nameMovie = nameMovie;
        this.descriptionMovie = descriptionMovie;
    }
}
