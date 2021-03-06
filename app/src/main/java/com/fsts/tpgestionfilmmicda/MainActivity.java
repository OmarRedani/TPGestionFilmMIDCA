package com.fsts.tpgestionfilmmicda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listeFilms;
    DaoFilm d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeFilms= findViewById(R.id.listeFilms);
        d= new DaoFilm(this);
        List<Film> films= new ArrayList<Film>();
        films= d.getAllFilms();
        List<String> titreFilms = new ArrayList<String>();
        for(Film f:films){
            titreFilms.add(f.getTitre());
        }
        ListAdapter adptr= new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,titreFilms);
        listeFilms.setAdapter(adptr);

    }

    public void methodeSortir(View v){
        finish();
    }
    public  void methodeVersAjouterActivity(View v){
        Intent i1= new Intent(getApplicationContext(),AjouterFilm.class);
        startActivity(i1);
    }

    public  void methodeVersConsulterActivity(View v){
        Intent i1= new Intent(getApplicationContext(),ConsulterUnFilm.class);
        startActivity(i1);
    }
}