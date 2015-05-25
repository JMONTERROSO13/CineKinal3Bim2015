package gt.edu.kinal.jmonterroso.movies.models;

/**
 * Created by JMONTERROSO on 24/05/2015.
 */
public class Titular {

    private String nameMovie;
    private String descriptionMovie;
    private String img;

    public String getNameMovie() {
        return nameMovie;
    }

    public String getDescriptionMovie() {
        return descriptionMovie;
    }

    public String getImg() {
        return img;
    }

    public Titular(String nameMovie, String descriptionMovie, String img) {
        this.nameMovie = nameMovie;
        this.descriptionMovie = descriptionMovie;
        this.img = img;
    }
}
