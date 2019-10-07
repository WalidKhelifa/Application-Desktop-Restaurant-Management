/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class GestionDuMenuController implements Initializable {

    @FXML
    JFXTextField tf1;
    @FXML
    JFXTextField tf3;
    @FXML
    JFXTextField tf11;
    @FXML
    JFXTextField tf44;
    @FXML
    JFXTextField tf111;
    @FXML
    JFXTextField tf1111;
    @FXML
    JFXTextField tfmp;
    @FXML
    JFXTextField tfmc;
    @FXML
    JFXTextField tfp2;
    @FXML
    JFXTextField tfp3;
    @FXML
    JFXTextField platDetail1;
    @FXML
    JFXTextField platDetail2;
    @FXML
    JFXTextField platDetail3;
    @FXML
    JFXTextField add_ing;
    @FXML
    JFXTextField prixPetit;
    @FXML
    JFXTextField caloriePetit;
    @FXML
    JFXTextField prixGrand;
    @FXML
    JFXTextField calorieGrand;
    @FXML
    JFXTextField prixPetitModifay;
    @FXML
    JFXTextField caloriePetitModifay;
    @FXML
    JFXTextField prixGrandModifay;
    @FXML
    JFXTextField calorieGrandModifay;
    @FXML
    JFXTextField prixPetitAdd;
    @FXML
    JFXTextField caloriePetitAdd;
    @FXML
    JFXTextField prixGrandAdd;
    @FXML
    JFXTextField calorieGrandAdd;

    @FXML
    Pane ap_afficher;
    @FXML
    AnchorPane ap_ajouter;
    @FXML
    AnchorPane ap_ajout_portion;
    @FXML
    AnchorPane ap_ajout_ingrediant;
    @FXML
    AnchorPane ap_modifier;
    @FXML
    AnchorPane ap_modifier2;
    @FXML
    AnchorPane ap_modifier3;
    @FXML
    AnchorPane ap_supprimer;
    @FXML
    AnchorPane ap_home;
    @FXML
    AnchorPane detialPlat;
    @FXML
    AnchorPane detialPlat2;
    @FXML
    AnchorPane ajout_ingrediant;
    @FXML
    AnchorPane ap_afficher_modifay;
    @FXML
    AnchorPane ap_photo;
    @FXML
    JFXComboBox<String> tf2;
    @FXML
    JFXComboBox<String> tf22;
    @FXML
    JFXComboBox<String> tf33;
    @FXML
    ComboBox<String> cbAllergene;
    @FXML
    JFXComboBox<String> tf4;
    @FXML
    Button btnconfirme;
    @FXML
    JFXButton btndelete;
    

    @FXML
    TableColumn columnId, columnId1, columnId2;
    @FXML
    TableColumn columnDesignation, columnDesignation1, columnDesignation2;
    @FXML
    TableColumn columnDateAjout, columnDateAjout1, columnDateAjout2;
    @FXML
    TableColumn columnCategorie, columnCategorie1, columnCategorie2;
    @FXML
    TableColumn columnModeCuisson1, columnModeCuisson2;
    @FXML
    TableColumn columnTempsCuisson1, columnTempsCuisson2;
    @FXML
    TableColumn columnUsername_ger, columnUsername_ger1, columnUsername_ger2;
    @FXML
    TableColumn columnModeCuisson;
    @FXML
    TableColumn columnTempsCuisson;
    @FXML
    TableView<plat> table, table1, table2;
    @FXML
    TableView<ingrediant> table_ingrediant;
    @FXML
    TableView<ingrediant> table_ingrediant1;
    @FXML
    TableView<ingrediant> table_ingrediant_selected;
    @FXML
    TableView<ingrediant> table_ingrediant_modifay;
    @FXML
    TableView<ingrediant> table_ingrediant_affiche;
    @FXML
    TableView<ingrediant> table_ingrediant_all;
    @FXML
    TableColumn columnIdIngrediant, columnIdIngrediant1, columnIdIngrediantModifay, columnIdIngrediantAffich;
    @FXML
    TableColumn columnNomIngrediant, columnNomIngrediant1, columnNomIngrediantModifay, columnNomIngrediantAffich;
    @FXML
    TableColumn columnIdIngrediant2;
    @FXML
    TableColumn columnNomIngrediant2;
    @FXML
    TableColumn columnIdIngrediantModifay1;
    @FXML
    TableColumn columnNomIngrediantModifay1;
    
    String designation;
    int id = 0;
    int id_plat = 0;
    int sauv_pos;
    int id_plat_modifay = 0;
    int sauv_pos2;
    String sauv_id_alergene;
    int sauv_id_ingrediant;

    ArrayList<ingrediant> listenvoi;
    ArrayList<ingrediant> listenvoi2;
    ArrayList<ingrediant> listeIngrediant;
    ArrayList<ingrediant> listeIngrediantPlat;
    //////////////////
    @FXML
    JFXTextField tfselected;
    @FXML
    JFXCheckBox m1;
    @FXML
    JFXCheckBox m2;
    @FXML
    JFXCheckBox m3;
    @FXML
    RadioButton rb;

    public void AfficherMenu(ActionEvent event) throws IOException, SQLException {
        ap_home.toFront();
        ap_afficher.toFront();
        table.setVisible(true);
        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();

            ResultSet myRs = myStmt.executeQuery("select id_plat,designation,dateAjout,categorie,modeCuisson,tempsCuisson,username_ger from plat;");

            ArrayList<plat> menu = new ArrayList<>();

            while (myRs.next()) {
                plat p = new plat(myRs.getInt("id_plat"), myRs.getString("designation"), myRs.getDate("dateAjout"), myRs.getString("categorie"),
                        myRs.getString("modeCuisson"), myRs.getString("tempsCuisson"), myRs.getString("username_ger"));
                menu.add(p);
            }

            // Caster la list a une list Observable
            ObservableList<plat> listObservable = FXCollections.observableArrayList(menu);
            table.setItems(listObservable);
            table1.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficherPlatDetail() throws SQLException {
        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            prixPetit.setText("");
            prixGrand.setText("");
            caloriePetit.setText("");
            calorieGrand.setText("");
            ap_home.toFront();
            detialPlat.toFront();
            plat p;
            p = table.getSelectionModel().getSelectedItem();
            int id_plat_details = p.getId_plat();
            try {
                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs2 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_details + " and portionNom ='medium';");

                if (myRs2.next()) {
                    platDetail1.setText(p.getDesignation());
                    platDetail2.setText(Float.toString(myRs2.getFloat("portionPrixUni")));
                    platDetail3.setText(Integer.toString(myRs2.getInt("portionCalories")));

                }

                ResultSet myRs1 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_details + " and portionNom ='small';");
                if (myRs1.next()) {

                    prixPetit.setText(Float.toString(myRs1.getFloat("portionPrixUni")));
                    caloriePetit.setText(Integer.toString(myRs1.getInt("portionCalories")));

                }

                ResultSet myRs3 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_details + " and portionNom ='large';");
                if (myRs3.next()) {

                    prixGrand.setText(Float.toString(myRs3.getFloat("portionPrixUni")));
                    calorieGrand.setText(Integer.toString(myRs3.getInt("portionCalories")));

                }

            } catch (SQLException e) {
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }

    }

    public void afficherPlatDetail2() throws SQLException {
        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            plat p;
            p = table.getSelectionModel().getSelectedItem();
            int id_plat_des = p.getId_plat();
            ap_home.toFront();
            detialPlat2.toFront();
            try {
                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs2 = myStmt.executeQuery("SELECT  plat_ingredients.id_ingredient,nom_ing FROM plat_ingredients,ingredient WHERE  id_plat=" + id_plat_des + " and plat_ingredients.id_ingredient=ingredient.id_ingredient ");

                ArrayList<ingrediant> ingr = new ArrayList<>();

                while (myRs2.next()) {
                    ingrediant in = new ingrediant(myRs2.getInt("id_ingredient"), myRs2.getString("nom_ing"));
                    ingr.add(in);
                }

                // Caster la list a une list Observable
                ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(ingr);
                table_ingrediant_affiche.setItems(listObservable);

            } catch (SQLException e) {

            }

        }
    }

    public void PaneAjouter(ActionEvent event) throws IOException, SQLException {
        ap_home.toFront();
        ap_ajouter.toFront();
        tf4.setValue("Aucun");
        clear();
        clear2();
        clear3();
        clearPortion();
        table_ingrediant_selected.getItems().clear();
    }

    public void AnnulerAjoutPlat(ActionEvent event) throws IOException, SQLException {
        clear();
    }

    public void AnnulerAjoutPortion(ActionEvent event) throws IOException, SQLException {

        ap_home.toFront();
        ap_ajouter.toFront();
        clearPortion();
    }

    public void AnnulerAjoutIngrediant(ActionEvent event) throws IOException, SQLException {
        ap_home.toFront();
        ap_ajout_portion.toFront();
    }

    public void PaneAjoutPortion(ActionEvent event) throws IOException, SQLException {
        if (!vide()) {
            ap_home.toFront();
            ap_ajout_portion.toFront();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void PaneAjoutIngrediant(ActionEvent event) throws IOException, SQLException {
        if (!videPortion()) {
            ap_home.toFront();
            ap_ajout_ingrediant.toFront();
            cbAllergene.setValue("Aucun");
            try {

                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs = myStmt.executeQuery("SELECT `id_ingredient`, `nom_ing` FROM `ingredient`;");

                ArrayList<ingrediant> ing = new ArrayList<>();

                while (myRs.next()) {
                    ingrediant i = new ingrediant(myRs.getInt("id_ingredient"), myRs.getString("nom_ing"));
                    ing.add(i);
                }

                // Caster la list a une list Observable
                ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(ing);
                table_ingrediant.setItems(listObservable);
                table_ingrediant1.setItems(listObservable);

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void AjoutIngrediantListPlats() throws SQLException {
        if (table_ingrediant.getSelectionModel().isSelected(table_ingrediant.getSelectionModel().getSelectedIndex())) {
            btnconfirme.setVisible(true);
            table_ingrediant_selected.setVisible(true);
            btndelete.setVisible(true);
            ingrediant ing;
            ArrayList<ingrediant> listing = new ArrayList<>();
            ing = table_ingrediant.getSelectionModel().getSelectedItem();
            listing.add(ing);
            listenvoi.add(ing);
            ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(listing);
            table_ingrediant_selected.getItems().add(ing);
            sauv_pos = table_ingrediant.getSelectionModel().getSelectedIndex();
            table_ingrediant.getItems().remove(ing);
            table_ingrediant_selected.getSortOrder().add(columnIdIngrediant1);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }

    }

    public void deleteIngrediantListPlats() throws SQLException {
        if (table_ingrediant_selected.getSelectionModel().isSelected(table_ingrediant_selected.getSelectionModel().getSelectedIndex())) {
            ingrediant ing;
            ArrayList<ingrediant> listing = new ArrayList<>();
            ing = table_ingrediant_selected.getSelectionModel().getSelectedItem();

            table_ingrediant_selected.getItems().remove(ing);
            listenvoi.remove(ing);
            table_ingrediant.getItems().add(ing);
            table_ingrediant.getSortOrder().add(columnIdIngrediant);

            //TableView<ingrediant>table_ingrediant = (ArrayList<ingrediant>)table_ingrediant;
            //Collections.sort(table_ingrediant,ComparatorId);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }
    }

    public void confirmerAjoutPlat(ActionEvent event) throws IOException, SQLException {

        if (!vide() && !videPortion()) {
            if (table_ingrediant_selected.getItems().size() > 0) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de l'ajout");
                alert.setContentText("Voulez vraiment confirmer l'ajout ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        Connection cnx = ApplicationDuGerant.connection;
                        //execute sql query
                        Statement myStmt = cnx.createStatement();
                        Date d = new Date();
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");

                        myStmt.execute("insert into plat(designation,dateAjout,categorie,tempsCuisson,modeCuisson,username_ger,photo_plat) values('"
                                + tf1.getText()
                                + "','" + dformat.format(d)
                                + "','" + tf2.getValue()
                                + "','" + tf3.getText()
                                + "','" + tf4.getValue()
                                + "','" + AccueilController.login
                                + "','" + "http://192.168.43.114:8888/Pictures/Restaurant/plats/" + tfselected.getText()
                                + "');");
                        ///////////////////////////////////////////////////

                        Statement myStmt2 = cnx.createStatement();
                        ResultSet myRs = myStmt.executeQuery("select id_plat from plat where designation='" + tf1.getText() + "' and categorie='" + tf2.getValue() + "' and username_ger ='" + AccueilController.login + "';");
                        if (myRs.next()) {
                            id_plat = myRs.getInt("id_plat");
                        }
                        //////////////////////////////////////////////////

                        Statement myStmt3 = cnx.createStatement();
                        myStmt3.execute("INSERT INTO `portion`(`portionNom`, `portionPrixUni`, `portionCalories`, `id_plat`) VALUES ('"
                                + "medium"
                                + "','" + tfp2.getText()
                                + "','" + tfp3.getText()
                                + "'," + id_plat
                                + ");");

                        Statement myStmt5 = cnx.createStatement();
                        myStmt5.execute("INSERT INTO `portion`(`portionNom`, `portionPrixUni`, `portionCalories`, `id_plat`) VALUES ('"
                                + "small"
                                + "','" + prixPetitAdd.getText()
                                + "','" + caloriePetitAdd.getText()
                                + "'," + id_plat
                                + ");");

                        Statement myStmt6 = cnx.createStatement();
                        myStmt6.execute("INSERT INTO `portion`(`portionNom`, `portionPrixUni`, `portionCalories`, `id_plat`) VALUES ('"
                                + "large"
                                + "','" + prixGrandAdd.getText()
                                + "','" + calorieGrandAdd.getText()
                                + "'," + id_plat
                                + ");");

                        Statement myStmt4 = cnx.createStatement();
                        for (int i = 0; i < listenvoi.size(); i++) {
                            myStmt4.execute("INSERT INTO `plat_ingredients`(`id_plat`, `id_ingredient`) VALUES ("
                                    + id_plat
                                    + "," + listenvoi.get(i).getId_ingredient()
                                    + ");");
                        }

                    } catch (SQLException e) {
                    }
                    clear();
                    clear2();
                    clear3();
                    listenvoi.clear();
                    AfficherMenu(event);

                } else {

                }

                //clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur ");
                alert.setContentText("Veuillez remplir au moins un seul ingrediant!");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void PaneChercher(ActionEvent event) {
        ap_home.toFront();

    }

    public void Chercher() throws IOException, SQLException {
        if (table2.getSelectionModel().isSelected(table2.getSelectionModel().getSelectedIndex())) {
            plat pl;
            pl = table2.getSelectionModel().getSelectedItem();

            ap_modifier.toFront();

            try {
                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();
                ResultSet myRs = myStmt.executeQuery("select id_plat,categorie,modeCuisson,tempsCuisson from plat where id_plat='" + table2.getSelectionModel().getSelectedItem().getId_plat() + "';");
                if (myRs.next()) {
                    tf11.setText(table2.getSelectionModel().getSelectedItem().getDesignation());
                    tf22.setValue(myRs.getString("categorie"));
                    tf33.setValue(myRs.getString("modeCuisson"));
                    tf44.setText(myRs.getString("tempsCuisson"));
                    id_plat_modifay = myRs.getInt("id_plat");
                    designation = tf11.getText();

                }

            } catch (SQLException e) {
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }

    }

    private static boolean isEmpty(JFXTextField textField) {
        return textField.getText().equals("");
    }

    public void SupprimerPlat(ActionEvent event) throws IOException, SQLException {
        ap_home.toFront();
        AfficherMenu(event);
        ap_supprimer.toFront();
        //table.setVisible(false);
    }

    public void delete() throws SQLException {
        if (table.getSelectionModel().isSelected(table.getSelectionModel().getSelectedIndex())) {
            plat p;
            p = table.getSelectionModel().getSelectedItem();
            int id_plat_delete = p.getId_plat();

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();

            try {

                myStmt.execute("DELETE FROM `plat` WHERE id_plat=" + id_plat_delete + ";");

            } catch (SQLException e) {

            }
            table1.getItems().remove(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }
    }

    public void Chercher2() throws IOException, SQLException {
        ap_modifier2.toFront();
        ap_modifier.toBack();
        ap_modifier3.toBack();
        tfmp.setText("");
        tfmc.setText("");
        prixPetitModifay.setText("");
        prixGrandModifay.setText("");
        caloriePetitModifay.setText("");
        calorieGrandModifay.setText("");

        
            try {
                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs2 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_modifay + " and portionNom ='medium';");

                if (myRs2.next()) {
                    tf111.setText(tf11.getText());
                    tfmp.setText(Float.toString(myRs2.getFloat("portionPrixUni")));
                    tfmc.setText(Integer.toString(myRs2.getInt("portionCalories")));

                }

                ResultSet myRs1 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_modifay + " and portionNom ='small';");

                if (myRs1.next()) {

                    prixPetitModifay.setText(Float.toString(myRs1.getFloat("portionPrixUni")));
                    caloriePetitModifay.setText(Integer.toString(myRs1.getInt("portionCalories")));

                }

                ResultSet myRs3 = myStmt.executeQuery("SELECT `portionPrixUni`, `portionCalories` FROM `portion` WHERE id_plat=" + id_plat_modifay + " and portionNom ='large';");

                if (myRs3.next()) {

                    prixGrandModifay.setText(Float.toString(myRs3.getFloat("portionPrixUni")));
                    calorieGrandModifay.setText(Integer.toString(myRs3.getInt("portionCalories")));

                }

            } catch (SQLException e) {
            }
        

    }

    public void ConfirmerModification2() throws IOException, SQLException {
        if (!vide3()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de la modification");
            alert.setContentText("Voulez vraiment confirmer la modification ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    Connection cnx = ApplicationDuGerant.connection;
                    //execute sql query
                    Statement myStmt = cnx.createStatement();
                    myStmt.execute("UPDATE `portion` SET `portionPrixUni`='" + tfmp.getText() + "' , portionCalories='" + tfmc.getText()
                            + "' where id_plat = " + id + " and portionNom='medium';");

                } catch (SQLException e) {

                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }

            clear3();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void PaneMofifierIngrediant(ActionEvent event) throws IOException, SQLException {
        ap_modifier3.toFront();
        ap_modifier.toBack();
        ap_modifier2.toBack();
        tf1111.setText("");

    }

    public void Chercher3() throws IOException, SQLException {

        

            ap_modifier3.toFront();
            try {
                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs2 = myStmt.executeQuery("SELECT  plat_ingredients.id_ingredient,nom_ing FROM plat_ingredients,ingredient WHERE  id_plat=" + id_plat_modifay + " and plat_ingredients.id_ingredient=ingredient.id_ingredient ");

                listeIngrediantPlat = new ArrayList<>();

                while (myRs2.next()) {
                    ingrediant in = new ingrediant(myRs2.getInt("id_ingredient"), myRs2.getString("nom_ing"));
                    listeIngrediantPlat.add(in);
                }

                ResultSet myRs3 = myStmt.executeQuery("SELECT `id_ingredient`, `nom_ing` FROM `ingredient` where `ingredient`.`id_ingredient` NOT IN (SELECT `id_ingredient` FROM `plat_ingredients` WHERE `id_plat`=" + id_plat_modifay + " and `plat_ingredients`.`id_ingredient`=`ingredient`.`id_ingredient`);");

                listeIngrediant = new ArrayList<>();

                while (myRs3.next()) {
                    ingrediant i = new ingrediant(myRs3.getInt("id_ingredient"), myRs3.getString("nom_ing"));
                    listeIngrediant.add(i);
                }

                // Caster la list a une list Observable
                ObservableList<ingrediant> listObservable2 = FXCollections.observableArrayList(listeIngrediant);

                // Caster la list a une list Observable
                ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(listeIngrediantPlat);
                table_ingrediant_all.setItems(listObservable2);
                table_ingrediant_modifay.setItems(listObservable);

            } catch (SQLException e) {

            }
        

    }

    public void clear() throws IOException {
        tf1.clear();
        tf2.setValue(null);
        tf3.clear();
        tf4.setValue("Aucun");
        tfselected.clear();

    }

    public void clear2() throws IOException {
        tf11.clear();
        tf22.setValue(null);
        tf33.setValue("Aucun");
        tf44.clear();

    }

    public void clear3() throws IOException {
        tfmc.clear();
        tfmp.clear();
        tf111.clear();

    }

    public void clearPortion() throws IOException {

        tfp2.clear();
        tfp3.clear();
        prixPetitAdd.clear();
        prixGrandAdd.clear();
        caloriePetitAdd.clear();
        calorieGrandAdd.clear();

    }

    public boolean vide() {
        if (tf1.getText().isEmpty() || tf2.getValue() == null || tf3.getText().isEmpty() || tf4.getValue() == null || tfselected.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }
    }

    public boolean vide2() {
        if (tf11.getText().isEmpty() || tf22.getValue() == null || tf33.getValue() == null || tf44.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }

    }

    public boolean vide3() {
        if (tfmc.getText().isEmpty() || tfmp.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }

    }

    public boolean videPortion() {
        if (tfp3.getText().isEmpty() || tfp2.getText().isEmpty() || prixPetitAdd.getText().isEmpty() || prixGrandAdd.getText().isEmpty() || caloriePetitAdd.getText().isEmpty() || calorieGrandAdd.getText().isEmpty()) {
            return true;

        } else {
            return false;
        }

    }

    

    public static Comparator<ingrediant> ComparatorId = (ingrediant e1, ingrediant e2) -> (int) (e1.getId_ingredient() - e2.getId_ingredient());


    public void PaneRetourAffiche(ActionEvent event) throws IOException, SQLException {
        ap_afficher.toFront();
    }

    public void PaneRetourModifayDetail2(ActionEvent event) throws IOException, SQLException {
        ap_modifier2.toFront();
    }

    public void PaneRetourModifayDetail1(ActionEvent event) throws IOException, SQLException {
        ap_modifier.toFront();
    }

    public void PaneRetourDeatil(ActionEvent event) throws IOException, SQLException {
        detialPlat.toFront();
    }

    public void PaneBack(ActionEvent event) throws IOException, SQLException {
        ap_home.toFront();

    }

    public void PaneAjoutIngrediants(ActionEvent event) throws IOException, SQLException {
        ajout_ingrediant.toFront();
        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT `id_ingredient`, `nom_ing` FROM `ingredient`;");

            ArrayList<ingrediant> ing = new ArrayList<>();

            while (myRs.next()) {
                ingrediant i = new ingrediant(myRs.getInt("id_ingredient"), myRs.getString("nom_ing"));
                ing.add(i);
            }

            // Caster la list a une list Observable
            ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(ing);

            table_ingrediant1.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AfficherMenuModifay(ActionEvent event) throws IOException, SQLException {
        ap_afficher_modifay.toFront();

        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();

            ResultSet myRs = myStmt.executeQuery("select id_plat,designation,dateAjout,categorie,modeCuisson,tempsCuisson,username_ger from plat;");

            ArrayList<plat> menu = new ArrayList<>();

            while (myRs.next()) {
                plat p = new plat(myRs.getInt("id_plat"), myRs.getString("designation"), myRs.getDate("dateAjout"), myRs.getString("categorie"),
                        myRs.getString("modeCuisson"), myRs.getString("tempsCuisson"), myRs.getString("username_ger"));
                menu.add(p);
            }

            // Caster la list a une list Observable
            ObservableList<plat> listObservable = FXCollections.observableArrayList(menu);
            table2.setItems(listObservable);

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void PaneAjoutDesIngrediants(ActionEvent event) throws IOException, SQLException {
        ajout_ingrediant.toFront();
        if (add_ing.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de l'ajout");
            alert.setContentText("Voulez vraiment confirmer l'ajout ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                try {
                    Connection cnx = ApplicationDuGerant.connection;
                    //execute sql query
                    Statement myStmt = cnx.createStatement();

                    myStmt.execute("INSERT INTO `ingredient`(`nom_ing`, `id_allergie`) VALUES('"
                            + add_ing.getText()
                            + "'," + 1
                            + ");");

                    // Caster la list a une list Observable
                } catch (SQLException ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void PaneAffichIngrediant(ActionEvent event) throws IOException, SQLException {
        if (true) {
            ap_home.toFront();
            ap_ajout_ingrediant.toFront();
            try {

                Connection cnx = ApplicationDuGerant.connection;
                //execute sql query
                Statement myStmt = cnx.createStatement();

                ResultSet myRs = myStmt.executeQuery("SELECT `id_ingredient`, `nom_ing` FROM `ingredient`;");

                ArrayList<ingrediant> ing = new ArrayList<>();

                while (myRs.next()) {
                    ingrediant i = new ingrediant(myRs.getInt("id_ingredient"), myRs.getString("nom_ing"));
                    ing.add(i);
                }

                // Caster la list a une list Observable
                ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(ing);
                table_ingrediant.setItems(listObservable);
                table_ingrediant1.setItems(listObservable);

            } catch (SQLException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();
        }

    }

    public void AjoutIngrediantListPlatsMofifay() throws SQLException {
        if (table_ingrediant_all.getSelectionModel().isSelected(table_ingrediant_all.getSelectionModel().getSelectedIndex())) {

            ingrediant ing;
            ArrayList<ingrediant> listing = new ArrayList<>();
            ing = table_ingrediant_all.getSelectionModel().getSelectedItem();
            listing.add(ing);
            listeIngrediantPlat.add(ing);
            ObservableList<ingrediant> listObservable = FXCollections.observableArrayList(listing);
            table_ingrediant_modifay.getItems().add(ing);
            sauv_pos2 = table_ingrediant.getSelectionModel().getSelectedIndex();
            table_ingrediant_all.getItems().remove(ing);
            listeIngrediant.remove(ing);
            table_ingrediant_modifay.getSortOrder().add(columnIdIngrediantModifay);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }

    }

    public void modifyIngrediantlist() throws SQLException {
        if (table_ingrediant_modifay.getSelectionModel().isSelected(table_ingrediant_modifay.getSelectionModel().getSelectedIndex())) {
            ingrediant ip;
            ip = table_ingrediant_modifay.getSelectionModel().getSelectedItem();
            table_ingrediant_modifay.getItems().remove(ip);
            listeIngrediantPlat.remove(ip);
            table_ingrediant_all.getItems().add(ip);
            listeIngrediant.add(ip);
            table_ingrediant_all.getSortOrder().add(columnIdIngrediantModifay1);
            table_ingrediant_modifay.getSortOrder().add(columnIdIngrediantModifay);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez selectionner une ligne d'abord!");
            alert.showAndWait();
        }
    }

    public void ConfirmerModification(ActionEvent event) throws IOException, SQLException {

        if (!vide2() || !vide3() || !videPortion()) {
            if (listeIngrediantPlat.size() > 0) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de la modification");
                alert.setContentText("Voulez vraiment confirmer la modification ?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        Connection cnx = ApplicationDuGerant.connection;
                        //execute sql query
                        /////////////////////////////
                        System.out.println("UPDATE `portion` SET `portionPrixUni`='" + prixPetitModifay.getText() + "' , portionCalories='" + caloriePetitModifay.getText()
                                + "' where id_plat = " + id_plat_modifay + " and portionNom='small';");
                        System.out.println("UPDATE `portion` SET `portionPrixUni`='" + prixGrandModifay.getText() + "' , portionCalories='" + calorieGrandModifay.getText()
                                + "' where id_plat = " + id_plat_modifay + " and portionNom='large';");

                        Statement myStmt = cnx.createStatement();
                        myStmt.execute("update plat set designation='" + tf11.getText() + "' , categorie='" + tf22.getValue()
                                + "' , modeCuisson ='" + tf33.getValue()
                                + "', tempsCuisson ='" + tf44.getText() + "' where designation = '" + designation + "';");
                        /////////////////////////////
                        Statement myStmt2 = cnx.createStatement();
                        myStmt2.execute("UPDATE `portion` SET `portionPrixUni`='" + tfmp.getText() + "' , portionCalories='" + tfmc.getText()
                                + "' where id_plat = " + id_plat_modifay + " and portionNom='medium';");

                        //////////////////////////////
                        Statement myStmt5 = cnx.createStatement();
                        myStmt5.execute("UPDATE `portion` SET `portionPrixUni`='" + prixPetitModifay.getText() + "' , portionCalories='" + caloriePetitModifay.getText()
                                + "' where id_plat = " + id_plat_modifay + " and portionNom='small';");
                        ///////////////////////////////
                        Statement myStmt6 = cnx.createStatement();
                        myStmt6.execute("UPDATE `portion` SET `portionPrixUni`='" + prixGrandModifay.getText() + "' , portionCalories='" + calorieGrandModifay.getText()
                                + "' where id_plat = " + id_plat_modifay + " and portionNom='large';");
                        /////////////////////////////////
                        Statement myStmt4 = cnx.createStatement();
                        myStmt4.execute("DELETE FROM `plat_ingredients` WHERE id_plat=" + id_plat_modifay + ";");

                        /////////////////////
                        Statement myStmt3 = cnx.createStatement();
                        for (int i = 0; i < listeIngrediantPlat.size(); i++) {
                            myStmt3.execute("INSERT INTO `plat_ingredients`(`id_plat`, `id_ingredient`) VALUES ("
                                    + id_plat_modifay
                                    + "," + listeIngrediantPlat.get(i).getId_ingredient()
                                    + ");");
                        }
                        AfficherMenuModifay(event);

                    } catch (IOException | SQLException e) {

                    }
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur ");
                alert.setContentText("Veuillez remplir au moins un seul ingrediant!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();

        }

    }

    public void AddImge(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //imageSelected.setText(selectedFile.getName());
            tfselected.setText(selectedFile.getName());
            System.out.println("le nom :" + selectedFile.getName());
        } else {
            System.out.println("erreur de selection");
        }

    }

    public void AjouterNewIngrediant() throws SQLException {

        if (!add_ing.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation de l'ajout");
            alert.setContentText("Voulez vraiment confirmer l'ajout ?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                try {
                    Connection cnx = ApplicationDuGerant.connection;
                    Statement myStmt = cnx.createStatement();
                    Statement myStmt1 = cnx.createStatement();
                    Statement myStmt2 = cnx.createStatement();

                    if (cbAllergene.getValue().equals("Aucun")) {
                        myStmt.execute("INSERT INTO `ingredient`(`nom_ing`) VALUES ('"
                                + add_ing.getText()
                                + "');");
                    } else {
                        ResultSet myRs = myStmt.executeQuery("SELECT `id_allergie` FROM `allergies` WHERE `nom_allergie`='" + cbAllergene.getValue() + "';");

                        if (myRs.next()) {
                            sauv_id_alergene = myRs.getString("id_allergie");
                        }

                        myStmt.execute("INSERT INTO `ingredient`(`nom_ing`, `id_allergie`) VALUES ('"
                                + add_ing.getText()
                                + "','" + sauv_id_alergene
                                + "');");
                    }
                    ResultSet myRs2 = myStmt.executeQuery("SELECT `id_ingredient` FROM `ingredient` WHERE `nom_ing`='" + add_ing.getText() + "';");

                    if (myRs2.next()) {
                        sauv_id_ingrediant = myRs2.getInt("id_ingredient");
                    }
                    ///////// Maladie
                    if (m1.isSelected() == true && m2.isSelected() == false && m3.isSelected() == false) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 1
                                + ");");
                    }
                    if (m2.isSelected() == true && m1.isSelected() == false && m3.isSelected() == false) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 2
                                + ");");
                    }
                    if (m3.isSelected() == true && m1.isSelected() == false && m2.isSelected() == false) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 3
                                + ");");
                    }
                    if (m1.isSelected() == true && m2.isSelected() == true && m3.isSelected() == false) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 1
                                + ");");
                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 2
                                + ");");
                    }
                    if (m1.isSelected() == true && m2.isSelected() == false && m3.isSelected() == true) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 1
                                + ");");
                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 3
                                + ");");
                    }
                    if (m1.isSelected() == false && m2.isSelected() == true && m3.isSelected() == true) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 2
                                + ");");
                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 3
                                + ");");
                    }
                    if (m1.isSelected() == true && m2.isSelected() == true && m3.isSelected() == true) {

                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 1
                                + ");");
                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 2
                                + ");");
                        myStmt2.execute("INSERT INTO `maladie_ingredients`(`id_ingredient`, `id_maladie`) VALUES ("
                                + sauv_id_ingrediant
                                + "," + 3
                                + ");");
                    }

                } catch (SQLIntegrityConstraintViolationException e) {

                }

                add_ing.setText("");
                cbAllergene.setValue("Aucun");
                m1.setSelected(false);
                m2.setSelected(false);
                m3.setSelected(false);
                ap_home.toFront();

            } else {
                add_ing.setText("");
                cbAllergene.setValue("Aucun");
                m1.setSelected(false);
                m2.setSelected(false);
                m3.setSelected(false);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur ");
            alert.setContentText("Veuillez remplir le champ!");
            alert.showAndWait();
            cbAllergene.setValue("Aucun");
        }

        cbAllergene.setValue("Aucun");

    }

    public void supprimerIngrediant() throws SQLException {
        if (table_ingrediant1.getSelectionModel().isSelected(table_ingrediant1.getSelectionModel().getSelectedIndex())) {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            java.sql.Statement myStmt = cnx.createStatement();

            try {
                // myStmt2.execute("UPDATE `tableconnectee` SET `id_serveur`=NULL WHERE `id_serveur`="+Id+";");
                myStmt.execute("DELETE FROM `ingredient` WHERE id_ingredient=" + table_ingrediant1.getSelectionModel().getSelectedItem().getId_ingredient() + ";");

            } catch (SQLException e) {

            }
            table_ingrediant1.getItems().remove(table_ingrediant1.getSelectionModel().getSelectedItem());
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        columnId.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
        columnDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        columnDateAjout.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        columnCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        columnModeCuisson.setCellValueFactory(new PropertyValueFactory<>("modeCuisson"));
        columnTempsCuisson.setCellValueFactory(new PropertyValueFactory<>("tempsCuisson"));
        columnUsername_ger.setCellValueFactory(new PropertyValueFactory<>("username_ger"));
        ///////////
        columnId1.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
        columnDesignation1.setCellValueFactory(new PropertyValueFactory<>("designation"));
        columnDateAjout1.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        columnCategorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        columnModeCuisson1.setCellValueFactory(new PropertyValueFactory<>("modeCuisson"));
        columnTempsCuisson1.setCellValueFactory(new PropertyValueFactory<>("tempsCuisson"));
        columnUsername_ger1.setCellValueFactory(new PropertyValueFactory<>("username_ger"));
        //////////
        columnId2.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
        columnDesignation2.setCellValueFactory(new PropertyValueFactory<>("designation"));
        columnDateAjout2.setCellValueFactory(new PropertyValueFactory<>("dateAjout"));
        columnCategorie2.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        columnModeCuisson2.setCellValueFactory(new PropertyValueFactory<>("modeCuisson"));
        columnTempsCuisson2.setCellValueFactory(new PropertyValueFactory<>("tempsCuisson"));
        columnUsername_ger2.setCellValueFactory(new PropertyValueFactory<>("username_ger"));
        ///////////
        columnIdIngrediant.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediant.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        /////////
        columnIdIngrediant1.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediant1.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        ///////
        columnIdIngrediant2.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediant2.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        ///////
        columnIdIngrediantModifay.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediantModifay.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        //////
        columnIdIngrediantModifay1.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediantModifay1.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        //////
        columnIdIngrediantAffich.setCellValueFactory(new PropertyValueFactory<>("id_ingredient"));
        columnNomIngrediantAffich.setCellValueFactory(new PropertyValueFactory<>("nom_ing"));
        //////
        tf2.getItems().addAll("plat", "entree", "dessert", "boisson");
        tf22.getItems().addAll("plat", "entree", "dessert", "boisson");
        tf4.getItems().addAll("Aucun", "Au four", "Cocotte minute", "A la poêle", "Machine à Expresso", "Théière", "Crêpière");
        tf4.setValue("Aucun");
        tf33.getItems().addAll("Aucun", "Au four", "Cocotte minute", "A la poêle", "Machine à Expresso", "Théière", "Crêpière");
        tf33.setValue("Aucun");

        ap_home.toFront();
        btnconfirme.setVisible(false);
        table_ingrediant_selected.setVisible(false);
        btndelete.setVisible(false);
        listenvoi = new ArrayList<>();
        listenvoi2 = new ArrayList<>();

        add_ing.setStyle("-fx-prompt-text-fill: WHITE");

        JfxTextFieldCharLimit.addTextLimiter(tf11, 20);
        JfxTextFieldCharLimit.addTextLimiter(tf44, 3);

        ///////////////
        JfxTextFieldCharLimit.addTextLimiter(tf1, 20);
        JfxTextFieldCharLimit.addTextLimiter(tf3, 3);

        JfxTextFieldCharLimit.addTextLimiter(tfmp, 20);
        JfxTextFieldCharLimit.addTextLimiter(tfmc, 20);

        JfxTextFieldCharLimit.addTextLimiter(tfp2, 20);
        JfxTextFieldCharLimit.addTextLimiter(tfp3, 20);
        JfxTextFieldCharLimit.addTextLimiter(prixPetitAdd, 20);
        JfxTextFieldCharLimit.addTextLimiter(caloriePetitAdd, 20);
        JfxTextFieldCharLimit.addTextLimiter(prixGrandAdd, 20);
        JfxTextFieldCharLimit.addTextLimiter(calorieGrandAdd, 20);
        JfxTextFieldCharLimit.addTextLimiter(prixPetitModifay, 20);
        JfxTextFieldCharLimit.addTextLimiter(caloriePetitModifay, 20);

        tf11.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                tf11.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        tf1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                tf1.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        tf3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf3.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        tf44.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf44.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        tfp3.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfp3.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        tfmc.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfmc.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        caloriePetitAdd.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                caloriePetitAdd.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        caloriePetitModifay.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                caloriePetitModifay.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        calorieGrandModifay.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                calorieGrandModifay.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        calorieGrandAdd.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                calorieGrandAdd.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        cbAllergene.getItems().add("Aucun");

        try {

            Connection cnx = ApplicationDuGerant.connection;
            //execute sql query
            Statement myStmt = cnx.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT `nom_allergie` FROM `allergies` ");
            while (myRs.next()) {
                cbAllergene.getItems().add(myRs.getString("nom_allergie"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbAllergene.setValue("Aucun");

    }

}
