package com.fsts.tpgestionfilmmicda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DaoFilm extends SQLiteOpenHelper {
    // RYANY Firdaousse MICDA M2

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GESTION_DES_FILMS_MICDA";

    private static final String code_film = "code";
    private static final String titre_film = "titre";
    private static final String realisateur_film = "realisateur";
    private static final String date_film = "date";
    private static final String film_table_name = "Films";
    private  static  final String[] colonnes = {code_film,titre_film,realisateur_film,date_film};

    private static final String id_film_evaluer = "id";
    private static final String code_film_evaluer = "codeFilm";
    private static final String valeur_evaluer = "valeur";
    private static final String evaluer_table_name = "EvaluerFilms";
    private  static  final String[] colonnesE = {id_film_evaluer,code_film_evaluer,valeur_evaluer};

    public DaoFilm(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Films ("+"code INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "titre TEXT,"+"realisateur TEXT,"+"date TEXT)";
        db.execSQL(query);
        String query2 = "CREATE TABLE EvaluerFilms ("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "codeFilm INTEGER,"+"valeur REAL)";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Films");
        this.onCreate(db);
    }

    public void addFilm(Film f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v   = new ContentValues();
        v.put(titre_film,f.getTitre());
        v.put(realisateur_film,f.getRealisateur());
        v.put(date_film,f.getDate());
        db.insert(film_table_name,null,v);
        db.close();
    }

    public List<Film> getAllFilms(){
        List<Film> films = new ArrayList<Film>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+film_table_name;
        Cursor c = db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                Film f = new Film();
                f.setCode(c.getInt(0));
                f.setTitre(c.getString(1));
                f.setRealisateur(c.getString(2));
                f.setDate(c.getString(3));
                films.add(f);
            }while (c.moveToNext());
        }
        return films;
    }

    public Film getFilm(int code){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+film_table_name;
        Film f = null;
        Cursor c = db.query(
                film_table_name,
                colonnes,
                code_film +"=?",
                new String[] {String.valueOf(code)},
                null,
                null,
                null,
                null
        );
        if( c != null && c.getCount()>0){
            c.moveToFirst();
            f = new Film();
            f.setCode(c.getInt(0));
            f.setTitre(c.getString(1));
            f.setRealisateur(c.getString(2));
            f.setDate(c.getString(3));
        }
        return f;
    }

    public int deleteFilm (Film f){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(
                film_table_name,
                code_film+"=?",
                new String[] {String.valueOf(f.getCode())}
        );
        db.close();
        return i;
    }

    public int UpateFilm(Film f){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v   = new ContentValues();
        v.put(code_film,f.getCode());
        v.put(titre_film,f.getTitre());
        v.put(realisateur_film,f.getRealisateur());
        v.put(date_film,f.getDate());
        int i = db.update(
                film_table_name,
                v,
                code_film+"=?",
                new String[] {String.valueOf(f.getCode())}
        );
        db.close();
        return i;
    }


    public void addEvaluer(Evaluer e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v   = new ContentValues();
        v.put(code_film_evaluer,e.getCodeFilm());
        v.put(valeur_evaluer,e.getValeur());
        db.insert(evaluer_table_name,null,v);
        db.close();
    }

    public Evaluer getEvaluer(int code){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+film_table_name;
        Evaluer e = null;
        Cursor c = db.query(
                evaluer_table_name,
                colonnesE,
                code_film_evaluer +"=?",
                new String[] {String.valueOf(code)},
                null,
                null,
                null,
                null
        );
        if( c != null && c.getCount()>0){
            c.moveToFirst();
            e = new Evaluer();
            e.setId(c.getInt(0));
            e.setCodeFilm(c.getInt(1));
            e.setValeur(c.getFloat(2));
        }
        return e;
    }

    public int UpateEvaluer(Evaluer e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v   = new ContentValues();
        v.put(code_film_evaluer,e.getCodeFilm());
        v.put(valeur_evaluer,e.getValeur());
        int i = db.update(
                evaluer_table_name,
                v,
                code_film_evaluer+"=?",
                new String[] {String.valueOf(e.getCodeFilm())}
        );
        db.close();
        return i;
    }
}
