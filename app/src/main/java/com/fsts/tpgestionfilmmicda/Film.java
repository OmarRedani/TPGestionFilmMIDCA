package com.fsts.tpgestionfilmmicda;

public class Film {

    private int code;
    private String titre;
    private String realisateur;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int codeFilm) {
        this.code = codeFilm;
    }

    public Film() {
        super();
    }

    public Film(String titre, String realisateur, String date) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.date = date;
    }

}
