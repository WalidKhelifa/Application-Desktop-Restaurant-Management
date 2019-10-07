/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;


public class platsCommandes {
    
    private int id_plat_commande,qte,id_plat,id_commande;
    private String portion;
    private String designation;

    public platsCommandes(int qte, int id_plat, String portion, String designation) {
        this.qte = qte;
        this.id_plat = id_plat;
        this.portion = portion;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    
    
    

    public int getId_plat_commande() {
        return id_plat_commande;
    }

    public void setId_plat_commande(int id_plat_commande) {
        this.id_plat_commande = id_plat_commande;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }
    
    
}
