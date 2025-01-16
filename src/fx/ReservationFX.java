package fx;

import models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import dao.ReservationDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.List;

public class ReservationFX {
    private TableView<Reservation> table;
    private ReservationDAO reservationDAO;
    private ExcelFX excelFX;       // référence à ExcelFX

    public ReservationFX(ExcelFX excelFX) {      // injecter ExcelFX dans le constructeur
        reservationDAO = new ReservationDAO();
        this.excelFX = excelFX;
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher les réservations ------//
        table = new TableView<>();
        table.setItems(getReservations());
        table.getColumns().addAll(createTableColumns());

        //------ Boutons CRUD ------//
        HBox buttons = createButtons();

        root.setCenter(table);
        root.setBottom(buttons);

        return root;
    }

    private ObservableList<Reservation> getReservations() {
        try {
            return FXCollections.observableArrayList(reservationDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Reservation, ?>> createTableColumns() {
        TableColumn<Reservation, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdReservation()));

        TableColumn<Reservation, Integer> clientCol = new TableColumn<>("ID Client");
        clientCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdClient()));

        TableColumn<Reservation, Integer> conseillerCol = new TableColumn<>("ID Conseiller");
        conseillerCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdConseiller()));

        TableColumn<Reservation, Integer> volCol = new TableColumn<>("ID Vol");
        volCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdVol()));

        TableColumn<Reservation, String> dateReservationCol = new TableColumn<>("Date Réservation");
        dateReservationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDateReservation().toString()));

        TableColumn<Reservation, String> classeCol = new TableColumn<>("Classe");
        classeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClasse()));

        TableColumn<Reservation, String> typePaiementCol = new TableColumn<>("Paiement");
        typePaiementCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTypePaiement()));

        TableColumn<Reservation, String> statutCol = new TableColumn<>("Statut");
        statutCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatut()));

        TableColumn<Reservation, String> nomReservateurCol = new TableColumn<>("Nom Réservateur");
        nomReservateurCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomReservateur()));

        TableColumn<Reservation, String> prenomReservateurCol = new TableColumn<>("Prénom Réservateur");
        prenomReservateurCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenomReservateur()));

        TableColumn<Reservation, String> numeroReservateurCol = new TableColumn<>("Téléphone Réservateur");
        numeroReservateurCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroReservateur()));

        return Arrays.asList(
            idCol, clientCol, conseillerCol, volCol, dateReservationCol, classeCol,
            typePaiementCol, statutCol, nomReservateurCol, prenomReservateurCol, numeroReservateurCol
        );
    }

    private HBox createButtons() {
        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        addButton.setOnAction(e -> addReservation());
        updateButton.setOnAction(e -> updateReservation());
        deleteButton.setOnAction(e -> deleteReservation());

        HBox buttons = new HBox(10, addButton, updateButton, deleteButton);
        buttons.setPadding(new Insets(10));
        return buttons;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //---------- Consignes pour le bouton "Ajouter" de la table "Reservation" ----------//
    private void addReservation() {
        Dialog<Reservation> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une Réservation");

        //------ Champs de saisie ------//
        TextField clientField = new TextField();
        clientField.setPromptText("ID Client");

        TextField conseillerField = new TextField();
        conseillerField.setPromptText("ID Conseiller");

        TextField volField = new TextField();
        volField.setPromptText("ID Vol");

        DatePicker dateReservationPicker = new DatePicker();

        //------ ComboBox pour Classe
        ComboBox<String> classeComboBox = new ComboBox<>();
        classeComboBox.getItems().addAll("economique", "premiere classe", "business");
        classeComboBox.setPromptText("Sélectionnez une classe");

        //------ ComboBox pour Statut
        ComboBox<String> statutComboBox = new ComboBox<>();
        statutComboBox.getItems().addAll("confirme", "annule");
        statutComboBox.setPromptText("Sélectionnez un statut");

        //------ ComboBox pour Type_Paiement
        ComboBox<String> type_paiementComboBox = new ComboBox<>();
        type_paiementComboBox.getItems().addAll("CB", "virement", "paypal", "especes");
        type_paiementComboBox.setPromptText("Sélectionnez un mode de paiement");

        TextField nomReservateurField = new TextField();
        nomReservateurField.setPromptText("Nom Réservateur");

        TextField prenomReservateurField = new TextField();
        prenomReservateurField.setPromptText("Prénom Réservateur");

        TextField numeroReservateurField = new TextField();
        numeroReservateurField.setPromptText("Téléphone Réservateur");

        VBox fields = new VBox(10,
            new Label("ID Client"), clientField,
            new Label("ID Conseiller"), conseillerField,
            new Label("ID Vol"), volField,
            new Label("Date Réservation"), dateReservationPicker,
            new Label("Classe"), classeComboBox,
            new Label("Statut"), statutComboBox,
            new Label("Paiement"), type_paiementComboBox,
            new Label("Nom Réservateur"), nomReservateurField,
            new Label("Prénom Réservateur"), prenomReservateurField,
            new Label("Téléphone Réservateur"), numeroReservateurField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            String numeroReservateur = numeroReservateurField.getText();

            if (numeroReservateur.length() < 10 || numeroReservateur.length() > 12) {
                showAlert("Erreur", "Le numéro de téléphone doit contenir entre 10 et 12 caractères.");
                event.consume(); 
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                //------ Validation des sélections pour Classe et Statut ------//
                if (classeComboBox.getValue() == null || statutComboBox.getValue() == null) {
                    showAlert("Erreur", "Veuillez sélectionner une valeur pour Classe et Statut.");
                    return null;
                }

                try {
                    //------ Récupération du dernier ID
                    int newId = reservationDAO.getLastId() + 1;

                    //------ Création de la réservation avec le nouvel ID
                    return new Reservation(
                        newId,
                        Integer.parseInt(clientField.getText()),
                        Integer.parseInt(conseillerField.getText()),
                        Integer.parseInt(volField.getText()),
                        dateReservationPicker.getValue(),
                        classeComboBox.getValue(),
                        type_paiementComboBox.getValue(),
                        statutComboBox.getValue(),
                        nomReservateurField.getText(),
                        prenomReservateurField.getText(),
                        numeroReservateurField.getText()
                    );
                } catch (Exception e) {
                    showAlert("Erreur", "Une erreur est survenue lors de la génération de l'ID. Vérifiez les champs saisis.");
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(reservation -> {
            try {
                reservationDAO.create(reservation);
                table.getItems().add(reservation);
                showAlert("Succès", "Réservation ajoutée avec succès !");
                excelFX.refreshData(); // mettre à jour Excel instantanément
            } catch (Exception e) {
                showAlert("Erreur", "Erreur lors de l'ajout de la réservation. Veuillez vérifier si les ID uniques saisis existent bien.");
                e.printStackTrace();
            }
        });
    }

    
    //---------- Consignes pour le bouton "Modifier" de la table "Reservation" ----------//
    private void updateReservation() {
        Reservation selectedReservation = table.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert("Information", "Veuillez sélectionner une réservation à modifier...");
            return;
        }

        Dialog<Reservation> dialog = new Dialog<>();
        dialog.setTitle("Modifier une Réservation");

        //------ Champs de saisie pré-remplis
        TextField clientField = new TextField(String.valueOf(selectedReservation.getIdClient()));
        TextField conseillerField = new TextField(String.valueOf(selectedReservation.getIdConseiller()));
        TextField volField = new TextField(String.valueOf(selectedReservation.getIdVol()));

        DatePicker dateReservationPicker = new DatePicker(selectedReservation.getDateReservation());

        ComboBox<String> classeComboBox = new ComboBox<>();
        classeComboBox.getItems().addAll("economique", "premiere classe", "business");
        classeComboBox.setValue(selectedReservation.getClasse());

        ComboBox<String> typePaiementComboBox = new ComboBox<>();
        typePaiementComboBox.getItems().addAll("CB", "virement", "paypal", "especes");
        typePaiementComboBox.setValue(selectedReservation.getTypePaiement());

        ComboBox<String> statutComboBox = new ComboBox<>();
        statutComboBox.getItems().addAll("confirme", "annule");
        statutComboBox.setValue(selectedReservation.getStatut());

        TextField nomReservateurField = new TextField(selectedReservation.getNomReservateur());
        TextField prenomReservateurField = new TextField(selectedReservation.getPrenomReservateur());
        TextField numeroReservateurField = new TextField(selectedReservation.getNumeroReservateur());

        VBox fields = new VBox(10,
            new Label("ID Client"), clientField,
            new Label("ID Conseiller"), conseillerField,
            new Label("ID Vol"), volField,
            new Label("Date Réservation"), dateReservationPicker,
            new Label("Classe"), classeComboBox,
            new Label("Paiement"), typePaiementComboBox,
            new Label("Statut"), statutComboBox,
            new Label("Nom Réservateur"), nomReservateurField,
            new Label("Prénom Réservateur"), prenomReservateurField,
            new Label("Téléphone Réservateur"), numeroReservateurField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            //------ Validation du numéro réservateur
            String numeroReservateur = numeroReservateurField.getText();
            if (numeroReservateur.length() < 10 || numeroReservateur.length() > 12) {
                showAlert("Erreur", "Le numéro de téléphone doit contenir entre 10 et 12 caractères.");                
                event.consume(); 
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedReservation.setIdClient(Integer.parseInt(clientField.getText()));
                selectedReservation.setIdConseiller(Integer.parseInt(conseillerField.getText()));
                selectedReservation.setIdVol(Integer.parseInt(volField.getText()));
                selectedReservation.setDateReservation(dateReservationPicker.getValue());
                selectedReservation.setClasse(classeComboBox.getValue());
                selectedReservation.setTypePaiement(typePaiementComboBox.getValue());
                selectedReservation.setStatut(statutComboBox.getValue());
                selectedReservation.setNomReservateur(nomReservateurField.getText());
                selectedReservation.setPrenomReservateur(prenomReservateurField.getText());
                selectedReservation.setNumeroReservateur(numeroReservateurField.getText());
                return selectedReservation;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(reservation -> {
            try {
                reservationDAO.update(reservation);
                table.refresh();
                showAlert("Succès", "Réservation modifiée avec succès !");
                System.out.println("Réservation modifiée avec succès !");
                excelFX.refreshData(); // mettre à jour Excel
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Erreur", "Une erreur est survenue lors de la modification de la réservation.");
            }
        });
    }
    

    //---------- Consignes pour le bouton "Supprimer" de la table "Reservation" ----------//
    private void deleteReservation() {
        Reservation selectedReservation = table.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert("Information", "Veuillez sélectionner une réservation à supprimer...");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette réservation ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    reservationDAO.deleteById(selectedReservation.getIdReservation());
                    table.getItems().remove(selectedReservation);
                    showAlert("Confirmation", "Réservation supprimée avec succès.");
                    System.out.println("Réservation supprimée avec succès.");
                    excelFX.refreshData(); // mettre à jour Excel
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Erreur", "Une erreur est survenue lors de la suppression de la réservation.");
                }
            }
        });
    }
}
