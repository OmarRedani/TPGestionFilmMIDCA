package com.fsts.tpgestionfilmmicda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AjouterFilm extends AppCompatActivity {

    LinearLayout layoutAnnuler,layoutAjouter;

    EditText textTitre,textRealisateur,textDate;

    DaoFilm d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_film);
        layoutAnnuler= findViewById(R.id.layoutAnnuler);
        layoutAjouter= findViewById(R.id.layoutAjouter);
        textTitre= findViewById(R.id.textTitre);
        textRealisateur= findViewById(R.id.textRealisateur);
        textDate= findViewById(R.id.textDate);
        d= new DaoFilm(this);

        /******************Annuler l'ajout*********************/
        layoutAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTitre.setText("");
                textRealisateur.setText("");
                textDate.setText("");
            }
        });

        /******************** Ajouter un film********************/
        layoutAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film f= new Film();
                f.setTitre(textTitre.getText().toString());
                f.setRealisateur(textRealisateur.getText().toString());
                f.setDate(textDate.getText().toString());
                d.addFilm(f);
                textTitre.setText("");
                textRealisateur.setText("");
                textDate.setText("");
            }
        });


    }

    public void RetourVersActivityPrincipale(View v){
        Intent i2= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i2);
    }
}