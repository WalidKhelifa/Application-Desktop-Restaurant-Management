
package applicationdugerant;


import java.util.Date;


public class Personnel {

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
    
    private String nom,prenom,genre,adresse,numero_tel,email,type_personnel,username_ger,type_service;
    private Date date;
    private String dates;
    private int id;
    private String idTable;

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public Personnel(String idTable) {
        this.idTable = idTable;
    }

    public Personnel(String nom, String prenom, String genre, String adresse, String numero_tel, String email, String type_personnel, Date date ,String username_ger){
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.adresse = adresse;
        this.numero_tel = numero_tel;
        this.email = email;
        this.type_personnel = type_personnel;
        this.username_ger = username_ger;
        this.date = date;
        
    }
    public Personnel(int id,String nom, String prenom, String genre, String adresse, String numero_tel, String email, String type_personnel, Date date ,String username_ger) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.adresse = adresse;
        this.numero_tel = numero_tel;
        this.email = email;
        this.type_personnel = type_personnel;
        this.username_ger = username_ger;
        this.date = date;
    }

    Personnel(String string, int date, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getType_service() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    public Personnel(String nom, Date date, int id,String idTable) {
        this.nom = nom;
        this.date = date;
        this.id = id;
        this.idTable=idTable;
    }
        public Personnel(String nom, String dates, int id,String idTable) {
        this.nom = nom;
        this.dates = dates;
        this.id = id;
        this.idTable=idTable;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(String numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType_personnel() {
        return type_personnel;
    }

    public void setType_personnel(String type_personnel) {
        this.type_personnel = type_personnel;
    }

    public String getUsername_ger() {
        return username_ger;
    }

    public void setUsername_ger(String username_ger) {
        this.username_ger = username_ger;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    

}