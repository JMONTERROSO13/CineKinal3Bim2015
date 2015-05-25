package gt.edu.kinal.jmonterroso.movies;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends ActionBarActivity {

    private Toolbar myToolBar;
    private Button btnlog;
    private Button btnregit;
    private EditText lblName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myToolBar = (Toolbar)findViewById(R.id.toolbar);
        btnlog = (Button)findViewById(R.id.logins);
        btnregit = (Button)findViewById(R.id.regit);
        lblName = (EditText)findViewById(R.id.userText);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Accediendo al Login", Toast.LENGTH_SHORT).show();
            }
        });

        btnregit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("userName", lblName.getText().toString());
                Intent intentRegit = new Intent(Register.this, MainActivity.class);
                intentRegit.putExtras(b);
                startActivity(intentRegit);
                Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Iniciando Sesion", Toast.LENGTH_SHORT).show();
            }
        });

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
