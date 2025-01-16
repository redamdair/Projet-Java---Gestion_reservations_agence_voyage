package dao;

import models.Reservation;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    //-------------------- Créer une nouvelle réservation --------------------//
    public void create(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservation (ID_Client, ID_Conseiller, ID_Vol, Date_Reservation, Classe, " +
                     "Type_Paiement, Statut, Nom_Reservateur, Prenom_Reservateur, Telephone_Reservateur) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reservation.getIdClient());
            stmt.setInt(2, reservation.getIdConseiller());
            stmt.setInt(3, reservation.getIdVol());
            stmt.setDate(4, Date.valueOf(reservation.getDateReservation()));
            stmt.setString(5, reservation.getClasse());
            stmt.setString(6, reservation.getTypePaiement());
            stmt.setString(7, reservation.getStatut());
            stmt.setString(8, reservation.getNomReservateur());
            stmt.setString(9, reservation.getPrenomReservateur());
            stmt.setString(10, reservation.getNumeroReservateur());
            stmt.executeUpdate();

            // Récupérer ID généré automatiquement par la base de données
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setIdReservation(generatedKeys.getInt(1));
                }
            }
        }
    }

    //-------------------- Trouver une réservation par ID --------------------//
    public Reservation findById(int id) throws SQLException {
        String sql = "SELECT * FROM Reservation WHERE ID_Reservation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("ID_Reservation"),
                        rs.getInt("ID_Client"),
                        rs.getInt("ID_Conseiller"),
                        rs.getInt("ID_Vol"),
                        rs.getDate("Date_Reservation").toLocalDate(),
                        rs.getString("Classe"),
                        rs.getString("Type_Paiement"),
                        rs.getString("Statut"),
                        rs.getString("Nom_Reservateur"),
                        rs.getString("Prenom_Reservateur"),
                        rs.getString("Telephone_Reservateur")
                    );
                }
            }
        }
        return null; // si aucune réservation trouvée
    }

    //-------------------- Récupérer toutes les réservations --------------------//
    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getInt("ID_Reservation"),
                    rs.getInt("ID_Client"),
                    rs.getInt("ID_Conseiller"),
                    rs.getInt("ID_Vol"),
                    rs.getDate("Date_Reservation").toLocalDate(),
                    rs.getString("Classe"),
                    rs.getString("Type_Paiement"),
                    rs.getString("Statut"),
                    rs.getString("Nom_Reservateur"),
                    rs.getString("Prenom_Reservateur"),
                    rs.getString("Telephone_Reservateur")
                ));
            }
        }
        return reservations;
    }


    //-------------------- Mettre à jour une réservation --------------------//
    public void update(Reservation reservation) throws SQLException {
        String sql = "UPDATE Reservation SET ID_Client = ?, ID_Conseiller = ?, ID_Vol = ?, Date_Reservation = ?, " +
                     "Classe = ?, Type_Paiement = ?, Statut = ?, Nom_Reservateur = ?, " +
                     "Prenom_Reservateur = ?, Telephone_Reservateur = ? WHERE ID_Reservation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getIdClient());
            stmt.setInt(2, reservation.getIdConseiller());
            stmt.setInt(3, reservation.getIdVol());
            stmt.setDate(4, Date.valueOf(reservation.getDateReservation()));
            stmt.setString(5, reservation.getClasse());
            stmt.setString(6, reservation.getTypePaiement());
            stmt.setString(7, reservation.getStatut());
            stmt.setString(8, reservation.getNomReservateur());
            stmt.setString(9, reservation.getPrenomReservateur());
            stmt.setString(10, reservation.getNumeroReservateur());
            stmt.setInt(11, reservation.getIdReservation());
            stmt.executeUpdate();
        }
    }


    //-------------------- Supprimer une réservation par ID --------------------//
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE ID_Reservation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    
    //-------------------- Récupérer dernier ID Réservation --------------------//
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(ID_Reservation) AS max_id FROM Reservation";
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
