package dmusiolik.ajcbk_vp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public static int counter = 0;
    public static String benutzer = null;
    public static String passwort = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void anmeldungalt(View v) {

    }

    public void anmeldung(View view) {

        TextView loginbox = (TextView) findViewById(R.id.email);
        TextView passbox = (TextView) findViewById(R.id.password);

        System.out.println("Lese ACJBK Benutzernamen >" + loginbox.getText() + "<");
        System.out.println("Ostereizähler >" + counter + "<");

        String logindata;
        logindata = loginbox.getText().toString();

        if (logindata.equals("")) {
            //Fehler
            if (counter == 0) {
                loginbox.setError("Sie Sir brauchen einen Account!");
                counter++;
                return;
            }
            if (counter == 1) {
                loginbox.setError("Ich verhandel nicht mit Ihnen!");
                counter++;
                return;
            }
            if (counter == 2) {
                loginbox.setError("Hallo ?! Das Feld darf nicht leer sein!");
                counter++;
                return;
            }
            if (counter == 3) {
                loginbox.setError("Verdammt! Trag einfach deinen Namen ein!!");
                counter = 0;
                return;
            }

        }
            //Zugangsdaten speichern


            //Benutzername und Passwort einlesen und weitergeben
            benutzer = loginbox.getText().toString();
            passwort = passbox.getText().toString();

            //Zweite Activity öffnen
            Intent myIntent=new Intent(view.getContext(),MainActivity.class);
            startActivity(myIntent);
            finish();
        }
}
