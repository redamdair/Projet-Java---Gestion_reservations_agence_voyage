package dao;

import models.Compagnie;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompagnieDAO {
	
	// La seule fonction de cette classe qui sera utilisée pour l'interface graphique est la 3ème 
	//																			(récupère les compagnies pré-enregistrées dans la base de donnée)
	// Les autres sont données à titre indicatif si l'on veut modifier la table des compagnies (supposée gérée uniquement par une administration)

    //-------------------- Créer une nouvelle compagnie --------------------//
    public void create(Compagnie compagnie) throws SQLException {
        String sql = "INSERT INTO Compagnie (Nom, Pays_Origine) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, compagnie.getNom());
            stmt.setString(2, compagnie.getPaysOrigine());
            stmt.executeUpdate();
        }
    }

    //-------------------- Trouver une compagnie par ID --------------------//
    public Compagnie findById(int id) throws SQLException {
        String sql = "SELECT * FROM Compagnie WHERE ID_Compagnie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Compagnie(
                        rs.getInt("ID_Compagnie"),
                        rs.getString("Nom"),
                        rs.getString("Pays_Origine")
                    );
                }
            }
        }
        return null; // si aucune compagnie trouvée
    }

    //-------------------- Récupérer toutes les compagnies --------------------//
    public List<Compagnie> findAll() throws SQLException {
        List<Compagnie> compagnies = new ArrayList<>();
        String sql = "SELECT * FROM Compagnie";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                compagnies.add(new Compagnie(
                    rs.getInt("ID_Compagnie"),
                    rs.getString("Nom"),
                    rs.getString("Pays_Origine")
                ));
            }
        }
        return compagnies;
    }
    
    //-------------------- Supprimer une compagnie par ID --------------------//
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Compagnie WHERE ID_Compagnie = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
