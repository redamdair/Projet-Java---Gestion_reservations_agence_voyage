package fx;

import models.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.VolDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.List;

public class VolFX {
    private TableView<Vol> table;
    private VolDAO volDAO;

    public VolFX() {
        volDAO = new VolDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher les vols ------//
        table = new TableView<>();
        table.setItems(getVols());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<Vol> getVols() {
        try {
            return FXCollections.observableArrayList(volDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Vol, ?>> createTableColumns() {
        TableColumn<Vol, Integer> idCol = new TableColumn<>("ID Vol");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdVol()));

        TableColumn<Vol, Integer> compagnieCol = new TableColumn<>("ID Compagnie");
        compagnieCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdCompagnie()));

        TableColumn<Vol, String> departCol = new TableColumn<>("Lieu de Départ");
        departCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLieuDepart()));

        TableColumn<Vol, String> arriveeCol = new TableColumn<>("Lieu d'Arrivée");
        arriveeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLieuArrivee()));

        TableColumn<Vol, String> dateDepartCol = new TableColumn<>("Date de Départ");
        dateDepartCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateDepart().toString()));

        TableColumn<Vol, String> heureDepartCol = new TableColumn<>("Heure de Départ");
        heureDepartCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHeureDepart().toString()));

        TableColumn<Vol, String> dateArriveeCol = new TableColumn<>("Date d'Arrivée");
        dateArriveeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateArrivee().toString()));

        TableColumn<Vol, String> heureArriveeCol = new TableColumn<>("Heure d'Arrivée");
        heureArriveeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHeureArrivee().toString()));

        return Arrays.asList(
            idCol, compagnieCol, departCol, arriveeCol,
            dateDepartCol, heureDepartCol, dateArriveeCol, heureArriveeCol
        );
    }

}
