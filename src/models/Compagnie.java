package models;

public class Compagnie {
    private int idCompagnie;
    private String nom;
    private String paysOrigine;

    //------ Constructeur vide ------//
    public Compagnie() {}

    //------ Constructeur avec tous les paramètres ------//
    public Compagnie(int idCompagnie, String nom, String paysOrigine) {
        this.idCompagnie = idCompagnie;
        this.nom = nom;
        this.paysOrigine = paysOrigine;
    }

    
    //------ Getters et Setters ------//
    public int getIdCompagnie() {
        return idCompagnie;
    }

    public void setIdCompagnie(int idCompagnie) {
        this.idCompagnie = idCompagnie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    @Override
    public String toString() {
        return "Compagnie{" +
                "idCompagnie=" + idCompagnie +
                ", nom='" + nom + '\'' +
                ", paysOrigine='" + paysOrigine + '\'' +
                '}';
    }
}
