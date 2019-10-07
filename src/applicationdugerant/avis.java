/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

public class avis {

    private String commentaire;
    private String date_avis;
    private String heure_avis;
    private int id_avis, id_client;
    private String userName;
    private String nb_etoile;

    public avis(String commentaire, String date_avis, String heure_avis, int id_avis, String nb_etoile, int id_client, String userName) {
        this.commentaire = commentaire;
        this.date_avis = date_avis;
        this.heure_avis = heure_avis;
        this.id_avis = id_avis;
        this.nb_etoile = nb_etoile;
        this.id_client = id_client;
        this.userName = userName;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(String date_avis) {
        this.date_avis = date_avis;
    }

    public String getHeure_avis() {
        return heure_avis;
    }

    public void setHeure_avis(String heure_avis) {
        this.heure_avis = heure_avis;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNb_etoile() {
        return nb_etoile;
    }

    public void setNb_etoile(String nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

}
