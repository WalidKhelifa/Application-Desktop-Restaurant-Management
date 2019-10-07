/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

public class commande {

    private int id_commande, id_client;
    private String date_commande;
    private String heure_commande;
    private String montant;
    private String type_commande, userName;

    public commande(int id_commande, int id_client, String date_commande, String heure_commande, String montant, String type_commande, String userName) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.date_commande = date_commande;
        this.heure_commande = heure_commande;
        this.montant = montant;
        this.type_commande = type_commande;
        this.userName = userName;
    }

    public commande(int id_commande, String date_commande, String heure_commande, String montant, String type_commande) {
        this.id_commande = id_commande;
        this.date_commande = date_commande;
        this.heure_commande = heure_commande;
        this.montant = montant;
        this.type_commande = type_commande;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

    public String getHeure_commande() {
        return heure_commande;
    }

    public void setHeure_commande(String heure_commande) {
        this.heure_commande = heure_commande;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getType_commande() {
        return type_commande;
    }

    public void setType_commande(String type_commande) {
        this.type_commande = type_commande;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
