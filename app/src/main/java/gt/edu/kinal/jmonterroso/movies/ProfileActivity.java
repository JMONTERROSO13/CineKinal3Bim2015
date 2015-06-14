package gt.edu.kinal.jmonterroso.movies;

import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.Toast;

import gt.edu.kinal.jmonterroso.movies.helpers.UserSQLite;


public class ProfileActivity extends ActionBarActivity {
    private Toolbar myToolBar;
    private Button goM;
    private Button updateP;
    private EditText password;
    private EditText password2;
    private EditText email;

    private SQLiteDatabase db;
    private UserSQLite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        myToolBar = (Toolbar)findViewById(R.id.toolbar);
        goM = (Button) findViewById(R.id.goBack);
        updateP = (Button) findViewById(R.id.update);
        password = (EditText)findViewById(R.id.passTexts);
        password2 = (EditText)findViewById(R.id.passText2s);
        email = (EditText)findViewById(R.id.emailTexts);

        goM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Regresar al Inicio", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        updateP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().length() > 0){
                    if (password2.getText().length() > 0){
                        if (email.getText().length() > 0){
                            if (password.getText().toString().equals(password2.getText().toString())){
                                SharedPreferences pref = ProfileActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);
                                String name = pref.getString(getString(R.string.userRemembered), "");
                                sqlite = new UserSQLite(getBaseContext());
                                db = sqlite.getReadableDatabase();
                                ContentValues updateUser = new ContentValues();
                                try{
                                    updateUser.put("password", password.getText().toString());
                                    updateUser.put("email", email.getText().toString());
                                    db.update("Users",updateUser, "userName='" + name + "'",null);
                                    db.close();
                                    Toast toast = Toast.makeText(getApplicationContext(),"Usuario Modificado", Toast.LENGTH_SHORT);
                                    toast.show();
                                } catch (Exception e) {
                                    db.close();
                                    Toast.makeText(getApplicationContext(),"3)" + e.toString(), Toast.LENGTH_SHORT).show();
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
            }
        });
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
