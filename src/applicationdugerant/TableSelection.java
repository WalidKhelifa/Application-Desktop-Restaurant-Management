/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;


import javafx.scene.control.CheckBox;

/**
 *
 * @author itashiNL
 */
public class TableSelection {
    
    String nom;
    CheckBox checkbox;

    TableSelection(String string, CheckBox ch) {
        this.nom=string;
        this.checkbox=ch;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public char getNum()
    {
        return nom.charAt(6);
    }
   
    
}
