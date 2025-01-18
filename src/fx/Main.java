package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private ExcelFX excelFX; // pour synchronisation

    @Override
    public void start(Stage primaryStage) {
    	
        excelFX = new ExcelFX();

        //------ Conteneur d'onglets ------//
        TabPane tabPane = new TabPane();

        //------ Onglets pour chaque entité ------//
        Tab clientTab = createTab("Clients", new ClientFX(excelFX).getLayout());                // Passer excelFX pour synchronisation avec Client
        Tab reservationTab = createTab("Réservations", new ReservationFX(excelFX).getLayout()); // Passer excelFX pour synchronisation avec Reservation
        Tab volTab = createTab("Vols", new VolFX().getLayout());
        Tab compagnieTab = createTab("Compagnies", new CompagnieFX().getLayout());
        Tab conseillerTab = createTab("Conseillers", new ConseillerFX().getLayout());
        Tab excelTab = createTab("Fichier Excel", excelFX.getLayout());

        //------ Ajout des onglets au conteneur ------//
        tabPane.getTabs().addAll(clientTab, reservationTab, volTab, compagnieTab, conseillerTab, excelTab);

        //------ Mise en page ------//
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        //------ Configuration fenêtre ------//
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Interface d'un conseiller pour effectuer une réservation pour un client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Tab createTab(String title, BorderPane content) {
        Tab tab = new Tab(title, content);
        tab.setClosable(false);
        return tab;
    }

}
