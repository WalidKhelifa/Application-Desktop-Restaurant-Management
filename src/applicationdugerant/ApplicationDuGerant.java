/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationDuGerant extends Application {

    static Stage stage;
    static Connection connection;

    @Override
    public void start(Stage primaryStage) throws IOException {

        // ouvrir la fenetre d'acceuil
        Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        // Sauvegarder la fenetre d'acceuil
        stage = primaryStage;
        stage.setTitle("Accueil");
    }

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {

        //connection to database
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8889/Restaurant", "root", "root");
        connection = myConn;
        launch(args);
    }

}
