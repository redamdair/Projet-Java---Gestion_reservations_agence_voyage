package models;

import java.time.LocalDate;

public class Reservation {
    private int idReservation;
    private int idClient;
    private int idConseiller;
    private int idVol;
    private LocalDate dateReservation;
    private String classe;
    private String typePaiement;
    private String statut;
    private String nomReservateur;
    private String prenomReservateur;
    private String numeroReservateur;

    public Reservation() {}

    //------ Constructeur avec tous les paramètres ------//
    public Reservation(int idReservation, int idClient, int idConseiller, int idVol, LocalDate dateReservation,
                       String classe, String typePaiement, String statut,
                       String nomReservateur, String prenomReservateur, String numeroReservateur) {
        this.idReservation = idReservation;
        this.idClient = idClient;
        this.idConseiller = idConseiller;
        this.idVol = idVol;
        this.dateReservation = dateReservation;
        this.classe = classe;
        this.typePaiement = typePaiement;
        this.statut = statut;
        this.nomReservateur = nomReservateur;
        this.prenomReservateur = prenomReservateur;
        this.numeroReservateur = numeroReservateur;
    }
    

    //------ Getters et Setters ------//
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdConseiller() {
        return idConseiller;
    }

    public void setIdConseiller(int idConseiller) {
        this.idConseiller = idConseiller;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNomReservateur() {
        return nomReservateur;
    }

    public void setNomReservateur(String nomReservateur) {
        this.nomReservateur = nomReservateur;
    }

    public String getPrenomReservateur() {
        return prenomReservateur;
    }

    public void setPrenomReservateur(String prenomReservateur) {
        this.prenomReservateur = prenomReservateur;
    }

    public String getNumeroReservateur() {
        return numeroReservateur;
    }

    public void setNumeroReservateur(String numeroReservateur) {
        this.numeroReservateur = numeroReservateur;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", idClient=" + idClient +
                ", idConseiller=" + idConseiller +
                ", idVol=" + idVol +
                ", dateReservation=" + dateReservation +
                ", classe='" + classe + '\'' +
                ", typePaiement='" + typePaiement + '\'' +
                ", statut='" + statut + '\'' +
                ", nomReservateur='" + nomReservateur + '\'' +
                ", prenomReservateur='" + prenomReservateur + '\'' +
                ", numeroReservateur='" + numeroReservateur + '\'' +
                '}';
    }
}
