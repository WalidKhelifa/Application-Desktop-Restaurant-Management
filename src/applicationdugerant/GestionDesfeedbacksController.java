/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author itashiNL
 */
public class GestionDesfeedbacksController implements Initializable {

    @FXML
    Pane ap_afficher;
    @FXML
    AnchorPane ap_home;
    @FXML
    TableView<avis> table;
    @FXML
    TableColumn id;
    @FXML
    TableColumn date_avis;
    @FXML
    TableColumn nombre_etoile;
    @FXML
    TableColumn heure_avis;
    @FXML
    TableColumn commentaire;
    @FXML
    TableColumn id_client;
    @FXML
    TableColumn userName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap_home.toFront();

        id.setCellValueFactory(new PropertyValueFactory<>("id_avis"));
        date_avis.setCellValueFactory(new PropertyValueFactory<>("date_avis"));
        nombre_etoile.setCellValueFactory(new PropertyValueFactory<>("nb_etoile"));
        heure_avis.setCellValueFactory(new PropertyValueFactory<>("heure_avis"));
        commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

    }

    public void Afficherfeedbacks(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<avis> av = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `id_avis`, `date_avis`, `nb_etoile`, `heure_avis`, `commentaire`, avis.`id_client`,`userName` FROM `avis`,`client` WHERE client.`id_client`=avis.`id_client` ;");

            while (myRs.next()) {
                avis avis_client = new avis(myRs.getString("commentaire"), myRs.getString("date_avis"), myRs.getString("heure_avis"), myRs.getInt("id_avis"), myRs.getString("nb_etoile"),
                        myRs.getInt("id_client"), myRs.getString("userName"));
                av.add(avis_client);
            }

            ObservableList<avis> listObservable = FXCollections.observableArrayList(av);
            table.setItems(listObservable);

        } catch (SQLException e) {

        }
    }

    public void Deconnecter() throws IOException {
        ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
        // retour a l'acceuil
        Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        ApplicationDuGerant.stage = stage;
    }

    public void retour(ActionEvent event) throws IOException {
        ApplicationDuGerant.stage.close(); // Fermer la fenetre d'acceuil
        // ouvrir la fenetre Principale
        Parent parent = FXMLLoader.load(getClass().getResource("Principale.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        ApplicationDuGerant.stage = stage;
        stage.setTitle("Principale");
    }

}
