package stl2.upmc.tpalt.core;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashraf on 16/10/2016.
 */

public class Contact {
    private String nom;
    private String prenom;
    private String codeBar;
    private List<String> dansGroupes;
    public boolean checked = false;

    public Contact() {
        dansGroupes = new ArrayList<>();
        dansGroupes.add(Groupe.DEFAUL.getNom());
    }

    public Contact(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        dansGroupes = new ArrayList<>();
        dansGroupes.add(Groupe.DEFAUL.getNom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!nom.equals(contact.nom)) return false;
        return prenom.equals(contact.prenom);

    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + prenom.hashCode();
        return result;
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

    public List<String> getDansGroupes() {
        return dansGroupes;
    }

    public void addDansGroupes(String nomGroupes) {
        if (dansGroupes.contains(nomGroupes))
            return;
        dansGroupes.add(nomGroupes);
    }

    public void removeDansGroupes(String nomGroupes) {
        dansGroupes.remove(nomGroupes);
    }
}
