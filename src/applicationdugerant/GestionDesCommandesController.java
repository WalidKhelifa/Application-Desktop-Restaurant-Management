/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GestionDesCommandesController implements Initializable {

    @FXML
    TableColumn id, id_plat_commande;
    @FXML
    TableColumn date, qte;
    @FXML
    TableColumn heure, id_plat;
    @FXML
    TableColumn montant, designation;
    @FXML
    TableColumn type, portion;
    @FXML
    TableColumn userName;
    @FXML
    TableColumn id_client;
    @FXML
    Pane ap_afficher;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane ap_details;
    @FXML
    TableView<commande> table;
    @FXML
    TableView<platsCommandes> table2;
    @FXML
    JFXButton btndetails;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ap_home.toFront();
        id.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure_commande"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_commande"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        //////

        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        id_plat.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        portion.setCellValueFactory(new PropertyValueFactory<>("portion"));

    }

    public void AfficherCommande(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
        btndetails.setVisible(true);
        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<commande> cmd = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();
        java.sql.Statement myStmt1 = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `id_commande`, `date_commande`, `heure_commande`, `montant`, `type_commande`, commande.`id_client`,`userName` FROM `commande`,`client` WHERE commande.`id_client`=client.`id_client` ;");

            while (myRs.next()) {
                commande cm = new commande(myRs.getInt("id_commande"), myRs.getInt("id_client"), myRs.getString("date_commande"), myRs.getString("heure_commande"), myRs.getString("montant"), myRs.getString("type_commande"), myRs.getString("userName"));
                cmd.add(cm);

            }
            ResultSet myRs1 = myStmt1.executeQuery("SELECT `id_commande`, `date_commande`, `heure_commande`, `montant`, `type_commande`, `id_client` FROM `commande` WHERE `id_client` IS null;");
            while (myRs1.next()) {

                commande cm1 = new commande(myRs1.getInt("id_commande"), myRs1.getString("date_commande"), myRs1.getString("heure_commande"), myRs1.getString("montant"), myRs1.getString("type_commande"));
                cmd.add(cm1);

            }

            // Caster la list a une list Observable
            ObservableList<commande> listObservable = FXCollections.observableArrayList(cmd);
            table.setItems(listObservable);
            table.getSortOrder().add(id);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AfficherDetails(ActionEvent event) throws IOException, SQLException {

        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            btndetails.setVisible(false);
            ap_details.toFront();
            commande cmd;
            cmd = table.getSelectionModel().getSelectedItem();
            int id_cmd = cmd.getId_commande();
            Connection cnx = ApplicationDuGerant.connection;
            ArrayList<platsCommandes> pc = new ArrayList<>();
            java.sql.Statement myStmt = cnx.createStatement();

            try {

                ResultSet myRs = myStmt.executeQuery("SELECT `qte`, `portion`, plats_commandes.id_plat,designation FROM plats_commandes,plat where id_commande =" + id_cmd + " and plats_commandes.id_plat=plat.id_plat;");

                while (myRs.next()) {
                    platsCommandes pcmd = new platsCommandes(myRs.getInt("qte"), myRs.getInt("id_plat"), myRs.getString("portion"), myRs.getString("designation"));
                    pc.add(pcmd);

                }
                // Caster la list a une list Observable
                ObservableList<platsCommandes> listObservable = FXCollections.observableArrayList(pc);
                table2.setItems(listObservable);

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }
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

}
