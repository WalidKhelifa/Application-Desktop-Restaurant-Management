/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GestionDesReservationsController implements Initializable {

    @FXML
    TableColumn id_reservation;
    @FXML
    TableColumn id_table;
    @FXML
    TableColumn id_client;
    @FXML
    TableColumn date_reserv;
    @FXML
    TableColumn date_rdv;
    @FXML
    TableColumn heure_reserv;
    @FXML
    TableColumn heure_rdv;
    @FXML
    TableColumn userName;
    @FXML
    TableColumn etat_reserv;
    @FXML
    TableColumn nb_personnes;
    @FXML
    TableView<reservation> table;
    @FXML
    AnchorPane ap_afficher;
    @FXML
    AnchorPane ap_home;
    @FXML
    Label lb;
    @FXML
    JFXToggleButton tb;
    static Boolean sauv_pos;
    private int indice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            ap_home.toFront();
            lb.setVisible(true);
            id_reservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
            id_table.setCellValueFactory(new PropertyValueFactory<>("id_table"));
            id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
            date_reserv.setCellValueFactory(new PropertyValueFactory<>("date_reserv"));
            date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
            heure_reserv.setCellValueFactory(new PropertyValueFactory<>("heure_reserv"));
            heure_rdv.setCellValueFactory(new PropertyValueFactory<>("heure_rdv"));
            etat_reserv.setCellValueFactory(new PropertyValueFactory<>("etat_reserv"));
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            nb_personnes.setCellValueFactory(new PropertyValueFactory<>("nb_personnes"));

            tb.selectedProperty().addListener((ObservableValue< ? extends Boolean> arg0, Boolean arg1, Boolean arg2) -> {
                if (tb.isSelected() == true) {
                    tb.setText("ON");
                    
                } else {
                    tb.setText("OFF");
                    
                }
            });

            Connection cnx = ApplicationDuGerant.connection;
            java.sql.Statement myStmt = cnx.createStatement();

            try {

                ResultSet myRs = myStmt.executeQuery("SELECT `dispo` FROM `tableconnectee`");

                if (myRs.next()) {
                    if (myRs.getInt("dispo") == 1) {
                        tb.setSelected(true);
                        sauv_pos = true;
                    } else {
                        tb.setSelected(false);
                        sauv_pos = false;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionDesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AfficherReservation(ActionEvent event) throws IOException, SQLException {

        tb.setSelected(sauv_pos);
        ap_afficher.toFront();
        tb.setSelected(sauv_pos);

        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<reservation> reserv = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `id_reservation`, `date_reserv`, `heure_reserv`, `date_rdv`, `heure_rdv`,`nb_personnes`, `etat_reserv`, `id_table`, client.`id_client`,`userName` FROM `reservation`,`client` WHERE client.`id_client`=reservation.`id_client`;");

            while (myRs.next()) {
                reservation rs = new reservation(myRs.getInt("id_reservation"), myRs.getInt("id_table"), myRs.getInt("id_client"), myRs.getString("date_reserv"), myRs.getString("date_rdv"), myRs.getString("heure_reserv"), myRs.getString("heure_rdv"), myRs.getString("etat_reserv"), myRs.getString("userName"), myRs.getString("nb_personnes"));
                reserv.add(rs);

            }

            // Caster la list a une list Observable
            ObservableList<reservation> listObservable = FXCollections.observableArrayList(reserv);
            table.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActDesReservation(ActionEvent event) throws IOException, SQLException {

        if (tb.getText().equals("ON")) {
            System.out.println("ok");
            sauv_pos = true;
            indice = 1;
        }
        if (tb.getText().equals("OFF")) {
            System.out.println("non ok");
            sauv_pos = false;
            indice = 0;
        }
        tb.setSelected(sauv_pos);

        try {
            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();

            myStmt.execute("UPDATE `tableconnectee` SET `dispo`=" + indice + ";");

        } catch (SQLException e) {
        }

    }
    
    public void Deconnecter() throws IOException {
        tb.setSelected(sauv_pos);
        ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
        // retour a l'acceuil
        Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        ApplicationDuGerant.stage = stage;
        tb.setSelected(sauv_pos);
    }

    public void retour(ActionEvent event) throws IOException {
        tb.setSelected(sauv_pos);
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
        tb.setSelected(sauv_pos);
    }

}
