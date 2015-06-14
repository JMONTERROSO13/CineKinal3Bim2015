package gt.edu.kinal.jmonterroso.movies.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JMONTERROSO on 08/06/2015.
 */
public class UserSQLite extends SQLiteOpenHelper{

    private static CursorFactory factory = null;

    private String sqlUsers = "CREATE TABLE Users (idUser INTEGER PRIMARY KEY, userName TEXT, email TEXT, password TEXT)";
    private String sqlMovies = "CREATE TABLE Movies (idMovie INTEGER PRIMARY KEY, name TEXT, description TEXT)";
    private String sqlFavorites = "CREATE TABLE Favorites (idFav INTEGER PRIMARY KEY, idMovie INTEGER,idUser INTEGER ,FOREIGN KEY(idMovie) REFERENCES Movies(idMovie),FOREIGN KEY(idUSer) REFERENCES Users(idUser));";

    public UserSQLite(Context context) {
        super(context, "DBCine",factory, 1);
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
