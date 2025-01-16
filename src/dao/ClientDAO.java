package dao;

import models.Client;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    //-------------------- Créer un client -------------------- //
    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO Client (Nom, Prenom, Adresse, Email, Telephone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getAdresse());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getTelephone());
            stmt.executeUpdate();
        }
    }

    //-------------------- Trouver un client par ID --------------------//
    public Client findById(int id) throws SQLException {
        String sql = "SELECT * FROM Client WHERE ID_Client = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                        rs.getInt("ID_Client"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Adresse"),
                        rs.getString("Email"),
                        rs.getString("Telephone")
                    );
                }
            }
        }
        return null; // si aucun client n'est trouvé
    }

    // -------------------- Récupérer tous les clients -------------------- //
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("ID_Client"),
                    rs.getString("Nom"),
                    rs.getString("Prenom"),
                    rs.getString("Adresse"),
                    rs.getString("Email"),
                    rs.getString("Telephone")
                ));
            }
        }
        return clients;
    }

    //-------------------- Mettre à jour un client --------------------//
    public void update(Client client) throws SQLException {
        String sql = "UPDATE Client SET Nom = ?, Prenom = ?, Adresse = ?, Email = ?, Telephone = ? WHERE ID_Client = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getAdresse());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getTelephone());
            stmt.setInt(6, client.getIdClient());
            stmt.executeUpdate();
        }
    }

    //-------------------- Supprimer un client par ID --------------------//
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Client WHERE ID_Client = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    //-------------------- Récupérer dernier ID présent dans la table --------------------//
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(ID_Client) AS max_id FROM Client";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("max_id"); // dernier ID utilisé
            }
        }
        return 0; // si aucune ligne n'existe
    }
}
