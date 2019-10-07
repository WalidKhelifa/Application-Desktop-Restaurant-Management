/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationdugerant;


public class ingrediant {
   private int id_ingredient,id_allergie;
   private String nom_ing;

    public ingrediant(int id_ingredient, String nom_ing) {
        this.id_ingredient = id_ingredient;
        this.nom_ing = nom_ing;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public int getId_allergie() {
        return id_allergie;
    }

    public void setId_allergie(int id_allergie) {
        this.id_allergie = id_allergie;
    }

    public String getNom_ing() {
        return nom_ing;
    }

    public void setNom_ing(String nom_ing) {
        this.nom_ing = nom_ing;
    }
   
    
}
