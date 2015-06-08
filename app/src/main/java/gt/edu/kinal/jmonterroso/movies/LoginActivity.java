package gt.edu.kinal.jmonterroso.movies;

import android.content.ClipData;
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
import android.widget.TextView;
import android.widget.Toast;

import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;


public class LoginActivity extends ActionBarActivity {

    private Toolbar myToolBar;
    private Button btnLogin;
    private Button btnRegis;
    private EditText userName;
    private EditText pass_text;

    private String UserName;
    private String Password;
    private SQLiteDatabase db;
    private UserSQLite userDB;

    String user_name;
    String pass_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myToolBar = (Toolbar)findViewById(R.id.toolbar);
        btnLogin = (Button)findViewById(R.id.logins);
        btnRegis = (Button)findViewById(R.id.registers);
        userName = (EditText)findViewById(R.id.userText);
        pass_text = (EditText)findViewById(R.id.passText);

        user_name = ((EditText)findViewById(R.id.userText)).getText().toString();
        pass_name = ((EditText)findViewById(R.id.passText)).getText().toString();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
                Toast.makeText( getApplicationContext() , "Accediendo al Registro", Toast.LENGTH_SHORT).show();
            }
        });

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void login(){

        userDB = new UserSQLite(this, "DBCine",null, 1);
        db = userDB.getWritableDatabase();
        Cursor c = db.rawQuery("Select password from Users Where userName='"+user_name+"'",null);
        int valida = 0;
        if (c.moveToFirst()){
            do {
                valida++;
                Password = c.getString(0);
            }while(c.moveToNext());
        }
        if (valida > 0){
            if (Password.equals(pass_name)){
                Bundle b = new Bundle();
                b.putString("userName", userName.getText().toString());
                b.putString("password", pass_text.getText().toString());
                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                intentLogin.putExtras(b);
                startActivity(intentLogin);
            }else{
                Toast.makeText(LoginActivity.this, "Password incorect",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(LoginActivity.this, "usuario no registrado",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Item Ajustes", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.register){
            Intent intenRegister = new Intent(LoginActivity.this , Register.class);
            Toast.makeText(getApplicationContext(), "Accediendo al Registro", Toast.LENGTH_SHORT).show();
            startActivity(intenRegister);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
