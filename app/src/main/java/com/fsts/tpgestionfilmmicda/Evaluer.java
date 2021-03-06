package com.fsts.tpgestionfilmmicda;

public class Evaluer {

    private int id;
    private int codeFilm;
    private float valeur;

    public Evaluer(int codeFilm, float valeur) {
        this.codeFilm = codeFilm;
        this.valeur = valeur;
    }
    public Evaluer() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeFilm() {
        return codeFilm;
    }

    public void setCodeFilm(int codeFilm) {
        this.codeFilm = codeFilm;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }
}

