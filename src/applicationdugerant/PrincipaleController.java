/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class PrincipaleController implements Initializable {

    
    public void gestionDuRestaurant(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuRestaurant
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDuRestaurant.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion du restaurant");
    }
    
    public void gestionDuMenu(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuMenu
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDuMenu.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion du menu");
            
            
    }
    
    public void gestionDuPersonnel(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuPersonnel
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDuPersonnel.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion du personnel");
            
            
            
    }
    public void Consultation(ActionEvent event) throws IOException
    {
        ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre GestionDuPersonnel
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDesfeedbacks.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion des feedbacks");
    }
    
    public void gestionDesCommandes(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre 
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDesCommandes.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion des commandes");
            
            
    }
    public void gestionDesReservation(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre 
            Parent parent = FXMLLoader.load(getClass().getResource("GestionDesReservations.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Gestion des reservations");
            
            
    }
    
    public void Statistique(ActionEvent event) throws IOException
    {
            ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
            // ouvrir la fenetre 
            Parent parent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
            stage.setTitle("Les Statistiques");
            
            
    }
    
    
    
    public void Deconnecter() throws IOException
    {
        ApplicationDuGerant.stage.close(); // Fermer la fenetre Principale
        // retour a l'acceuil
        Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene scene= new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ApplicationDuGerant.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
