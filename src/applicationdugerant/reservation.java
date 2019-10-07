/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;




public class reservation {
    private int id_reservation,id_table,id_client;
    private String date_reserv,date_rdv;
    private String heure_reserv,heure_rdv;
    private String etat_reserv;
    private String userName,nb_personnes;

    public String getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(String nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDate_reserv() {
        return date_reserv;
    }

    public void setDate_reserv(String date_reserv) {
        this.date_reserv = date_reserv;
    }

    public String getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(String date_rdv) {
        this.date_rdv = date_rdv;
    }

    public String getHeure_reserv() {
        return heure_reserv;
    }

    public void setHeure_reserv(String heure_reserv) {
        this.heure_reserv = heure_reserv;
    }

    public String getHeure_rdv() {
        return heure_rdv;
    }

    public void setHeure_rdv(String heure_rdv) {
        this.heure_rdv = heure_rdv;
    }

    public String getEtat_reserv() {
        return etat_reserv;
    }

    public void setEtat_reserv(String etat_reserv) {
        this.etat_reserv = etat_reserv;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   
    

    public reservation(int id_reservation, int id_table, int id_client, String date_reserv, String date_rdv, String heure_reserv, String heure_rdv, String etat_reserv,String userName,String nb_personnes) {
        this.id_reservation = id_reservation;
        this.id_table = id_table;
        this.id_client = id_client;
        this.date_reserv = date_reserv;
        this.date_rdv = date_rdv;
        this.heure_reserv = heure_reserv;
        this.heure_rdv = heure_rdv;
        this.etat_reserv = etat_reserv;
        this.userName=userName;
        this.nb_personnes=nb_personnes;
    }
    
    
}
