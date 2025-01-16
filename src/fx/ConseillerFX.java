package fx;

import models.Conseiller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.ConseillerDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;

public class ConseillerFX {
    private TableView<Conseiller> table;
    private ConseillerDAO conseillerDAO;

    public ConseillerFX() {
        conseillerDAO = new ConseillerDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher les conseillers ------//
        table = new TableView<>();
        table.setItems(getConseillers());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<Conseiller> getConseillers() {
        try {
            return FXCollections.observableArrayList(conseillerDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Conseiller, ?>> createTableColumns() {
        TableColumn<Conseiller, Integer> idCol = new TableColumn<>("ID Conseiller");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdConseiller()));

        TableColumn<Conseiller, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Conseiller, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Conseiller, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Conseiller, String> telephoneCol = new TableColumn<>("Téléphone");
        telephoneCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));

        return Arrays.asList(idCol, nomCol, prenomCol, emailCol, telephoneCol);
    }
}