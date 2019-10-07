/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import static applicationdugerant.JfxTextFieldCharLimit.addTextLimiter;
import static applicationdugerant.JfxTextFieldCharLimit.addTextLimiterArea;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GestionDuRestaurantController implements Initializable {

    @FXML
    AnchorPane ap_afficher;
    @FXML
    AnchorPane ap_home;
    @FXML
    Pane ap_modifier;
    @FXML
    JFXTextField tf1;
    @FXML
    JFXTextField tf2;
    @FXML
    JFXTextField tf3;
    @FXML
    JFXTimePicker tp1;
    @FXML
    JFXTimePicker tp2;
    @FXML
    JFXTextArea ta1;
    @FXML
    JFXTextField tf11;
    @FXML
    JFXTextField tf22;
    @FXML
    JFXTextField tf33;
    @FXML
    JFXTextField tf44;
    @FXML
    JFXTextField tf55;
    @FXML
    JFXTextArea ta11;
    @FXML
    Button btnmodify;

    public void Afficher(ActionEvent event) throws IOException, SQLException {

        ap_afficher.toFront();

        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from restaurant");
            if (myRs.next()) {
                tf11.setText(myRs.getString(1));
                tf22.setText(myRs.getString(4));
                tf33.setText(myRs.getString(5));
                tf44.setText(myRs.getString(6));
                tf55.setText(myRs.getString(7));
                ta11.setText(myRs.getString(3));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Modifier(ActionEvent event) throws IOException, SQLException, ParseException {

        ap_modifier.toFront();

        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from restaurant");
            if (myRs.next()) {
                tf1.setText(myRs.getString(1));
                tf2.setText(myRs.getString(4));
                tf3.setText(myRs.getString(5));
                DateFormat format = new SimpleDateFormat("HH:mm");
                ta1.setText(myRs.getString(3));
                LocalTime date_ouverture = LocalTime.parse(format.format(format.parse(myRs.getString(6))));
                LocalTime date_fermeture = LocalTime.parse(format.format(format.parse(myRs.getString(7))));
                tp1.setValue(date_ouverture);
                tp2.setValue(date_fermeture);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ConfirmerModification() throws IOException, SQLException {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de modification");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                myStmt.execute("update restaurant set num_tel_rest='" + tf2.getText()
                        + "' , email_rest ='" + tf3.getText()
                        + "' , description ='" + ta1.getText()
                        + "' , horaire_ouv ='" + tp1.getValue()
                        + "' , horaire_ferm ='" + tp2.getValue()
                        + "' where nom_rest = '" + tf1.getText() + "';");
            } else {
                // ... user chose CANCEL or closed the dialog
            }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ap_home.toFront();
        tp1.setEditable(false);
        tp2.setEditable(false);
        addTextLimiter(tf2, 10);
        addTextLimiter(tf3, 20);
        addTextLimiterArea(ta1, 200);
        // force the field to be numeric only
        tf2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf2.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

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
