package gt.edu.kinal.jmonterroso.movies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;
import gt.edu.kinal.jmonterroso.movies.models.Users;


public class Register extends ActionBarActivity {

    private Toolbar myToolBar;
    private Button btnlog;
    private Button btnregit;
    private EditText userName;
    private EditText password;
    private EditText password2;
    private EditText email;

    private SQLiteDatabase db;
    private UserSQLite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myToolBar = (Toolbar)findViewById(R.id.toolbar);
        btnlog = (Button)findViewById(R.id.logins);
        btnregit = (Button)findViewById(R.id.regit);
        userName = (EditText)findViewById(R.id.userText);
        password = (EditText)findViewById(R.id.passText);
        password2 = (EditText)findViewById(R.id.passText2);
        email = (EditText)findViewById(R.id.emailText);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Regresar al Login", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnregit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().length() > 0){
                    if (password.getText().length() > 0){
                        if (password2.getText().length() > 0){
                            if (email.getText().length() > 0){
                                if (password.getText().toString().equals(password2.getText().toString())){

                                    sqlite = new UserSQLite(getBaseContext());
                                    db = sqlite.getReadableDatabase();

                                    String Sql = "SELECT * FROM Users WHERE userName='"+userName.getText().toString()+"'";

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
                                        sqlite = new UserSQLite(getBaseContext());
                                        db = sqlite.getWritableDatabase();
                                        try{

                                            users.put("idUser", 3);
                                            users.put("userName", userName.getText().toString());
                                            users.put("password", password.getText().toString());
                                            users.put("email", email.getText().toString());

                                            db.insert("Users", null, users);
                                            db.close();

                                            Intent intent =  new Intent(Register.this, LoginActivity.class);
                                            startActivity(intent);
                                            Register.this.finish();
                                        } catch (Exception e) {
                                            db.close();
                                            Toast toast = Toast.makeText(getApplicationContext(),"3)" + e.toString(), Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    }else
                                    {
                                        Toast toast = Toast.makeText(getApplicationContext(),"El nombre de usuario ya existe", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Las contraseñas no son inguales", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(),"Ingrese Email", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Confirme su contraseña", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Ingrese una contraseña", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Ingrese un nombre de usuario", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
         });

        UserSQLite userSQLite = new UserSQLite(this);
        SQLiteDatabase db = userSQLite.getWritableDatabase();

        if(db != null) {
           
        }
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.loginReg) {
            Intent intent = new Intent(Register.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Accediendo al Login", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
