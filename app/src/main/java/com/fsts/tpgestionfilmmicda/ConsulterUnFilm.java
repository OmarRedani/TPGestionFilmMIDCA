package com.fsts.tpgestionfilmmicda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

public class ConsulterUnFilm extends AppCompatActivity {

    EditText textCode, textTitre,textRealisateur,textDate;
    Button btnOk;
    DaoFilm d;
    LinearLayout layoutSupprimer, layoutModifier;
    Film f;
    RatingBar mRatingBar;

    Evaluer evaluer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_un_film);

        textCode= findViewById(R.id.textcodefilm);
        textTitre= findViewById(R.id.titreval);
        textRealisateur= findViewById(R.id.realisateurval);
        textDate= findViewById(R.id.dateVal);
        mRatingBar = findViewById(R.id.evaluerfilm);

        btnOk= findViewById(R.id.btnRech);

        d= new DaoFilm(this);

        layoutSupprimer= findViewById(R.id.layoutSupprimer);
        layoutModifier= findViewById(R.id.layoutModifier);
        /*****************************Consulter un film***************/
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f= new Film();
                evaluer = new Evaluer();
                evaluer = d.getEvaluer(Integer.parseInt(textCode.getText().toString()));
                f= d.getFilm(Integer.parseInt(textCode.getText().toString()));
                textTitre.setText(f.getTitre());
                textRealisateur.setText(f.getRealisateur());
                textDate.setText(f.getDate());
                try {
                    mRatingBar.setRating(evaluer.getValeur());
                }catch (NullPointerException e){

                }

            }
        });

        /*******Supprimer un film*****/
        layoutSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i= d.deleteFilm(f);
                textCode.setText("");
                textTitre.setText("");
                textRealisateur.setText("");
                textDate.setText("");
                if(i==0){
                    Toast.makeText(getApplicationContext(),"Echec de suppression",
                            Toast.LENGTH_LONG).show();
                }
                if (i==1){
                    Toast.makeText(getApplicationContext(),"Film bien supprimé!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        /********************Modification d un film*************/
        layoutModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film f= new Film();
                Evaluer evaluer = new Evaluer();
                f.setCode(Integer.parseInt(textCode.getText().toString()));
                f.setTitre(textTitre.getText().toString());
                f.setRealisateur(textRealisateur.getText().toString());
                f.setDate(textDate.getText().toString());
                evaluer.setValeur(mRatingBar.getRating());
                d.UpateFilm(f);
                d.addEvaluer(evaluer);
            }
        });



    }
    public void RetourVersActivityPrincipale1(View v){
        Intent i2= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i2);
    }
}