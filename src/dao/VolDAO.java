package dao;

import models.Vol;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolDAO {
	
	// La seule fonction de cette classe qui sera utilisée pour l'interface graphique est la 3ème 
	//																  (récupère uniquement les vols pré-enregistrés dans la base de donnée)
	// Les autres sont données à titre indicatif si l'on veut modifier la table des vols (supposée gérée uniquement par une administration)


    //-------------------- Créer un nouveau vol --------------------//
    public void create(Vol vol) throws SQLException {
        String sql = "INSERT INTO Vol (ID_Compagnie, Lieu_Depart, Lieu_Arrivee, Date_Depart, Heure_Depart, Date_Arrivee, Heure_Arrivee) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vol.getIdCompagnie());
            stmt.setString(2, vol.getLieuDepart());
            stmt.setString(3, vol.getLieuArrivee());
            stmt.setDate(4, Date.valueOf(vol.getDateDepart()));
            stmt.setTime(5, Time.valueOf(vol.getHeureDepart()));
            stmt.setDate(6, Date.valueOf(vol.getDateArrivee()));
            stmt.setTime(7, Time.valueOf(vol.getHeureArrivee()));
            stmt.executeUpdate();
        }
    }

    //-------------------- Trouver un vol par ID --------------------//
    public Vol findById(int id) throws SQLException {
        String sql = "SELECT * FROM Vol WHERE ID_Vol = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vol(
                        rs.getInt("ID_Vol"),
                        rs.getInt("ID_Compagnie"),
                        rs.getString("Lieu_Depart"),
                        rs.getString("Lieu_Arrivee"),
                        rs.getDate("Date_Depart").toLocalDate(),
                        rs.getTime("Heure_Depart").toLocalTime(),
                        rs.getDate("Date_Arrivee").toLocalDate(),
                        rs.getTime("Heure_Arrivee").toLocalTime()
                    );
                }
            }
        }
        return null; // si aucun vol trouvé
    }

    //-------------------- Récupérer tous les vols --------------------//
    public List<Vol> findAll() throws SQLException {
        List<Vol> vols = new ArrayList<>();
        String sql = "SELECT * FROM Vol";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                vols.add(new Vol(
                    rs.getInt("ID_Vol"),
                    rs.getInt("ID_Compagnie"),
                    rs.getString("Lieu_Depart"),
                    rs.getString("Lieu_Arrivee"),
                    rs.getDate("Date_Depart").toLocalDate(),
                    rs.getTime("Heure_Depart").toLocalTime(),
                    rs.getDate("Date_Arrivee").toLocalDate(),
                    rs.getTime("Heure_Arrivee").toLocalTime()
                ));
            }
        }
        return vols;
    }
    
    //-------------------- Supprimer un vol par ID --------------------//
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Vol WHERE ID_Vol = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

   
}
