package fx;

import models.Compagnie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.CompagnieDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;

public class CompagnieFX {
    private TableView<Compagnie> table;
    private CompagnieDAO compagnieDAO;

    public CompagnieFX() {
        compagnieDAO = new CompagnieDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher les compagnies ------//
        table = new TableView<>();
        table.setItems(getCompagnies());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<Compagnie> getCompagnies() {
        try {
            return FXCollections.observableArrayList(compagnieDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Compagnie, ?>> createTableColumns() {
        TableColumn<Compagnie, Integer> idCol = new TableColumn<>("ID Compagnie");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdCompagnie()));

        TableColumn<Compagnie, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Compagnie, String> paysCol = new TableColumn<>("Pays d'Origine");
        paysCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPaysOrigine()));

        return Arrays.asList(idCol, nomCol, paysCol);
    }
}
