/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author itashiNL
 */
public class GestionDuPersonnelController implements Initializable {

    @FXML
    TextField add_nom, add_nom1;
    @FXML
    TextField add_prenom, add_prenom1;
    @FXML
    TextField add_numero_tel, add_numero_tel1;
    @FXML
    TextField add_email, add_email1;
    @FXML
    DatePicker add_date, add_date1;
    @FXML
    ComboBox<String> genre, genre1;
    @FXML
    ComboBox<String> type_personnel, type_personnel1, type_service;
    @FXML
    TableView<Personnel> table;
    @FXML
    TableView<Personnel> table4;
    @FXML
    TableView<Personnel> table3;
    @FXML
    TableColumn columnDateNaissance, columnDateNaissance4;
    @FXML
    TableColumn columnNom, columnNom4;
    @FXML
    TableColumn columnPrenom, columnPrenom4;
    @FXML
    TableColumn columnAdresse, columnAdresse4;
    @FXML
    TableColumn columnGenre, columnGenre4;
    @FXML
    TableColumn columnEmail, columnEmail4;
    @FXML
    TableColumn columnType_personnel, columnType_personnel4;
    @FXML
    TableColumn columnNumeroTel, columnNumeroTel4;
    @FXML
    TableColumn columnUserGerant, columnUserGerant4;
    @FXML
    TableColumn idPersonnel;
    @FXML
    TableColumn nomPersonnel;
    @FXML
    TableColumn dateService;
    @FXML
    TableColumn idTable;
    @FXML
    AnchorPane ap_ajouter;
    @FXML
    AnchorPane ap_home;
    @FXML
    Pane ap_afficher;
    @FXML
    AnchorPane planning, planning1;
    @FXML
    AnchorPane ap_modifier;
    @FXML
    AnchorPane ap_confirme_modifay;
    @FXML
    AnchorPane affiche_affectation;
    @FXML
    JFXDatePicker dp1;
    @FXML
    JFXDatePicker dp2;
    @FXML
    JFXComboBox<String> cb1;
    @FXML
    JFXButton btnAffecter, btnmodifier;
    @FXML
    Button btnSupprimer;
    @FXML
    JFXTextField tf1, tf11;
    @FXML
    JFXTextField tf2, tf22;
    @FXML
    JFXTextArea adresse_personnel, adresse_personnel1;
    private int id_perso;
    private int sauv_id;
    private int sauv_id_serveur;

    //////////////////////
    @FXML
    TableColumn<TableSelection, String> nomTable;
    @FXML
    TableColumn<TableSelection, String> nomTable1;
    @FXML
    TableColumn<TableSelection, CheckBox> selectTable;
    @FXML
    TableColumn<TableSelection, CheckBox> selectTable1;
    @FXML
    TableView<TableSelection> tableRestaurant, tableRestaurant1;
    private ArrayList<TableSelection> ArrayListeTable, ArrayListeTable1, ArrayListeTable2;
    ObservableList<TableSelection> listeTable, listeTable1;

