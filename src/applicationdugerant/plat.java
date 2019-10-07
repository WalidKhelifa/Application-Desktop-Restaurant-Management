/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;

import java.util.Date;

public class plat {
    
    private int id_plat;
    private String designation;
    private Date dateAjout;
    private String categorie;
    private String modeCuisson;
    private String tempsCuisson;
    private String username_ger;
    private int prixMoyen;

    

    public int getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(int prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public String getUsername_ger() {
        return username_ger;
    }

    public void setUsername_ger(String username_ger) {
        this.username_ger = username_ger;
    }

    public plat(int id_plat, String designation, Date dateAjout, String categorie, String modeCuisson, String tempsCuisson, String username_ger) {
        this.id_plat = id_plat;
        this.designation = designation;
        this.dateAjout = dateAjout;
        this.categorie = categorie;
        this.modeCuisson = modeCuisson;
        this.tempsCuisson = tempsCuisson;
        this.username_ger = username_ger;
    }

    public plat(String designation, Date dateAjout, String categorie, String modeCuisson, String tempsCuisson) {
        this.designation = designation;
        this.dateAjout = dateAjout;
        this.categorie = categorie;
        this.modeCuisson = modeCuisson;
        this.tempsCuisson = tempsCuisson;
    }

    public plat() {
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getModeCuisson() {
        return modeCuisson;
    }

    public void setModeCuisson(String modeCuisson) {
        this.modeCuisson = modeCuisson;
    }

    public String getTempsCuisson() {
        return tempsCuisson;
    }

    public void setTempsCuisson(String tempsCuisson) {
        this.tempsCuisson = tempsCuisson;
    }
    
    
}
