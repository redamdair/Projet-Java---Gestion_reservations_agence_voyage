package dao;

import models.Conseiller;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConseillerDAO {
	
	// La seule fonction de cette classe qui sera utilisée pour l'interface graphique est la 3ème 
	                                                                // (affiche uniquement les conseillers pré-enregistrés dans la base de donnée)
	// Les autres sont données à titre indicatif si l'on veut modifier la table des conseillers (supposée gérée uniquement par une administration)

    //-------------------- Créer un nouveau conseiller --------------------//
    public void create(Conseiller conseiller) throws SQLException {
        String sql = "INSERT INTO Conseiller (Nom, Prenom, Email, Telephone) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conseiller.getNom());
            stmt.setString(2, conseiller.getPrenom());
            stmt.setString(3, conseiller.getEmail());
            stmt.setString(4, conseiller.getTelephone());
            stmt.executeUpdate();
        }
    }

    //-------------------- Trouver un conseiller par ID --------------------//
    public Conseiller findById(int id) throws SQLException {
        String sql = "SELECT * FROM Conseiller WHERE ID_Conseiller = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conseiller(
                        rs.getInt("ID_Conseiller"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("Telephone")
                    );
                }
            }
        }
        return null; // si aucun conseiller trouvé
    }

    //-------------------- Récupérer tous les conseillers --------------------//
    public List<Conseiller> findAll() throws SQLException {
        List<Conseiller> conseillers = new ArrayList<>();
        String sql = "SELECT * FROM Conseiller";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                conseillers.add(new Conseiller(
                    rs.getInt("ID_Conseiller"),
                    rs.getString("Nom"),
                    rs.getString("Prenom"),
                    rs.getString("Email"),
                    rs.getString("Telephone")
                ));
            }
        }
        return conseillers;
    }
    
    //-------------------- Supprimer un conseiller par ID --------------------//
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Conseiller WHERE ID_Conseiller = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
