package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vol {
    private int idVol;
    private int idCompagnie;
    private String lieuDepart;
    private String lieuArrivee;
    private LocalDate dateDepart;
    private LocalTime heureDepart;
    private LocalDate dateArrivee;
    private LocalTime heureArrivee;

    //------ Constructeur vide ------//
    public Vol() {}

    //------ Constructeur avec tous les paramètres ------//
    public Vol(int idVol, int idCompagnie, String lieuDepart, String lieuArrivee,
               LocalDate dateDepart, LocalTime heureDepart,
               LocalDate dateArrivee, LocalTime heureArrivee) {
        this.idVol = idVol;
        this.idCompagnie = idCompagnie;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.dateArrivee = dateArrivee;
        this.heureArrivee = heureArrivee;
    }

    
    //------ Getters/Setters ------//
    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public int getIdCompagnie() {
        return idCompagnie;
    }

    public void setIdCompagnie(int idCompagnie) {
        this.idCompagnie = idCompagnie;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "idVol=" + idVol +
                ", idCompagnie=" + idCompagnie +
                ", lieuDepart='" + lieuDepart + '\'' +
                ", lieuArrivee='" + lieuArrivee + '\'' +
                ", dateDepart=" + dateDepart +
                ", heureDepart=" + heureDepart +
                ", dateArrivee=" + dateArrivee +
                ", heureArrivee=" + heureArrivee +
                '}';
    }
}