    //////////////////////////////////////////////// Supprimer un personnel
    public void delete() throws SQLException {
        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table.getSelectionModel().getSelectedItem();
            String nom = personnel.getNom();
            int Id = personnel.getId();
            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            java.sql.Statement myStmt2 = cnx.createStatement();

            try {
                // myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`="+Id+";");
                myStmt.execute("delete from personnel where id_personnel=" + Id + ";");

            } catch (SQLException e) {

            }
            table.getItems().remove(personnel);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }
    }
    /////////////////////////////////////////////////////////////////////          

    //////////////////////////////////////////////// Affecter un personnel        
    public void affecter() throws SQLException {

        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            cb1.setValue("Matin");
            tf1.setText("9:30");
            tf2.setText("11:59");
            av();
            Personnel personnel;
            int id_serv = 0;
            personnel = table.getSelectionModel().getSelectedItem();
            int id = personnel.getId();
            String nom = personnel.getNom();
            String prenom = personnel.getPrenom();
            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            java.sql.Statement myStmt1 = cnx.createStatement();

            try {
                if (personnel.getType_personnel().equals("Serveur")) {
                    ResultSet myRs3 = myStmt1.executeQuery("SELECT `id_personnel` FROM `personnel_service` WHERE `id_personnel`=" + id + " ;");
                    if (myRs3.next()) {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur");
                        // Header Text: null
                        alert.setHeaderText(null);
                        alert.setContentText("Ce personnel est déjà affecté !");
                        alert.show();

                    } else {
                        planning.toFront();
                        ResultSet myRs1 = myStmt.executeQuery("SELECT `id_table` FROM `tableconnectee` WHERE `id_serveur` is NULL;");
                        ArrayListeTable = new ArrayList<>();
                        while (myRs1.next()) {
                            CheckBox ch = new CheckBox("" + myRs1.getInt(1));
                            ArrayListeTable.add(new TableSelection("Table" + " " + myRs1.getInt(1), ch));
                        }
                        listeTable = FXCollections.observableArrayList(ArrayListeTable);
                        tableRestaurant.setItems(listeTable);
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Affectation impossible!");
                    alert.show();
                }
                dp1.setValue(LocalDate.of(2019, Month.JULY, 14));

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }

    }

    public void av() {
        if (cb1.getValue().equals("Matin")) {
            tf1.setText("9:30");
            tf2.setText("11:59");
        } else if (cb1.getValue().equals("Apres-Midi")) {
            tf1.setText("12:00");
            tf2.setText("17:59");
        } else {
            tf1.setText("18:00");
            tf2.setText("23:59");
        }
    }

    public void retourAffectation() {
        ap_afficher.toFront();
    }

    public void retourModifierAffectation() {
        affiche_affectation.toFront();
    }

    public void btnConfirmerAffectation(ActionEvent event) throws SQLException, ParseException {
        int bol = 0;
        if (!videAffect()) {
            if (!tableRestaurant.getItems().isEmpty()) {
                for (int j = 0; j < tableRestaurant.getItems().size(); j++) {
                    if (tableRestaurant.getItems().get(j).getCheckbox().isSelected()) {
                        bol = 1;
                    }
                }
                if (bol == 1) {

                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Confirmation de l'ajout");
                    alert.setContentText("Voulez vraiment confirmer l'ajout ?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        Personnel personnel;
                        int id_serv = 0;
                        personnel = table.getSelectionModel().getSelectedItem();
                        String type = personnel.getType_personnel();
                        String nom = personnel.getNom();
                        String prenom = personnel.getPrenom();
                        int id = personnel.getId();
                        Connection cnx = ApplicationDuGerant.connection;
                        //execute sql query
                        java.sql.Statement myStmt = cnx.createStatement();
                        java.sql.Statement myStmt1 = cnx.createStatement();
                        java.sql.Statement myStmt2 = cnx.createStatement();
                        java.sql.Statement myStmt3 = cnx.createStatement();
                        try {

                            ResultSet myRs2 = myStmt1.executeQuery("SELECT `id_service` FROM `service` WHERE type_service ='" + cb1.getValue() + "';");
                            if (myRs2.next()) {
                                id_serv = myRs2.getInt("id_service");
                            }

                            ResultSet myRs3 = myStmt3.executeQuery("SELECT `id_personnel` FROM `personnel_service` WHERE `id_personnel`=" + id + " ;");
                            if (myRs3.next()) {
                                System.out.println("il exsiste deja");
                            } else {

                                myStmt2.execute("INSERT INTO `personnel_service`(`date_service`, `id_personnel`, `id_service`) VALUES  ('"
                                        + dp1.getValue()
                                        + "'," + id
                                        + "," + id_serv
                                        + ");");
                            }

                            for (int i = 0; i < tableRestaurant.getItems().size(); i++) {
                                if (tableRestaurant.getItems().get(i).getCheckbox().isSelected()) {
                                    myStmt.execute("UPDATE `tableconnectee` SET `id_serveur`=" + id + " WHERE `id_table`="
                                            + tableRestaurant.getItems().get(i).getNum() + ";");

                                }
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        AfficherlistAffectation(event);
                    } else {

                        dp1.setValue(LocalDate.of(2019, Month.JULY, 14));

                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez choisir au moins une table!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Aucune table disponible!");
                alert.show();
            }

        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();

            dp1.setValue(LocalDate.of(2019, Month.JULY, 14));

        }

    }

    public boolean videAffect() {
        if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || cb1.getValue() == null) {
            return true;

        } else {
            return false;
        }
    }

    public boolean videAffect2() {
        if (tf11.getText().isEmpty() || tf22.getText().isEmpty() || type_service.getValue() == null) {
            return true;

        } else {
            return false;
        }
    }

    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////// Ajouter un personnel           
    public void PaneAjouter(ActionEvent event) throws IOException, SQLException {
        ap_ajouter.toFront();

    }

    public void ajouterPersonnel(ActionEvent event) throws IOException, SQLException {
        type_personnel.setValue("Cuisinier");
        genre.setValue("Homme");
        if (!vide()) {
            if (TestEmail()) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de l'ajout");
                alert.setContentText("Voulez vraiment confirmer l'ajout ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {

                    try {
                        Connection cnx = ApplicationDuGerant.connection;
                        //execute sql query
                        java.sql.Statement myStmt = cnx.createStatement();

                        myStmt.execute("INSERT INTO `personnel`(`nom_pers`, `pren_pers`, `dateNaissPers`, `genre_pers`, `adresse_pers`, `num_tel_pers`, `email_pers`, `type_pers`, `username_ger`) VALUES('"
                                + add_nom.getText()
                                + "','" + add_prenom.getText()
                                + "','" + add_date.getValue()
                                + "','" + genre.getValue()
                                + "','" + adresse_personnel.getText()
                                + "','" + add_numero_tel.getText()
                                + "','" + add_email.getText()
                                + "','" + type_personnel.getValue()
                                + "','" + AccueilController.login
                                + "');");

                        AfficherPersonnel(event);

                    } catch (IOException | SQLException e) {
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
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();
            clear();
        }

    }

    public void clear() throws IOException {
        add_nom.clear();
        add_prenom.clear();
        add_numero_tel.clear();
        add_email.clear();
        genre.setValue(null);
        type_personnel.setValue(null);
        adresse_personnel.setText(null);
        add_date.setValue(LocalDate.of(2001, Month.JANUARY, 1));

    }

    public boolean vide() {
        if (add_nom.getText().isEmpty() || add_prenom.getText().isEmpty() || add_numero_tel.getText().isEmpty() || add_email.getText().isEmpty() || genre.getValue() == null || type_personnel.getValue() == null || adresse_personnel.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }
    }

    //////////////////////////////////////////////// Modifier un personnel 
    public void ModifierPersonnel(ActionEvent event) throws IOException, SQLException {
        ap_modifier.toFront();
        btnmodifier.setVisible(true);
        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<Personnel> personnel = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `id_personnel`,`nom_pers`, `pren_pers`, `dateNaissPers`, `genre_pers`, `adresse_pers`, `num_tel_pers`, `email_pers`, `type_pers`, `username_ger` FROM `personnel`;");

            while (myRs.next()) {
                Personnel p = new Personnel(myRs.getInt("id_personnel"), myRs.getString("nom_pers"), myRs.getString("pren_pers"), myRs.getString("genre_pers"), myRs.getString("adresse_pers"),
                        myRs.getString("num_tel_pers"), myRs.getString("email_pers"), myRs.getString("type_pers"), myRs.getDate("dateNaissPers"), myRs.getString("username_ger"));
                personnel.add(p);

            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel);
            table4.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void PaneModifier(ActionEvent event) throws IOException, SQLException {
        if (table4.getSelectionModel().isSelected(table4.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table4.getSelectionModel().getSelectedItem();
            id_perso = personnel.getId();
            ap_confirme_modifay.toFront();

            try {

                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                ResultSet myRs = myStmt.executeQuery("select * from personnel WHERE `id_personnel`= " + id_perso + ";");
                if (myRs.next()) {
                    add_nom1.setText(myRs.getString("nom_pers"));
                    add_prenom1.setText(myRs.getString("pren_pers"));
                    add_numero_tel1.setText(Integer.toString(myRs.getInt("num_tel_pers")));
                    add_email1.setText(myRs.getString("email_pers"));
                    genre1.setValue(myRs.getString("genre_pers"));
                    type_personnel1.setValue(myRs.getString("type_pers"));
                    adresse_personnel1.setText(myRs.getString("adresse_pers"));
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(format.format(myRs.getDate("dateNaissPers")));
                    add_date1.setValue(date);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }

    }

    public void ConfirmerModification(ActionEvent event) throws IOException, SQLException {

        if (!videPersModifay()) {
            if (TestEmail1()) {

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

                        myStmt.execute("UPDATE personnel SET nom_pers='" + add_nom1.getText() + "', pren_pers='" + add_prenom1.getText() + "', adresse_pers='" + adresse_personnel1.getText()
                                + "' , num_tel_pers =" + add_numero_tel1.getText()
                                + " , email_pers ='" + add_email1.getText()
                                + "' , type_pers ='" + type_personnel1.getValue()
                                + "' , genre_pers ='" + genre1.getValue()
                                + "' , dateNaissPers ='" + add_date1.getValue()
                                + "' where `id_personnel`= " + id_perso + ";");

                        ModifierPersonnel(event);

                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                } catch (IOException | SQLException e) {

                }
            } else {

            }

        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();
        }

    }

    public boolean videPersModifay() {
        if (add_nom1.getText().isEmpty() || add_prenom1.getText().isEmpty() || add_numero_tel1.getText().isEmpty() || add_email1.getText().isEmpty() || genre1.getValue() == null || type_personnel1.getValue() == null || adresse_personnel1.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }
    }

    //////////////////////////////////////////////////////////////////////     
    public void AfficherPersonnel(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<Personnel> personnel = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs = myStmt.executeQuery("SELECT `id_personnel`,`nom_pers`, `pren_pers`, `dateNaissPers`, `genre_pers`, `adresse_pers`, `num_tel_pers`, `email_pers`, `type_pers`, `username_ger` FROM `personnel`;");

            while (myRs.next()) {
                Personnel p = new Personnel(myRs.getInt("id_personnel"), myRs.getString("nom_pers"), myRs.getString("pren_pers"), myRs.getString("genre_pers"), myRs.getString("adresse_pers"),
                        myRs.getString("num_tel_pers"), myRs.getString("email_pers"), myRs.getString("type_pers"), myRs.getDate("dateNaissPers"), myRs.getString("username_ger"));
                personnel.add(p);

            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel);
            table.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void av2() {
        if (type_service.getValue().equals("Matin")) {
            tf11.setText("9:30");
            tf22.setText("11:59");
        } else if (type_service.getValue().equals("Apres-Midi")) {
            tf11.setText("12:00");
            tf22.setText("17:59");
        } else {
            tf11.setText("18:00");
            tf22.setText("23:59");
        }
    }

    public void AfficherlistAffectation(ActionEvent event) throws SQLException, ParseException {

        DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        affiche_affectation.toFront();
        Connection cnx = ApplicationDuGerant.connection;
        ArrayList<Personnel> personnel2 = new ArrayList<>();
        ArrayList<Personnel> personnel3 = new ArrayList<>();
        java.sql.Statement myStmt = cnx.createStatement();

        try {

            ResultSet myRs2 = myStmt.executeQuery("SELECT `date_service`,personnel.`id_personnel`,nom_pers,`id_table` FROM personnel_service,personnel,`tableconnectee` WHERE personnel.`id_personnel`=personnel_service.`id_personnel` AND `tableconnectee`.`id_serveur`=personnel_service.`id_personnel`;");
            boolean b = true;
            if (myRs2.next()) {
                while (1 != 0) {
                    String id_table = "";
                    int id_serveur = myRs2.getInt("id_personnel");
                    Personnel p2 = new Personnel(myRs2.getString("nom_pers"), myRs2.getString(1), myRs2.getInt("id_personnel"), id_table);
                    while (id_serveur == myRs2.getInt("id_personnel")) {
                        id_table = id_table.concat("  " + Integer.toString(myRs2.getInt("id_table")));
                        b = myRs2.next();
                        if (b == false) {
                            break;
                        }
                    }
                    p2.setIdTable(id_table);

                    personnel2.add(p2);
                    if (b == false) {
                        break;
                    }

                }
            }

            // Caster la list a une list Observable
            ObservableList<Personnel> listObservable = FXCollections.observableArrayList(personnel2);
            table3.setItems(listObservable);
            table3.refresh();

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void PaneModifierPlanning(ActionEvent event) throws IOException, SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (table3.getSelectionModel().isSelected(table3.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table3.getSelectionModel().getSelectedItem();
            id_perso = personnel.getId();
            planning1.toFront();
            try {

                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                ResultSet myRs = myStmt.executeQuery("SELECT `date_service`,`id_perso_service`, `type_service`,`id_personnel` FROM `personnel_service`,`service` WHERE `service`.`id_service`=`personnel_service`.`id_service` and `id_personnel`=" + id_perso + ";");
                if (myRs.next()) {

                    LocalDate date_ouverture = LocalDate.parse((myRs.getString("date_service")), formatter);
                    dp2.setValue(date_ouverture);
                    type_service.setValue(myRs.getString("type_service"));
                    av2();
                    sauv_id = myRs.getInt("id_perso_service");
                    sauv_id_serveur = myRs.getInt("id_personnel");

                }
                System.out.println(sauv_id_serveur);
                ResultSet myRs1 = myStmt.executeQuery("SELECT `id_table` FROM `tableconnectee` WHERE `id_serveur` is NULL;");
                ArrayListeTable1 = new ArrayList<>();
                ArrayListeTable2 = new ArrayList<>();
                while (myRs1.next()) {
                    CheckBox ch = new CheckBox("" + myRs1.getInt(1));
                    ArrayListeTable1.add(new TableSelection("Table" + " " + myRs1.getInt(1), ch));
                }
                ResultSet myRs2 = myStmt.executeQuery("SELECT `id_table` FROM `tableconnectee` WHERE `id_serveur`=" + id_perso + ";");
                while (myRs2.next()) {
                    CheckBox ch = new CheckBox("" + myRs2.getInt(1));

                    ArrayListeTable1.add(new TableSelection("Table" + " " + myRs2.getInt(1), ch));
                    ArrayListeTable1.get(ArrayListeTable1.size() - 1).getCheckbox().setSelected(true);

                }

                listeTable1 = FXCollections.observableArrayList(ArrayListeTable1);
                tableRestaurant1.setItems(listeTable1);
                tableRestaurant1.getSortOrder().add(nomTable);

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }

    }

    public void ModifierPlanning(ActionEvent event) throws IOException, SQLException {

        int bol = 0;
        if (!videAffect2()) {
            if (!tableRestaurant1.getItems().isEmpty()) {
                for (int j = 0; j < tableRestaurant1.getItems().size(); j++) {
                    if (tableRestaurant1.getItems().get(j).getCheckbox().isSelected()) {
                        bol = 1;
                    }
                }
                if (bol == 1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Confirmation de la modification");
                    alert.setContentText("Voulez vraiment confirmer la modification ?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {

                        try {
                            int temp;
                            if (type_service.getValue().equals("Matin")) {
                                temp = 8;
                            } else if (type_service.getValue().equals("Apres-Midi")) {
                                temp = 9;
                            } else {
                                temp = 11;
                            }

                            Connection cnx = ApplicationDuGerant.connection;
                            //execute sql query
                            Statement myStmt = cnx.createStatement();

                            myStmt.execute("UPDATE `personnel_service` SET `date_service`='" + dp2.getValue() + "',`id_service`=" + temp + "  where `id_perso_service` = " + sauv_id + ";");

                            Statement myStmt2 = cnx.createStatement();

                            myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`=" + sauv_id_serveur + ";");

                            for (int i = 0; i < tableRestaurant1.getItems().size(); i++) {
                                if (tableRestaurant1.getItems().get(i).getCheckbox().isSelected()) {
                                    myStmt.execute("UPDATE `tableconnectee` SET `id_serveur`=" + sauv_id_serveur + " WHERE `id_table`="
                                            + tableRestaurant1.getItems().get(i).getNum() + ";");

                                }
                            }
                            AfficherlistAffectation(event);

                        } catch (SQLException | ParseException e) {

                        }

                    } else {
                        dp2.setValue(LocalDate.of(2019, Month.JULY, 14));
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    // Header Text: null
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez choisir au moins une table!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Aucune table disponible!");
                alert.show();
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.show();
            dp2.setValue(LocalDate.of(2019, Month.JULY, 14));

        }

    }


    public void deletePersonnelService() throws SQLException {
        if (table3.getSelectionModel().isSelected(table3.getSelectionModel().getSelectedIndex())) {
            Personnel personnel;
            personnel = table3.getSelectionModel().getSelectedItem();
            String nom = personnel.getNom();
            int Id = personnel.getId();
            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();
            java.sql.Statement myStmt2 = cnx.createStatement();

            try {
                myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`=" + Id + ";");
                myStmt.execute("delete from personnel_service where id_personnel=" + Id + ";");

            } catch (SQLException e) {

            }
            table3.getItems().remove(personnel);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.show();
        }
    }

    public boolean TestEmail() {
        int s = 0;
        String str = add_email.getText();
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

    public boolean TestEmail1() {
        int s = 0;
        String str = add_email1.getText();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ap_home.toFront();
        JfxTextFieldCharLimit.addTextLimiter(add_nom, 20);
        JfxTextFieldCharLimit.addTextLimiter(add_prenom, 20);
        JfxTextFieldCharLimit.addTextLimiter(add_numero_tel, 10);
        JfxTextFieldCharLimit.addTextLimiter(add_email, 20);
        JfxTextFieldCharLimit.addTextLimiter(add_nom1, 20);
        JfxTextFieldCharLimit.addTextLimiter(add_prenom1, 20);
        JfxTextFieldCharLimit.addTextLimiter(add_numero_tel1, 10);
        JfxTextFieldCharLimit.addTextLimiter(add_email1, 20);

        add_numero_tel.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                add_numero_tel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        add_numero_tel1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                add_numero_tel1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        add_nom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                add_nom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        add_nom1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                add_nom1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        add_prenom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                add_prenom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        add_prenom1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                add_prenom1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        genre.getItems().addAll("Homme", "Femme");
        cb1.getItems().addAll("Matin", "Apres-Midi", "Soir");
        type_service.getItems().addAll("Matin", "Apres-Midi", "Soir");
        type_personnel.getItems().addAll("Cuisinier", "Serveur");
        type_personnel.setValue("Cuisinier");
        genre.setValue("Homme");
        cb1.setValue("Matin");

        //
        genre1.getItems().addAll("Homme", "Femme");
        type_personnel1.getItems().addAll("Cuisinier", "Serveur");
        type_personnel1.setValue("Cuisinier");
        genre1.setValue("Homme");

        ///
        LocalDate minDate = LocalDate.of(1940, 1, 1);
        LocalDate maxDate = LocalDate.of(2001, 1, 1);

        LocalDate minDate1 = LocalDate.of(2019, 6, 14);
        LocalDate maxDate1 = LocalDate.of(2020, 1, 1);

        add_date.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
            }
        });
        add_date1.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
            }
        });

        dp1.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                setDisable(item.isAfter(maxDate1) || item.isBefore(minDate1));
            }
        });

        add_date.setValue(LocalDate.of(2001, Month.JANUARY, 1));
        add_date1.setValue(LocalDate.of(2001, Month.JANUARY, 1));
        dp1.setValue(LocalDate.of(2019, Month.JULY, 14));

        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnDateNaissance.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        columnNumeroTel.setCellValueFactory(new PropertyValueFactory<>("numero_tel"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnType_personnel.setCellValueFactory(new PropertyValueFactory<>("type_personnel"));
        columnUserGerant.setCellValueFactory(new PropertyValueFactory<>("username_ger"));
        ///////
        columnNom4.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom4.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnDateNaissance4.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnGenre4.setCellValueFactory(new PropertyValueFactory<>("genre"));
        columnAdresse4.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        columnNumeroTel4.setCellValueFactory(new PropertyValueFactory<>("numero_tel"));
        columnEmail4.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnType_personnel4.setCellValueFactory(new PropertyValueFactory<>("type_personnel"));
        columnUserGerant4.setCellValueFactory(new PropertyValueFactory<>("username_ger"));
        //////
        idPersonnel.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomPersonnel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateService.setCellValueFactory(new PropertyValueFactory<>("dates"));
        idTable.setCellValueFactory(new PropertyValueFactory<>("idTable"));

        tf1.setStyle("-fx-text-inner-color: WHITE");
        tf2.setStyle("-fx-text-inner-color: WHITE");

        /////////////////////
        nomTable.setCellValueFactory(new PropertyValueFactory<>("nom"));
        selectTable.setCellValueFactory(new PropertyValueFactory<>("checkbox"));

        nomTable1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        selectTable1.setCellValueFactory(new PropertyValueFactory<>("checkbox"));

        ArrayListeTable = new ArrayList<>();
        listeTable = FXCollections.observableArrayList(ArrayListeTable);
        tableRestaurant.setItems(listeTable);
        //////////                  

    }

}
