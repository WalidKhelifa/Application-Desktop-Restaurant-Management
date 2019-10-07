/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import static applicationdugerant.JfxTextFieldCharLimit.addTextLimiter;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author itashiNL
 */
public class AccueilController implements Initializable {

    @FXML
    private JFXTextField user;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label erreur;
    JfxTextFieldCharLimit limit;
    static String login;

    @FXML
    public void inscrire(ActionEvent event) throws IOException, SQLException {
        erreur.setText("");
        erreur.setTextFill(Color.web("#cc3333"));
        if (!vide()) {
            if (TestEmail()) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de l'inscription");
                alert.setContentText("Voulez vraiment confirmer l'inscription ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    try {
                        Connection cnx = ApplicationDuGerant.connection;
                        //execute sql query
                        java.sql.Statement myStmt = cnx.createStatement();

                        myStmt.execute("INSERT INTO `gerant`(`username_ger`,`email_ger`,`mdp_ger`, `nom_rest`) VALUES ('"
                                + user.getText()
                                + "','" + email.getText()
                                + "','" + pass.getText()
                                + "','" + "Myresto"
                                + "');");

                        login = user.getText();
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

                    } catch (SQLIntegrityConstraintViolationException e) {

                        erreur.setText("Compte déja existant !");
                    }

                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            } else {

            }

            clear();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }
    }

    @FXML
    public void seConnecter(ActionEvent event) throws IOException {
        erreur.setText("");
        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();
            ResultSet myRs = myStmt.executeQuery("select mdp_ger from gerant where username_ger='" + username.getText() + "'");

            //results set
            if (myRs.next() && password.getText().equals(myRs.getString("mdp_ger"))) {
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
            } else {
                erreur();
            }
            login = username.getText();
        } catch (SQLIntegrityConstraintViolationException e) {

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void erreur() {
        if (username.getText().equals("") || password.getText().equals("")) {
            username.setText("");
            password.clear();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs!");
            alert.show();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Identifiant ou mot de passe incorrect!");
            username.setText("");
            password.clear();
            alert.show();
        }
    }

    public void clear() throws IOException {
        user.clear();
        email.clear();
        pass.clear();

    }

    public boolean vide() {
        if (user.getText().isEmpty() || email.getText().isEmpty() || pass.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }

    }

    public boolean TestEmail() {
        int s = 0;
        String str = email.getText();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '@') {
                s++;
            }
            if (s == 1 && str.charAt(i) == '.') {
                s++;
            }
            if (s == 2 && str.charAt(i) == '@') {
                s++;
            }
        }
        if (s != 2) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Test Connection");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Email incorrect!");
            alert.show();
            return false;
        }
        return true;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setStyle("-fx-text-inner-color: BLACK");
        user.setStyle("-fx-text-inner-color: BLACK");
        password.setStyle("-fx-text-inner-color: BLACK");
        pass.setStyle("-fx-text-inner-color: BLACK");
        addTextLimiter(username, 20);
        addTextLimiter(password, 20);
        addTextLimiter(user, 20);
        addTextLimiter(email, 20);
        addTextLimiter(pass, 20);

    }

}
