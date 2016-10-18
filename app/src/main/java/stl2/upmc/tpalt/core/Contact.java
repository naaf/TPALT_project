package stl2.upmc.tpalt.core;

/**
 * Created by ashraf on 16/10/2016.
 */

public class Contact {
    private String nom;
    private String prenom;
    private String codeBar;

    public Contact() {
    }

    public Contact(String nom, String prenom, String codeBar) {
        this.nom = nom;
        this.prenom = prenom;
        this.codeBar = codeBar;
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

    public String getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }
}
