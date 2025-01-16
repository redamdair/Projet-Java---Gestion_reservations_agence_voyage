package fx;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import models.*;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class ExcelFX {
    private TableView<List<Object>> table;
    private ClientDAO clientDAO;
    private ConseillerDAO conseillerDAO;
    private ReservationDAO reservationDAO;
    private VolDAO volDAO;
    private CompagnieDAO compagnieDAO;

    public ExcelFX() {
        clientDAO = new ClientDAO();
        conseillerDAO = new ConseillerDAO();
        reservationDAO = new ReservationDAO();
        volDAO = new VolDAO();
        compagnieDAO = new CompagnieDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher toutes les données Excel ------//
        table = new TableView<>();
        refreshData();    // charger données au démarrage
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);
        return root;
    }

    // Mise à jour instantanée de JavaFx sur la table "Fichier Excel" lorsque l'on modifie une autre table (Client ou reservation) sans avoir à re-exécuter le Main 
    public void refreshData() {
        ObservableList<List<Object>> data = getAllData();
        if (table != null) {
            table.setItems(data); 
            table.refresh();    // rafraîchit l'affichage
        }
    }


    private ObservableList<List<Object>> getAllData() {
        List<List<Object>> data = new ArrayList<>();

        try {
            List<Client> clients = clientDAO.findAll();
            List<Reservation> reservations = reservationDAO.findAll();
            List<Vol> vols = volDAO.findAll();
            List<Compagnie> compagnies = compagnieDAO.findAll();
            List<Conseiller> conseillers = conseillerDAO.findAll();

            for (Reservation reservation : reservations) {
                Client client = clients.stream().filter(c -> c.getIdClient() == reservation.getIdClient()).findFirst().orElse(null);
                Conseiller conseiller = conseillers.stream().filter(c -> c.getIdConseiller() == reservation.getIdConseiller()).findFirst().orElse(null);
                Vol vol = vols.stream().filter(v -> v.getIdVol() == reservation.getIdVol()).findFirst().orElse(null);
                Compagnie compagnie = vol != null ? compagnies.stream().filter(comp -> comp.getIdCompagnie() == vol.getIdCompagnie()).findFirst().orElse(null) : null;

                List<Object> row = new ArrayList<>();
                if (client != null) {
                    row.add(client.getIdClient());
                    row.add(client.getNom());
                    row.add(client.getPrenom());
                    row.add(client.getAdresse());
                    row.add(client.getEmail());
                    row.add(client.getTelephone());
                }
                if (reservation != null) {
                    row.add(reservation.getIdReservation());
                    row.add(reservation.getDateReservation());
                    row.add(reservation.getClasse());
                    row.add(reservation.getTypePaiement());
                    row.add(reservation.getStatut());
                    row.add(reservation.getNomReservateur());
                    row.add(reservation.getPrenomReservateur());
                    row.add(reservation.getNumeroReservateur());
                }
                if (vol != null) {
                    row.add(vol.getIdVol());
                    row.add(vol.getLieuDepart());
                    row.add(vol.getLieuArrivee());
                    row.add(vol.getDateDepart());
                    row.add(vol.getHeureDepart());
                    row.add(vol.getDateArrivee());
                    row.add(vol.getHeureArrivee());
                }
                if (compagnie != null) {
                    row.add(compagnie.getIdCompagnie());
                    row.add(compagnie.getNom());
                    row.add(compagnie.getPaysOrigine());
                }
                if (conseiller != null) {
                    row.add(conseiller.getIdConseiller());
                    row.add(conseiller.getNom());
                    row.add(conseiller.getPrenom());
                    row.add(conseiller.getEmail());
                    row.add(conseiller.getTelephone());
                }

                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(data);
    }

 
    private List<TableColumn<List<Object>, ?>> createTableColumns() {
        List<String> headers = List.of(
            "ID Client", "Nom Client", "Prénom Client", "Adresse Client", "Email Client", "Téléphone Client",
            "ID Réservation", "Date Réservation", "Classe", "Type Paiement", "Statut", "Nom Réservateur", "Prénom Réservateur", "Téléphone Réservateur",
            "ID Vol", "Lieu Départ", "Lieu Arrivée", "Date Départ", "Heure Départ", "Date Arrivée", "Heure Arrivée",
            "ID Compagnie", "Nom Compagnie", "Pays Origine",
            "ID Conseiller", "Nom Conseiller", "Prénom Conseiller", "Email Conseiller", "Téléphone Conseiller"

        );

        List<TableColumn<List<Object>, ?>> columns = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            final int index = i;
            TableColumn<List<Object>, Object> column = new TableColumn<>(headers.get(i));
            column.setCellValueFactory(data -> {
                if (data.getValue().size() > index) {
                    return new SimpleObjectProperty<>(data.getValue().get(index));
                } else {
                    return new SimpleObjectProperty<>(null);
                }
            });
            columns.add(column);
        }

        return columns;
    }
}
