package gt.edu.kinal.jmonterroso.movies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox cRemember;
    private String nameuser;
    private String Password;
    private SQLiteDatabase db;
    private UserSQLite userDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myToolBar = (Toolbar)findViewById(R.id.toolbar);
        btnLogin = (Button)findViewById(R.id.logins);
        btnRegis = (Button)findViewById(R.id.registers);
        userName = (EditText)findViewById(R.id.userText);
        pass_text = (EditText)findViewById(R.id.passText);
        cRemember = (CheckBox)findViewById(R.id.remember);
        nameuser = userName.getText().toString();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB = new UserSQLite(getBaseContext());
                db = userDB.getReadableDatabase();
                String sql;
                sql = "Select password from Users Where userName='"+userName.getText().toString()+"'";
                Cursor c = db.rawQuery(sql,null);

                int valida = 0;
                if (c.moveToFirst()){
                    do {
                        Password = c.getString(0);
                        valida++;
                    }while(c.moveToNext());
                }
                db.close();
                if (userName.getText().length() > 0) {
                    if (pass_text.getText().length() > 0) {
                        if (valida > 0) {
                            if (Password.equals(pass_text.getText().toString())) {

                                SharedPreferences preferences = getSharedPreferences( getString(R.string.sharedClass), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(getString(R.string.userRemembered), nameuser);
                                editor.putBoolean(getString(R.string.isRemembered), cRemember.isChecked());
                                editor.apply();
                                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentLogin);
                                LoginActivity.this.finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario No Registrado", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Ingrese su Contraseña", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Ingrese su Nombre de Usuario", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
