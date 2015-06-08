package gt.edu.kinal.jmonterroso.movies.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JMONTERROSO on 08/06/2015.
 */
public class UserSQLite extends SQLiteOpenHelper{

    private String sqlUsers = "CREATE TABLE Users (idUser INTEGER PRIMARY KEY, userName TEXT, email TEXT, password TEXT)";
    private String sqlMovies = "CREATE TABLE Movies (idMovie INTEGER PRIMARY KEY, name TEXT, description TEXT)";
    private String sqlFavorites = "CREATE TABLE Favorites (idFav INTEGER PRIMARY KEY, idMovie INTEGER,idUser TEXT )";
    /* private String innerFavs = "SELECT Users.userName as 'User Name', Movies.name as 'Movie Title', Movies.description as 'Description Movie'\n" +
            "FROM Favorites \n" +
            "INNER JOIN Users  ON Favorites.idUser = Users.idUser\n" +
            "INNER JOIN Movies  ON Favorites.idMovie = Movies.idMovie\n" +
            "WHERE Favorites.idUser=?"; */

    public UserSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlUsers);
        db.execSQL(sqlMovies);
        db.execSQL(sqlFavorites);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Movies");
        db.execSQL("DROP TABLE IF EXISTS Favorites");

        db.execSQL(sqlMovies);
        db.execSQL(sqlUsers);
        db.execSQL(sqlFavorites);
    }
}
