/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StatistiqueController implements Initializable {

    @FXML
    AnchorPane ap_home;
    @FXML
    Pane ap_afficher;
    @FXML
    JFXTextField nombreDeCommandes;
    @FXML
    JFXTextField nombreDeClients;
    @FXML
    JFXTextField chiffreAfaire;
    @FXML
    JFXTextField moyenneRestaurant;
    @FXML
    JFXTextField prixMoyenneCommande;

    public void PaneAfficher() throws SQLException {
        ap_afficher.toFront();

        Connection cnx = ApplicationDuGerant.connection;
        //execute sql query
        java.sql.Statement myStmt = cnx.createStatement();
        try {

            int nbr = 0;
            ResultSet myRs = myStmt.executeQuery("select count(*) FROM plats_commandes;");
            if (myRs.next()) {
                nbr = myRs.getInt("count(*)");
            }
            nombreDeCommandes.setText(Integer.toString(nbr));

            int nbr2 = 0;
            ResultSet myRs2 = myStmt.executeQuery("SELECT count(*) FROM `client`;");
            if (myRs2.next()) {
                nbr2 = myRs2.getInt("count(*)");
            }
            nombreDeClients.setText(Integer.toString(nbr2));

            String nbr3 = "";
            ResultSet myRs3 = myStmt.executeQuery("select sum(`montant`) from `commande` where `date_commande` = CURDATE();");
            if (myRs3.next()) {
                nbr3 = myRs3.getString("sum(`montant`)");
            }
            chiffreAfaire.setText(nbr3);

            String nbr4 = "";
            ResultSet myRs4 = myStmt.executeQuery("select AVG(`nb_etoile`) from `avis`;");
            if (myRs4.next()) {
                nbr4 = myRs4.getString("AVG(`nb_etoile`)");
            }
            moyenneRestaurant.setText(nbr4);

            String nbr5 = "";
            ResultSet myRs5 = myStmt.executeQuery("SELECT AVG(`montant`)FROM `commande`;");
            if (myRs5.next()) {
                nbr5 = myRs5.getString("AVG(`montant`)");
            }
            prixMoyenneCommande.setText(nbr5);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ap_home.toFront();
    }

}
