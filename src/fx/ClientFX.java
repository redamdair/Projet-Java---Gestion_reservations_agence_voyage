package fx;

import models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import dao.ClientDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class ClientFX {
    private TableView<Client> table;
    private ClientDAO clientDAO;
    private ExcelFX excelFX;       // référence à ExcelFX

    
    public ClientFX(ExcelFX excelFX) {      // injecter ExcelFX dans le constructeur
        clientDAO = new ClientDAO();
        this.excelFX = excelFX;
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Tableau pour afficher les clients ------//
        table = new TableView<>();
        table.setItems(getClients());
        table.getColumns().addAll(createTableColumns());

        //------ Boutons CRUD ------//
        HBox buttons = createButtons();

        root.setCenter(table);
        root.setBottom(buttons);

        return root;
    }

    private ObservableList<Client> getClients() {
        try {
            return FXCollections.observableArrayList(clientDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Client, ?>> createTableColumns() {
    	
    	TableColumn<Client, Integer> idCol = new TableColumn<>("ID Client");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getIdClient()));
        
        TableColumn<Client, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Client, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Client, String> adresseCol = new TableColumn<>("Adresse");
        adresseCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdresse()));

        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Client, String> telephoneCol = new TableColumn<>("Téléphone");
        telephoneCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));

        return Arrays.asList(idCol, nomCol, prenomCol, adresseCol, emailCol, telephoneCol);
    }

    private HBox createButtons() {
        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        addButton.setOnAction(e -> addClient());
        updateButton.setOnAction(e -> updateClient());
        deleteButton.setOnAction(e -> deleteClient());

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

    
    //---------- Consignes pour le bouton "Ajouter" de la table "Client" ----------//
    private void addClient() {
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un Client");

        //------ Champs de saisie ------//
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        TextField adresseField = new TextField();
        adresseField.setPromptText("Adresse");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField telephoneField = new TextField();
        telephoneField.setPromptText("Téléphone");

        VBox fields = new VBox(10,
            new Label("Nom"), nomField,
            new Label("Prénom"), prenomField,
            new Label("Adresse"), adresseField,
            new Label("Email"), emailField,
            new Label("Téléphone"), telephoneField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        //------ Ajout d'un bouton "Enregistrer" avec validation ------//
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            String telephone = telephoneField.getText();

            //------ Validation taille numéro de téléphone ------//
            if (telephone.length() < 10 || telephone.length() > 12) {
                showAlert("Erreur", "Le numéro de téléphone doit contenir entre 10 et 12 caractères.");
                event.consume();   // empêche fermeture boîte de dialogue
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    //------ Génération dynamique de ID_Client ------//
                    int newId = clientDAO.getLastId() + 1;

                    return new Client(newId,
                        nomField.getText(),
                        prenomField.getText(),
                        adresseField.getText(),
                        emailField.getText(),
                        telephoneField.getText()
                    );
                } catch (Exception e) {
                    showAlert("Erreur", "Une erreur est survenue lors de la génération de l'ID.");
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(client -> {
            try {
                clientDAO.create(client);
                table.getItems().add(client);
                showAlert("Succès", "Client ajouté avec succès !");
            } catch (Exception e) {
                showAlert("Erreur", "Une erreur est survenue lors de l'ajout du client.");
                e.printStackTrace();
            }
        });
    }


    //---------- Consignes pour le bouton "Modifier" de la table "Client" ----------//
    private void updateClient() {
        Client selectedClient = table.getSelectionModel().getSelectedItem();
        if (selectedClient == null) {
            showAlert("Information", "Veuillez sélectionner un client à modifier...");
            return;
        }

        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Modifier un Client");

        //------ Champs de saisie préremplis ------//
        TextField nomField = new TextField(selectedClient.getNom());
        TextField prenomField = new TextField(selectedClient.getPrenom());
        TextField adresseField = new TextField(selectedClient.getAdresse());
        TextField emailField = new TextField(selectedClient.getEmail());
        TextField telephoneField = new TextField(selectedClient.getTelephone());

        VBox fields = new VBox(10,
            new Label("Nom"), nomField,
            new Label("Prénom"), prenomField,
            new Label("Adresse"), adresseField,
            new Label("Email"), emailField,
            new Label("Téléphone"), telephoneField);
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            String telephone = telephoneField.getText();

            if (telephone.length() < 10 || telephone.length() > 12) {
                showAlert("Erreur", "Le numéro de téléphone doit contenir entre 10 et 12 caractères.");
                event.consume();
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedClient.setNom(nomField.getText());
                selectedClient.setPrenom(prenomField.getText());
                selectedClient.setAdresse(adresseField.getText());
                selectedClient.setEmail(emailField.getText());
                selectedClient.setTelephone(telephoneField.getText());
                return selectedClient;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(client -> {
            try {
                clientDAO.update(client);
                table.refresh(); // Rafraîchit les données dans la table
                showAlert("Succès", "Client modifié avec succès !");
                System.out.println("Client modifié avec succès !");
                excelFX.refreshData(); // Rafraîchit les données Excel
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Erreur", "Erreur lors de la modification du client. Veuillez vérifier les champs saisis.");
            }
        });
    }


    //---------- Consignes pour le bouton "Supprimer" de la table "Client" ----------//
    private void deleteClient() {
        Client selectedClient = table.getSelectionModel().getSelectedItem();
        if (selectedClient == null) {
            showAlert("Information", "Veuillez sélectionner un client à supprimer...");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce client ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    clientDAO.deleteById(selectedClient.getIdClient());
                    table.getItems().remove(selectedClient);
                    showAlert("Confirmation", "Client supprimé avec succès.");
                    System.out.println("Client supprimé avec succès.");
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Erreur", "Veuillez à supprimer toute réservation du client en question afin de pouvoir le retirer de la table.");
                    System.out.println("Veuillez à supprimer toute réservation du client en question afin de pouvoir le retirer de la table.");
                }
            }
        });
    }

}